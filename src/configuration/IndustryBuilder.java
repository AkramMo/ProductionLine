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

public class IndustryBuilder {

	private ArrayList<Industry> listIndustry = new ArrayList<Industry>();
	private Store store;
	private XMLParserProductionLine xmlParser;


	public IndustryBuilder(XMLParserProductionLine xmlParser) {

		this.xmlParser = xmlParser;
		setIndustryList();
	}

	private void setIndustryList() {

		String industryType;
		int timeProduction = 0;
		int capacity = 0;
		ComponentIndustry componentOut = null;
		ArrayList<ComponentIndustry> entryList = new ArrayList<ComponentIndustry>();
		LinkedList<String> labelPathList= new LinkedList<String>();
		Industry industryTMP;
		Store store;


		if(this.xmlParser != null) {

			NodeList nodeIndustry= this.xmlParser.getIndustryList();

			for(int i = 0; i < nodeIndustry.getLength(); i++) {

				Node nNode = nodeIndustry.item(i);

				if(nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elementIndustry = (Element) nNode;

					industryType = elementIndustry.getAttribute(Industry.FIELD_TYPE);
					if(!industryType.equals("entrepot")) {
						
						timeProduction = Integer.parseInt(elementIndustry.getElementsByTagName(Industry.FIELD_INTERVAL).
								item(0).getTextContent());

						setEntryList(elementIndustry, entryList);
						componentOut = getComponentOut(elementIndustry);
						setPathList(elementIndustry, labelPathList);
						
						industryTMP = new Industry(industryType, entryList, timeProduction, labelPathList);
						entryList.clear();
						industryTMP.setComponentOut(componentOut);
						this.listIndustry.add(industryTMP);
						
					}else {

						setEntryList(elementIndustry,entryList);
						setPathList(elementIndustry, labelPathList);
						capacity = Integer.parseInt(( (Element) elementIndustry.getElementsByTagName(Industry.FIELD_ENTREE).
								item(0)).getAttribute(Store.FIELD_CAPACITY));
						store = new Store(capacity, labelPathList);
						this.store = store;
					}

				}
				labelPathList.clear();
			}

		}
	}


	private void setPathList(Element elementIndustry, LinkedList<String> pathIconList) {


		Node node = elementIndustry.getElementsByTagName(Industry.FIELD_ICONES).item(0);
		NodeList iconeList = ((Element) node).getElementsByTagName(Industry.FIELD_ICONE);
		Element iconeElement;
		String iconePath;
		
		for(int i = 0; i < iconeList.getLength(); i++) {
			
			iconeElement = (Element) iconeList.item(i);
			iconePath =  iconeElement.getAttribute(Industry.FIELD_PATH);
			iconePath = iconePath.substring(3);
			pathIconList.add(iconePath);


		}
	}
	
	private ComponentIndustry getComponentOut(Element elementIndustry) {

		String componentType;
		ComponentIndustry componentOut = null;
		componentType = ((Element) elementIndustry.getElementsByTagName(Industry.FIELD_SORTIE).
				item(0)).getAttribute(Industry.FIELD_TYPE);


		componentOut = getComponentByType(componentType);

		return componentOut;
	}
	
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Industry> getListIndustry() {
		
		return (ArrayList<Industry>) listIndustry.clone();
	}

	public Store getStore() {
		
		return store;
	}
}
