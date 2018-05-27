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

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private int taille = 32;
	private DomParserProductionLine domParser;
	private ArrayList<Usine> listUsine = new ArrayList<Usine>();
	private ArrayList<Usine> listUsineSimulation = new ArrayList<Usine>();
	private Entrepot entrepot;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
		// On ajoute à la position le delta x et y de la vitesse
		//drawUsine();
		//position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);


	}

	/**
	 * private void addIDPositionUsine() {

		NodeList usineAttributeList = this.domParser.getUsineAttributeList();
		String typeUsine;
		int idUsine;
		Point position;

		for(int i = 0; i < usineAttributeList.getLength(); i++) {

			Element elementUsineAttribute = (Element) usineAttributeList.item(i);
			typeUsine = elementUsineAttribute.getAttribute(Usine.FIELD_TYPE);
			idUsine = Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_ID));
			position = new Point(Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_X)),
					Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_Y)));

			if(this.entrepot.getTypeUsine().equals(typeUsine)) {

				this.entrepot.setPosition(position);
				this.entrepot.setIdEntrepot(idUsine);

			}else {

				for(int j = 0; j < this.listUsine.size(); j++) {

					if(this.listUsine.get(i).getTypeUsine().equals(typeUsine)) {

						this.listUsine.get(i).setPosition(position);
						this.listUsine.get(i).setIdUsine(idUsine);
					}
				}
			}
		}
	}
	 */

	private void drawUsine() {

		JLabel labelIcon;
		Point positionLabel;
		for(int i = 0; i < listUsine.size(); i++) {

			positionLabel = this.listUsine.get(i).getPosition();
			labelIcon = this.listUsine.get(i).getLabelIcon(0);
			this.add(labelIcon);
			labelIcon.setLocation(positionLabel);
		}


	}
	public void setDomParser(DomParserProductionLine domParser) {

		this.domParser = domParser;
	}





}