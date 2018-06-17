package configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import industry.Wing;
import industry.Plane;
import industry.ComponentIndustry;
import industry.Metal;
import industry.Engine;
import industry.Store;
import industry.Industry;

// TODO: Auto-generated Javadoc
/**
 * The Class IndustryBuilder.
 * Build and create all the list I need to create my simulation.
 * The class takes his informations from the XMLParserProductionLine
 * @author Akram Mousselmal
 * @version 1.0.0
 */
public class IndustryBuilder {

	/** The list industry. */
	private ArrayList<Industry> listIndustry = new ArrayList<Industry>();
	
	/** The store list. */
	private ArrayList<Store> storeList = new ArrayList<Store>();
	
	/** The xml parser. */
	private XMLParserProductionLine xmlParser;


	/**
	 * Instantiates a new industry builder.
	 *
	 * @param xmlParser the xml parser
	 */
	public IndustryBuilder(XMLParserProductionLine xmlParser) {

		this.xmlParser = xmlParser;
		setIndustryList();
	}

	/**
	 * Sets the industry list.
	 */
	private void setIndustryList() {

		String industryType;
		int timeProduction = 0;
		int capacity = 0;
		ComponentIndustry componentOut = null;
		ArrayList<ComponentIndustry> entryList = new ArrayList<ComponentIndustry>();
		LinkedList<String> pathList= new LinkedList<String>();
		Industry industryTMP;
		Store store;


		if(this.xmlParser != null) {

			NodeList nodeIndustry= this.xmlParser.getIndustryList();

			for(int i = 0; i < nodeIndustry.getLength(); i++) {

				Node nNode = nodeIndustry.item(i);

				if(nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elementIndustry = (Element) nNode;
					
					// Get the type of the industry
					industryType = elementIndustry.getAttribute(Industry.FIELD_TYPE);
					
					// verify if its a Store or an Industry
					if(!industryType.equals("entrepot")) {

						// Set all the attributs of a Store before adding it in a Store list.
						timeProduction = Integer.parseInt(elementIndustry.getElementsByTagName(Industry.FIELD_INTERVAL).
								item(0).getTextContent());

						setEntryList(elementIndustry, entryList);
						componentOut = getComponentOut(elementIndustry);
						setPathList(elementIndustry, pathList);

						industryTMP = new Industry(industryType, entryList, timeProduction, pathList);
						entryList.clear();
						industryTMP.setComponentOut(componentOut);
						this.listIndustry.add(industryTMP);

					}else {

						// Set all the attributs of an Industry before adding it in an Industry list
						setEntryList(elementIndustry,entryList);
						setPathList(elementIndustry, pathList);
						capacity = Integer.parseInt(( (Element) elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
								item(0)).getAttribute(Store.FIELD_CAPACITY));
						store = new Store(capacity, pathList);
						this.storeList.add(store);
					}

				}
				// Clear the path list before using them another time in the loop
				pathList.clear();
			}

		}
	}


	/**
	 * Sets the path list.
	 *
	 * @param elementIndustry the element industry
	 * @param pathIconList the path icon list
	 */
	private void setPathList(Element elementIndustry, LinkedList<String> pathIconList) {


		Node node = elementIndustry.getElementsByTagName(Industry.FIELD_ICONES).item(0);
		NodeList iconeList = ((Element) node).getElementsByTagName(Industry.FIELD_ICONE);
		Element iconeElement;
		String iconePath;

		// Create a list of path to the icon of an Industry
		for(int i = 0; i < iconeList.getLength(); i++) {
			
			iconeElement = (Element) iconeList.item(i);
			iconePath = iconeElement.getAttribute(Industry.FIELD_PATH);
			
			pathIconList.add(iconePath);
		}
	}

	/**
	 * Gets the component created by an industry.
	 *
	 * @param elementIndustry the element industry
	 * @return the component out
	 */
	private ComponentIndustry getComponentOut(Element elementIndustry) {

		String componentType;
		ComponentIndustry componentOut = null;
		
		// Get the type of the component
		componentType = ((Element) elementIndustry.getElementsByTagName(Industry.FIELD_SORTIE).
				item(0)).getAttribute(Industry.FIELD_TYPE);

		// create a component the will be use to identify what industry
		// needs to create
		componentOut = getComponentByType(componentType);

		return componentOut;
	}

	/**
	 * Sets the entry list.
	 *
	 * @param elementIndustry the element industry
	 * @param entryList the entry list
	 */
	private void setEntryList(Element elementIndustry, ArrayList<ComponentIndustry> entryList) {

		String componentType;
		int desiredQuantity = 0;
		ComponentIndustry entryComponent = null;

		//Element usineAttributes = 
		if( elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE) != null && 
				!elementIndustry.getAttribute(Industry.FIELD_TYPE).equals("entrepot")) {

			for(int i = 0; i < elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
					getLength(); i++) {

				componentType = ((Element) elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
						item(0)).getAttribute(Industry.FIELD_TYPE);
				desiredQuantity = Integer.parseInt(((Element) elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
						item(0)).getAttribute(Industry.FIELD_QUANTITE));

				entryComponent = getComponentByType(componentType);


				if(entryComponent != null) {

					entryComponent.setDesiredQuantity(desiredQuantity);
					entryList.add(entryComponent);
				}
			}
		}else {

			for(int i = 0; i < elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
					getLength(); i++) {

				componentType = ((Element) elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
						item(0)).getAttribute(Industry.FIELD_TYPE);
				entryComponent = getComponentByType(componentType);
			}
		}
	}


	/**
	 * Gets the component by type.
	 *
	 * @param componentType the component type
	 * @return the component by type
	 */
	private ComponentIndustry getComponentByType(String componentType) {

		ComponentIndustry componentIndustry = null;

		switch (componentType) {
		case "metal":
			componentIndustry = new Metal();
			break;
		case "avion":
			componentIndustry = new Plane();
			break;
		case "aile":
			componentIndustry = new Wing();
			break;
		case "moteur":
			componentIndustry = new Engine();
			break;
		default:
			break;
		}

		return componentIndustry;

	}

	/**
	 * Gets the list industry.
	 *
	 * @return the list industry
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Industry> getListIndustry() {

		return (ArrayList<Industry>) listIndustry.clone();
	}

	/**
	 * Gets the store list.
	 *
	 * @return the store list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Store> getStoreList() {

		return (ArrayList<Store>) this.storeList.clone();
	}
}
