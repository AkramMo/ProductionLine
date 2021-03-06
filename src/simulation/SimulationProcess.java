package simulation;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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

// TODO: Auto-generated Javadoc
/**
 * The Class SimulationProcess.
 */
public class SimulationProcess {

	/** The XML parser. */
	private XMLParserProductionLine XMLParser;
	
	/** The list industry. */
	private ArrayList<Industry> listIndustry;
	
	/** The list industry simulation. */
	private ArrayList<Industry> listIndustrySimulation;
	
	/** The list component. */
	private ArrayList<ComponentIndustry> listComponent = new ArrayList<ComponentIndustry>();
	
	/** The list path. */
	private ArrayList<PathIndustry> listPath;
	
	/** The sales strategy. */
	private Sales salesStrategy;
	
	/** The store list. */
	private ArrayList<Store> storeList;

	/**
	 * Instantiates a new simulation process.
	 *
	 * @param XMLParser the XML parser
	 */
	public SimulationProcess(XMLParserProductionLine XMLParser) {

		this.XMLParser = XMLParser;
		this.listIndustry = new ArrayList<Industry>();
		this.listIndustrySimulation = new ArrayList<Industry>();
		this.listPath = new ArrayList<PathIndustry>();
		setIndustryAndStoreList();
		setListIndustrySimulation();
		setPathList();
		setObserver();
	}



	/**
	 * Draw industry.
	 *
	 * @param g the g
	 * @param panel the panel
	 */
	public void drawIndustry(Graphics g, JPanel panel) {

		ImageIcon imageTMP;
		Point positionIcon;

		for(int i = 0; i < listIndustrySimulation.size(); i++) {

			positionIcon = this.listIndustrySimulation.get(i).getPosition();
			imageTMP = this.listIndustrySimulation.get(i).getIconByState();
			imageTMP.paintIcon(panel, g, positionIcon.x, positionIcon.y);


		}

		if(this.storeList != null) {

			for(int i = 0; i < this.storeList.size(); i++) {
				positionIcon = this.storeList.get(i).getPosition();
				imageTMP = this.storeList.get(i).getIconByState();
				imageTMP.paintIcon(panel,g, positionIcon.x, positionIcon.y);
			}
		}

		drawComponent(panel, g);
	}

	/**
	 * Draw component.
	 *
	 * @param panel the panel
	 * @param g the g
	 */
	private void drawComponent(JPanel panel, Graphics g) {

		ImageIcon imageTMP;
		Point positionIcone;


		if(!this.listComponent.isEmpty()) {
			for(int i = 0; i < listComponent.size(); i++) {

				this.listComponent.get(i).translatePosition();
				positionIcone = this.listComponent.get(i).getPosition();
				imageTMP = this.listComponent.get(i).getComponentIcon();
				imageTMP.paintIcon(panel, g, positionIcone.x, positionIcone.y);
			}
		}

		reachAnIndustry();
	}

	/**
	 * Draw path.
	 *
	 * @param g the g
	 */
	public void drawPath(Graphics g) {

		int initialID;
		int finalID;
		Industry initialIndustry;
		Industry finalIndustry;
		Point positionInitial = null;
		Point positionFinal = null;


		for(int i = 0; i < this.listPath.size(); i++) {
			initialID = this.listPath.get(i).getInitialID();
			finalID = this.listPath.get(i).getFinalID();



			initialIndustry= getIndustryByID(initialID);
			finalIndustry = getIndustryByID(finalID);

			if(finalIndustry == null) {


				for(int j = 0; j < this.storeList.size(); j++) {

					if(this.storeList.get(j).getIdStore() == finalID) {

						positionFinal = this.storeList.get(j).getPosition();
					}
				}

				positionInitial = initialIndustry.getPosition();

			}else {

				positionInitial = initialIndustry.getPosition();
				positionFinal = finalIndustry.getPosition();
			}

			g.drawLine(positionInitial.x + 16 , positionInitial.y + 16 , positionFinal.x + 16, positionFinal.y + 16);

		}
	}

	/**
	 * Gets the industry by ID.
	 *
	 * @param industryID the industry ID
	 * @return the industry by ID
	 */
	private Industry getIndustryByID(int industryID) {

		for(int i = 0; i < this.listIndustrySimulation.size(); i++) {

			if(this.listIndustrySimulation.get(i).getIdIndustry() == industryID) {

				return this.listIndustrySimulation.get(i);
			}
		}

		return null;
	}

	/**
	 * Sets the industry and store list.
	 */
	private void setIndustryAndStoreList() {

		IndustryBuilder industryBuilder;

		industryBuilder = new IndustryBuilder(XMLParser);
		this.listIndustry = industryBuilder.getListIndustry();
		this.storeList = industryBuilder.getStoreList();

	}

	/**
	 * Sets the list industry simulation.
	 */
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

				if(Store.TYPE_USINE.equals(typeIndustry) && this.storeList != null) {

					for(int j = 0; j < this.storeList.size(); j++) {
						this.storeList.get(j).setPosition(position);
						this.storeList.get(j).setIdStore(idIndustry);
					}
				}else {

					for(int j = 0; j < this.listIndustry.size(); j++) {

						if(this.listIndustry.get(j).getIndustryType().equals(typeIndustry)) {

							industryTMP = new Industry(this.listIndustry.get(j).getIndustryType(),
									this.listIndustry.get(j).getEntry(),this.listIndustry.get(j).getTimeProduction(), 
									this.listIndustry.get(j).getPathList());

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

	/**
	 * Sets the path list.
	 */
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

	/**
	 * Sets the XML parser.
	 *
	 * @param XMLParser the new XML parser
	 */
	public void setXMLParser(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			setListIndustrySimulation();
			setIndustryAndStoreList();
		}
	}

	/**
	 * Update list component.
	 *
	 * @param salesStrategy the sales strategy
	 */
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

	/**
	 * Sets the component positions.
	 *
	 * @param component the component
	 * @param initialID the initial ID
	 */
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

		for(int j =0; j < this.storeList.size(); j++) {

			if(finalID == this.storeList.get(j).getIdStore()){

				finalPosition = this.storeList.get(j).getPosition();
			}
		}

		component.setSpeedAndPosition(initialPosition, finalPosition);

	}

	/**
	 * Gets the final ID industry.
	 *
	 * @param initialID the initial ID
	 * @return the final ID industry
	 */
	private int getFinalIDIndustry(int initialID) {

		int finalID = -1;

		for(int i = 0; i < this.listPath.size(); i++) {

			if(initialID == this.listPath.get(i).getInitialID()) {

				finalID = this.listPath.get(i).getFinalID();
			}
		}

		return finalID;
	}

	/**
	 * Gets the component by type.
	 *
	 * @param type the type
	 * @return the component by type
	 */
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

	/**
	 * Sets the observer.
	 */
	private void setObserver() {

		for(int j = 0; j < this.storeList.size(); j++) {

			for(int i = 0; i < this.listIndustrySimulation.size(); i++) {


				this.storeList.get(j).addObserver(this.listIndustrySimulation.get(i));
			}
		}
	}

	/**
	 * Reach an industry.
	 */
	private void reachAnIndustry() {

		int xComponent;
		int yComponent;
		int xIndustry;
		int yIndustry;

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
				}else if(reachAStore(xComponent, yComponent)) {

					this.listComponent.remove(j);
					j--;
				}
			}
		}
	}

	/**
	 * Reach A store.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	private boolean reachAStore(int x, int y) {

		Point positionStore = null;

		for(int i = 0; i < this.storeList.size(); i++) {

			positionStore = this.storeList.get(i).getPosition();

			if(positionStore.x == x && positionStore.y == y) {

				this.storeList.get(i).setQuantity();
				return true;
			}
		}

		return false;
	}

	/**
	 * Update industry.
	 */
	public void updateIndustry() {

		for(int i = 0; i < listIndustrySimulation.size(); i++) {

			this.listIndustrySimulation.get(i).updateIndustry();
		}

		if(this.salesStrategy != null) {

			for(int j = 0; j < this.storeList.size(); j++) {
				this.storeList.get(j).doASales(salesStrategy);
				this.storeList.get(j).updateIcon();
			}
		}
	}
}
