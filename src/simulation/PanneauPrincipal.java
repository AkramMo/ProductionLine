package simulation;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
import xmlParser.DomParserProductionLine;
import xmlParser.UsineBuilder;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private int taille = 32;
	private DomParserProductionLine domParser;
	private ArrayList<Usine> listUsine;
	private ArrayList<Usine> listUsineSimulation;
	private Entrepot entrepot;


	public PanneauPrincipal() {

		this.domParser = null;
		this.listUsine = new ArrayList<Usine>();
		this.listUsineSimulation = new ArrayList<Usine>();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		updateListUsineSimulation();
		// On ajoute à la position le delta x et y de la vitesse
		drawUsine();
		//position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);

		//add(new Metal().getLabelIcon());
	
	}

	private void updateListUsineSimulation() {
		if(this.domParser != null) {
			NodeList usineAttributeList = this.domParser.getUsineAttributeList();
			String typeUsine;
			int idUsine;
			Usine usineTMP;
			Point position;

			for(int i = 0; i < usineAttributeList.getLength(); i++) {

				Element elementUsineAttribute = (Element) usineAttributeList.item(i);
				typeUsine = elementUsineAttribute.getAttribute(Usine.FIELD_TYPE);
				idUsine = Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_ID));
				position = new Point(Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_X)),
						Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_Y)));

				if(Entrepot.FIELD_CAPACITY.equals(typeUsine)) {

					this.entrepot.setPosition(position);
					this.entrepot.setIdEntrepot(idUsine);
				}else {

					for(int j = 0; j < this.listUsine.size(); j++) {

						if(this.listUsine.get(j).getTypeUsine().equals(typeUsine)) {

							usineTMP = this.listUsine.get(j);
							usineTMP.setPosition(position);
							usineTMP.setIdUsine(idUsine);
							this.listUsineSimulation.add(usineTMP);
						}
					}
				}
			}
		}
	}

	private void drawUsine() {

		JLabel labelIcon;
		Point positionLabel;
		for(int i = 0; i < listUsineSimulation.size(); i++) {

			positionLabel = this.listUsineSimulation.get(i).getPosition();
			labelIcon = this.listUsineSimulation.get(i).getLabelIcon().get(0);
			this.add(labelIcon);
			labelIcon.setLocation(positionLabel);
		}


	}
	public void setDomParser(DomParserProductionLine domParser) {

		this.domParser = domParser;
	}

	public void setUsineList() {

		UsineBuilder usineBuilder;
		if(this.domParser != null) {

			usineBuilder = new UsineBuilder(domParser);
			this.listUsine = usineBuilder.getListUsine();
			this.entrepot = usineBuilder.getEntrepot();
		}
	}


}