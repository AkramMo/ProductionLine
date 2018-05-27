package xmlParser;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import industrie.Aile;
import industrie.Avion;
import industrie.ComponentIndustry;
import industrie.Entrepot;
import industrie.Metal;
import industrie.Moteur;
import industrie.Usine;

public class UsineBuilder {

	private ArrayList<Usine> listUsine = new ArrayList<Usine>();
	private Entrepot entrepot;
	private DomParserProductionLine domParser;


	public UsineBuilder(DomParserProductionLine domParser) {

		this.domParser = domParser;
		updateUsineList();
	}



	public ArrayList<Usine> getListUsine() {
		return listUsine;
	}

	public Entrepot getEntrepot() {
		return entrepot;
	}
	
	public void updateUsineList() {

		String typeUsine;
		int timeProduction = 0;
		int capacity = 0;
		ComponentIndustry componentOut = null;
		ArrayList<ComponentIndustry> entryList = new ArrayList<ComponentIndustry>();
		ArrayList<JLabel> labelIconList = new ArrayList<JLabel>();


		if(this.domParser != null) {

			NodeList usineList = this.domParser.getUsineList();

			for(int i = 0; i < usineList.getLength(); i++) {

				Node nNode = usineList.item(i);
				System.out.println("Element courant " + nNode.getNodeName());

				if(nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elementUsine = (Element) nNode;

					typeUsine = elementUsine.getAttribute(Usine.FIELD_TYPE);
					if(!typeUsine.equals("entrepot")) {
						timeProduction = Integer.parseInt(elementUsine.getElementsByTagName(Usine.FIELD_INTERVAL).
								item(0).getTextContent());

						setEntryList(elementUsine, entryList);
						setComponentOut(elementUsine, componentOut);
						setIconeList(elementUsine, labelIconList);

						this.listUsine.add(new Usine(typeUsine, entryList, timeProduction, labelIconList));
					}else {

						setEntryList(elementUsine,entryList);
						setIconeList(elementUsine, labelIconList);
						capacity = Integer.parseInt(( (Element) elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
								item(0)).getAttribute(Entrepot.FIELD_CAPACITY));

						this.entrepot = new Entrepot(typeUsine, entryList, capacity, labelIconList);
					}

				}

			}

		}
	}


	private void setIconeList(Element elementUsine, ArrayList<JLabel> labelIconList) {


		Node node = elementUsine.getElementsByTagName(Usine.FIELD_ICONES).item(0);
		NodeList iconeList = ((Element) node).getElementsByTagName(Usine.FIELD_ICONE);
		Element iconeElement;
		JLabel iconeLabel;
		String iconePath;
		for(int i = 0; i < iconeList.getLength(); i++) {
			iconeElement = (Element) iconeList.item(i);
			iconePath =  iconeElement.getAttribute(Usine.FIELD_PATH);
			iconePath = iconePath.substring(3);
			try {
				BufferedImage classPathImage = ImageIO.read(getClass().getResourceAsStream(iconePath));

				iconeLabel = new JLabel(new ImageIcon(classPathImage));
				labelIconList.add(iconeLabel);

			} catch (IOException e) {

				System.err.println("Icone introuvable !");
			}

		}
	}
	private void setComponentOut(Element elementUsine, ComponentIndustry componentOut) {

		String componentType;

		componentType = ((Element) elementUsine.getElementsByTagName(Usine.FIELD_SORTIE).
				item(0)).getAttribute(Usine.FIELD_TYPE);


		setComponentByType(componentType, componentOut);

	}
	private void setEntryList(Element elementUsine, ArrayList<ComponentIndustry> entryList) {

		String componentType;
		int desiredQuantity = 0;
		int desiredCapacity = 0;
		ComponentIndustry entryComponent = null;

		//Element usineAttributes = 
		if( elementUsine.getElementsByTagName(Usine.FIELD_ENTREE) != null && 
				!elementUsine.getAttribute(Usine.FIELD_TYPE).equals("entrepot")) {

			for(int i = 0; i < elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
					getLength(); i++) {

				componentType = ((Element) elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
						item(0)).getAttribute(Usine.FIELD_TYPE);
				desiredQuantity = Integer.parseInt(((Element) elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
						item(0)).getAttribute(Usine.FIELD_QUANTITE));

				setComponentByType(componentType, entryComponent);


				if(entryComponent != null) {

					entryComponent.setDesiredQuantity(desiredQuantity);
					entryList.add(entryComponent);
				}

			}
		}else {

			for(int i = 0; i < elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
					getLength(); i++) {

				componentType = ((Element) elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
						item(0)).getAttribute(Usine.FIELD_TYPE);
				desiredCapacity = Integer.parseInt(((Element) elementUsine.getElementsByTagName(Usine.FIELD_ENTREE).
						item(0)).getAttribute(Entrepot.FIELD_CAPACITY));

				setComponentByType(componentType, entryComponent);


				if(entryComponent != null) {

					entryComponent.setDesiredCapacity(desiredCapacity);
					entryList.add(entryComponent);
				}

			}
		}

	}


	private void setComponentByType(String componentType, ComponentIndustry componentIndustry) {

		switch (componentType) {
		case "metal":
			componentIndustry = new Metal();
			break;
		case "avion":
			componentIndustry = new Avion();
			break;
		case "aile":
			componentIndustry = new Aile();
			break;
		case "moteur":
			componentIndustry = new Moteur();
			break;
		default:
			break;
		}

	}
}
