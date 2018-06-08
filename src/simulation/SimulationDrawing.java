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
import industrie.Aile;
import industrie.Avion;
import industrie.ComponentIndustry;
import industrie.Entrepot;
import industrie.Metal;
import industrie.Moteur;
import industrie.Usine;
import network.PathIndustry;

public class SimulationDrawing {

	private final int TAILLE = 32;
	private XMLParserProductionLine XMLParser;
	private ArrayList<Usine> listUsine;
	private ArrayList<Usine> listUsineSimulation;
	private ArrayList<ComponentIndustry> listComponent = new ArrayList<ComponentIndustry>();
	private ArrayList<PathIndustry> listPath;
	private Entrepot entrepot;

	public SimulationDrawing(XMLParserProductionLine XMLParser) {

		this.XMLParser = XMLParser;
		this.listUsine = new ArrayList<Usine>();
		this.listUsineSimulation = new ArrayList<Usine>();
		this.listPath = new ArrayList<PathIndustry>();
		setUsineList();
		setListUsineSimulation();
		setPathList();
		setObserver();
	}



	public void drawUsine(JPanel pannel) {

		JLabel labelIcon;
		Point positionLabel;
		pannel.removeAll();
		//this.listUsine.get(1).getLabelIcon();
		for(int i = 0; i < listUsineSimulation.size(); i++) {

			positionLabel = this.listUsineSimulation.get(i).getPosition();
			labelIcon = this.listUsineSimulation.get(i).getLabelIcon();
			labelIcon.setBounds(positionLabel.x,positionLabel.y, TAILLE, TAILLE);

			pannel.add(labelIcon);

		}

		if(this.entrepot != null) {
			positionLabel = this.entrepot.getPosition();
			labelIcon = this.entrepot.getLabelIconList().get(0);
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
	}

	public void drawPath(Graphics g) {

		int initialID;
		int finalID;
		Usine usineInitial;
		Usine usineFinal;
		Point positionInitial;
		Point positionFinal;


		for(int i = 0; i < this.listPath.size(); i++) {
			initialID = this.listPath.get(i).getInitialID();
			finalID = this.listPath.get(i).getFinalID();

			if(initialID == this.entrepot.getIdEntrepot()) {

				positionInitial = this.entrepot.getPosition();
				usineFinal = getUsineByID(finalID);
				positionFinal = usineFinal.getPosition();
			}else if(finalID == this.entrepot.getIdEntrepot()) {

				usineInitial = getUsineByID(initialID);
				positionInitial = usineInitial.getPosition();
				positionFinal = this.entrepot.getPosition();	
			}else {

				usineInitial = getUsineByID(initialID);
				usineFinal = getUsineByID(finalID);
				positionInitial = usineInitial.getPosition();
				positionFinal = usineFinal.getPosition();
			}

			if(positionInitial.y < positionFinal.y && positionInitial.x > positionFinal.x) {

				g.drawLine(positionInitial.x + 10 , positionInitial.y + 32 , positionFinal.x + 15, positionFinal.y);
			}else if( positionInitial.y > positionFinal.y && positionInitial.x > positionFinal.x){

				g.drawLine(positionInitial.x + 10 , positionInitial.y , positionFinal.x + 15, positionFinal.y + 32 );		
			}else {
				g.drawLine(positionInitial.x + 32, positionInitial.y + 16, positionFinal.x, positionFinal.y + 16);
			}
		}
	}



	private Usine getUsineByID(int usineID) {

		for(int i = 0; i < this.listUsineSimulation.size(); i++) {

			if(this.listUsineSimulation.get(i).getIdUsine() == usineID) {

				return this.listUsineSimulation.get(i);
			}
		}

		return null;
	}

	private void setUsineList() {

		IndustryBuilder usineBuilder;

		usineBuilder = new IndustryBuilder(XMLParser);
		this.listUsine = usineBuilder.getListUsine();
		this.entrepot = usineBuilder.getEntrepot();

	}

	private void setListUsineSimulation() {

		String typeUsine;
		int idUsine;
		Usine usineTMP;
		Point position;

		if(this.XMLParser != null && this.listUsineSimulation.isEmpty()) {

			NodeList usineAttributeList = this.XMLParser.getUsineAttributeList();

			for(int i = 0; i < usineAttributeList.getLength(); i++) {

				Element elementUsineAttribute = (Element) usineAttributeList.item(i);
				typeUsine = elementUsineAttribute.getAttribute(Usine.FIELD_TYPE);
				idUsine = Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_ID));
				position = new Point(Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_X)),
						Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_Y)));
				System.out.println(idUsine);
				if(Entrepot.TYPE_USINE.equals(typeUsine) && this.entrepot != null) {

					this.entrepot.setPosition(position);
					this.entrepot.setIdEntrepot(idUsine);
				}else {

					for(int j = 0; j < this.listUsine.size(); j++) {

						if(this.listUsine.get(j).getTypeUsine().equals(typeUsine)) {

							usineTMP = new Usine(this.listUsine.get(j).getTypeUsine(),
									this.listUsine.get(j).getEntry(),this.listUsine.get(j).getTimeProduction(), 
									this.listUsine.get(j).getLabelPathList());
							usineTMP.setPosition(position);
							usineTMP.setIdUsine(idUsine);
							usineTMP.setComponentOut(getComponentByType(this.listUsine.get(j).getComponentType()));
							this.listUsineSimulation.add(usineTMP);
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

			NodeList usinePathList = this.XMLParser.getPathList();

			for(int i = 0; i < usinePathList.getLength(); i++) {

				Element elementUsineAttribute = (Element) usinePathList.item(i);
				initialID = Integer.parseInt(elementUsineAttribute.getAttribute(PathIndustry.FIELD_DE));
				finalID = Integer.parseInt(elementUsineAttribute.getAttribute(PathIndustry.FIELD_VERS));

				this.listPath.add(new PathIndustry(initialID, finalID));

			}
		}
	}	

	public void setXMLParser(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			setListUsineSimulation();
			setUsineList();
		}
	}


	public void updateListComponent() {

		String componentType;
		ComponentIndustry component = null;

		for(int i = 0; i < listUsineSimulation.size(); i++) {

			this.listUsineSimulation.get(i).updateUsine();
			componentType = this.listUsineSimulation.get(i).getComponentOutToCreate();

			if(componentType != null) {
				component = getComponentByType(componentType);

				setComponentPositions(component,  this.listUsineSimulation.get(i).getIdUsine());

				this.listComponent.add(component);
			}
		}
	}

	private void setComponentPositions(ComponentIndustry component, int initialID) {

		int finalID = getFinalIDUsine(initialID);
		Point initialPosition = null;
		Point finalPosition = null;
		for(int i = 0; i < this.listUsineSimulation.size(); i++) {

			if(this.listUsineSimulation.get(i).getIdUsine() == initialID) {

				initialPosition = this.listUsineSimulation.get(i).getPosition();

			}else if(this.listUsineSimulation.get(i).getIdUsine() == finalID) {

				finalPosition = this.listUsineSimulation.get(i).getPosition();
			}
		}

		component.setVitesseAndPosition(initialPosition, finalPosition);

	}

	private int getFinalIDUsine(int initialID) {

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
			component = new Avion();
			break;
		case "aile":
			component = new Aile();
			break;
		case "moteur":
			component = new Moteur();
			break;
		default:
			break;
		}

		return component;
	}
	private void setObserver() {

		for(int i = 0; i < this.listUsineSimulation.size(); i++) {

			this.entrepot.addObserver(this.listUsineSimulation.get(i));
		}
	}




	public void updateUsine() {


		//this.listUsine.get(1).getLabelIcon();
		for(int i = 0; i < listUsineSimulation.size(); i++) {

			this.listUsineSimulation.get(i).updateUsine();


		}

	}
}
