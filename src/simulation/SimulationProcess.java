package simulation;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import configuration.IndustryBuilder;
import configuration.XMLParserProductionLine;
import industry.Wing;
import industry.Plane;
import industry.ComponentIndustry;
import industry.Metal;
import industry.Engine;
import industry.Store;
import industry.Industry;
import network.PathIndustry;
import selling.Sales;

public class SimulationProcess {

	private final int TAILLE = 32;
	private XMLParserProductionLine XMLParser;
	private ArrayList<Industry> listIndustry;
	private ArrayList<Industry> listIndustrySimulation;
	private ArrayList<ComponentIndustry> listComponent = new ArrayList<ComponentIndustry>();
	private ArrayList<PathIndustry> listPath;
	private Sales salesStrategy;
	private Store store;

	public SimulationProcess(XMLParserProductionLine XMLParser) {

		this.XMLParser = XMLParser;
		this.listIndustry = new ArrayList<Industry>();
		this.listIndustrySimulation = new ArrayList<Industry>();
		this.listPath = new ArrayList<PathIndustry>();
		setIndustryList();
		setListIndustrySimulation();
		setPathList();
		setObserver();
	}



	public void drawIndustry(JPanel pannel) {

		JLabel labelIcon;
		Point positionLabel;
		pannel.removeAll();
		
		for(int i = 0; i < listIndustrySimulation.size(); i++) {

			positionLabel = this.listIndustrySimulation.get(i).getPosition();
			labelIcon = this.listIndustrySimulation.get(i).getLabelIcon();
			labelIcon.setBounds(positionLabel.x,positionLabel.y, TAILLE, TAILLE);

			pannel.add(labelIcon);

		}

		if(this.store != null) {
			
			positionLabel = this.store.getPosition();
			labelIcon = this.store.getJLabel();
			labelIcon.setBounds(positionLabel.x, positionLabel.y, TAILLE, TAILLE);

			pannel.add(labelIcon);
		}

		drawComponent(pannel);
	}

	private void drawComponent(JPanel pannel) {

		JLabel labelIcon;
		Point positionLabel;


		if(!this.listComponent.isEmpty()) {
			for(int i = 0; i < listComponent.size(); i++) {

				this.listComponent.get(i).translatePosition();
				positionLabel = this.listComponent.get(i).getPosition();
				labelIcon = this.listComponent.get(i).getLabelIcon();
				labelIcon.setBounds(positionLabel.x,positionLabel.y, TAILLE, TAILLE);

				pannel.add(labelIcon);
			}
		}

		updateComponent();
	}

	public void drawPath(Graphics g) {

		int initialID;
		int finalID;
		Industry initialIndustry;
		Industry finalIndustry;
		Point positionInitial;
		Point positionFinal;


		for(int i = 0; i < this.listPath.size(); i++) {
			initialID = this.listPath.get(i).getInitialID();
			finalID = this.listPath.get(i).getFinalID();

			if(initialID == this.store.getIdStore()) {

				positionInitial = this.store.getPosition();
				finalIndustry= getIndustryByID(finalID);
				positionFinal = finalIndustry.getPosition();
			}else if(finalID == this.store.getIdStore()) {

				initialIndustry= getIndustryByID(initialID);
				positionInitial = initialIndustry.getPosition();
				positionFinal = this.store.getPosition();	
			}else {

				initialIndustry= getIndustryByID(initialID);
				finalIndustry = getIndustryByID(finalID);
				positionInitial = initialIndustry.getPosition();
				positionFinal = finalIndustry.getPosition();
			}


			if(positionInitial.y < positionFinal.y && positionInitial.x > positionFinal.x) {

				g.drawLine(positionInitial.x + 5 , positionInitial.y + 32 , positionFinal.x + 32, positionFinal.y);
			}else if( positionInitial.y > positionFinal.y && positionInitial.x > positionFinal.x){

				g.drawLine(positionInitial.x + 5 , positionInitial.y + 2 , positionFinal.x + 30, positionFinal.y + 30 );		
			}else {
				
				g.drawLine(positionInitial.x + 32, positionInitial.y + 16, positionFinal.x, positionFinal.y + 16);
			}
		}
	}

	private Industry getIndustryByID(int industryID) {

		for(int i = 0; i < this.listIndustrySimulation.size(); i++) {

			if(this.listIndustrySimulation.get(i).getIdIndustry() == industryID) {

				return this.listIndustrySimulation.get(i);
			}
		}

		return null;
	}

	private void setIndustryList() {

		IndustryBuilder industryBuilder;

		industryBuilder = new IndustryBuilder(XMLParser);
		this.listIndustry = industryBuilder.getListIndustry();
		this.store = industryBuilder.getStore();

	}

	private void setListIndustrySimulation() {

		String typeIndustry;
		int idIndustry;
		Industry industryTMP;
		Point position;

		if(this.XMLParser != null && this.listIndustrySimulation.isEmpty()) {

			NodeList industryAttributeList = this.XMLParser.getIndustryAttributeList();

			for(int i = 0; i < industryAttributeList.getLength(); i++) {

				Element elementIndustryAttribute = (Element) industryAttributeList.item(i);
				
				typeIndustry = elementIndustryAttribute.getAttribute(Industry.FIELD_TYPE);
				
				idIndustry = Integer.parseInt(elementIndustryAttribute.getAttribute(Industry.FIELD_ID));
				
				position = new Point(Integer.parseInt(elementIndustryAttribute.getAttribute(Industry.FIELD_X)),
						Integer.parseInt(elementIndustryAttribute.getAttribute(Industry.FIELD_Y)));
				
				if(Store.TYPE_USINE.equals(typeIndustry) && this.store != null) {

					this.store.setPosition(position);
					this.store.setIdStore(idIndustry);
				}else {

					for(int j = 0; j < this.listIndustry.size(); j++) {

						if(this.listIndustry.get(j).getIndustryType().equals(typeIndustry)) {

							industryTMP = new Industry(this.listIndustry.get(j).getIndustryType(),
									this.listIndustry.get(j).getEntry(),this.listIndustry.get(j).getTimeProduction(), 
									this.listIndustry.get(j).getLabelPathList());
							
							industryTMP.setPosition(position);
							
							industryTMP.setIdIndustry(idIndustry);
							
							industryTMP.setComponentOut(getComponentByType(this.listIndustry.get(j).getComponentType()));
							
							this.listIndustrySimulation.add(industryTMP);
						}
					}
				}
			}
		}
	}

	private void setPathList() {

		int initialID;
		int finalID;

		if(this.XMLParser != null && this.listPath.isEmpty()) {

			NodeList industryPathList = this.XMLParser.getPathList();

			for(int i = 0; i < industryPathList.getLength(); i++) {

				Element elementIndustryAttribute = (Element) industryPathList.item(i);
				initialID = Integer.parseInt(elementIndustryAttribute.getAttribute(PathIndustry.FIELD_DE));
				finalID = Integer.parseInt(elementIndustryAttribute.getAttribute(PathIndustry.FIELD_VERS));
				this.listPath.add(new PathIndustry(initialID, finalID));
			}
		}
	}	

	public void setXMLParser(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			setListIndustrySimulation();
			setIndustryList();
		}
	}

	public void updateListComponent(Sales salesStrategy) {

		String componentType;
		ComponentIndustry component = null;
		this.salesStrategy = salesStrategy;

		for(int i = 0; i < listIndustrySimulation.size(); i++) {

			componentType = this.listIndustrySimulation.get(i).getComponentOutToCreate();
			
			if(componentType != null) {
				
				component = getComponentByType(componentType);
				setComponentPositions(component,  this.listIndustrySimulation.get(i).getIdIndustry());
				this.listComponent.add(component);
			}
		}
	}

	private void setComponentPositions(ComponentIndustry component, int initialID) {

		int finalID = getFinalIDIndustry(initialID);
		Point initialPosition = null;
		Point finalPosition = null;


		for(int i = 0; i < this.listIndustrySimulation.size(); i++) {

			if(this.listIndustrySimulation.get(i).getIdIndustry() == initialID) {

				initialPosition = this.listIndustrySimulation.get(i).getPosition();

			}else if(this.listIndustrySimulation.get(i).getIdIndustry() == finalID) {

				finalPosition = this.listIndustrySimulation.get(i).getPosition();
			}
		}
		if(finalID == this.store.getIdStore()){

			finalPosition = this.store.getPosition();
		}


		component.setSpeedAndPosition(initialPosition, finalPosition);

	}

	private int getFinalIDIndustry(int initialID) {

		int finalID = -1;

		for(int i = 0; i < this.listPath.size(); i++) {

			if(initialID == this.listPath.get(i).getInitialID()) {

				finalID = this.listPath.get(i).getFinalID();
			}
		}

		return finalID;
	}

	private ComponentIndustry getComponentByType(String type) {

		ComponentIndustry component = null;

		switch (type) {
		case "metal":
			component = new Metal();
			break;
		case "avion":
			component = new Plane();
			break;
		case "aile":
			component = new Wing();
			break;
		case "moteur":
			component = new Engine();
			break;
		default:
			break;
		}

		return component;
	}

	private void setObserver() {

		for(int i = 0; i < this.listIndustrySimulation.size(); i++) {

			this.store.addObserver(this.listIndustrySimulation.get(i));
		}
	}

	private void updateComponent() {

		int xComponent;
		int yComponent;
		int xIndustry;
		int yIndustry;
		int xStore = this.store.getPosition().x;
		int yStore = this.store.getPosition().y;

		for(int i = 0; i < listIndustrySimulation.size(); i++) {

			this.listIndustrySimulation.get(i);

			for(int j = 0; j < this.listComponent.size(); j++) {
				xComponent = this.listComponent.get(j).getPosition().x; 
				xIndustry = this.listIndustrySimulation.get(i).getPosition().x;				
				yComponent = this.listComponent.get(j).getPosition().y;
				yIndustry = this.listIndustrySimulation.get(i).getPosition().y;


				if(xComponent == xIndustry && yComponent == yIndustry) {

					this.listIndustrySimulation.get(i).updateEntryByType(this.listComponent.get(j).getType());

					this.listComponent.remove(j);

					j--;
				}else if(xComponent == xStore && yComponent == yStore) {

					this.store.setQuantity();
					this.listComponent.remove(j);
					j--;
				}
			}
		}
	}
	
	public void updateIndustry() {

		for(int i = 0; i < listIndustrySimulation.size(); i++) {

			this.listIndustrySimulation.get(i).updateIndustry();
		}
		
		if(this.salesStrategy != null) {
			
			this.store.doASales(salesStrategy);
			this.store.updateLabel();
		}
	}
}
