package simulation;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import configuration.IndustryBuilder;
import configuration.XMLParserProductionLine;
import industrie.Entrepot;
import industrie.Usine;
import network.PathIndustry;

public class SimulationDrawing {

	private final int TAILLE = 32;
	private XMLParserProductionLine XMLParser;
	private ArrayList<Usine> listUsine;
	private ArrayList<Usine> listUsineSimulation;
	private ArrayList<PathIndustry> listPath;
	private Entrepot entrepot;

	public SimulationDrawing(XMLParserProductionLine XMLParser) {

		this.XMLParser = XMLParser;
		this.listUsine = new ArrayList<Usine>();
		this.listUsineSimulation = new ArrayList<Usine>();
		this.listPath = new ArrayList<PathIndustry>();
		setUsineList();
		setListUsineSimulation();
		//setPathList
	}



	public void drawUsine(JPanel pannel) {

		JLabel labelIcon;
		Point positionLabel;

		//this.listUsine.get(1).getLabelIcon();
		for(int i = 0; i < listUsineSimulation.size(); i++) {

			positionLabel = this.listUsineSimulation.get(i).getPosition();
			labelIcon = this.listUsineSimulation.get(i).getLabelIcon().get(0);
			labelIcon.setBounds(positionLabel.x,positionLabel.y, TAILLE, TAILLE);

			pannel.add(labelIcon);

		}

		if(this.entrepot != null) {
			positionLabel = this.entrepot.getPosition();
			labelIcon = this.entrepot.getLabelIconList().get(0);
			labelIcon.setBounds(positionLabel.x, positionLabel.y, TAILLE, TAILLE);

			pannel.add(labelIcon);
		}
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
							this.listUsineSimulation.add(usineTMP);
						}
					}
				}
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
}
