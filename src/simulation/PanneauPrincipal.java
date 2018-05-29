package simulation;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import configuration.DomParserProductionLine;
import configuration.UsineBuilder;
import industrie.Aile;
import industrie.Avion;
import industrie.ComponentIndustry;
import industrie.Entrepot;
import industrie.Metal;
import industrie.Moteur;
import industrie.Usine;

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


		// On ajoute à la position le delta x et y de la vitesse
		createListUsineSimulation();
		drawUsine();
		//position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);


	}

	private void createListUsineSimulation() {

		String typeUsine;
		int idUsine;
		Usine usineTMP;
		Point position;

		if(this.domParser != null && this.listUsineSimulation.isEmpty()) {

			NodeList usineAttributeList = this.domParser.getUsineAttributeList();

			for(int i = 0; i < usineAttributeList.getLength(); i++) {

				Element elementUsineAttribute = (Element) usineAttributeList.item(i);
				typeUsine = elementUsineAttribute.getAttribute(Usine.FIELD_TYPE);
				idUsine = Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_ID));
				position = new Point(Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_X)),
						Integer.parseInt(elementUsineAttribute.getAttribute(Usine.FIELD_Y)));

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

	private void drawUsine() {

		JLabel labelIcon;
		Point positionLabel;

		//this.listUsine.get(1).getLabelIcon();
		for(int i = 0; i < listUsineSimulation.size(); i++) {

			positionLabel = this.listUsineSimulation.get(i).getPosition();
			labelIcon = this.listUsineSimulation.get(i).getLabelIcon().get(0);
			labelIcon.setBounds(positionLabel.x,positionLabel.y, taille, taille);

			this.add(labelIcon);

		}

		if(this.entrepot != null) {
			positionLabel = this.entrepot.getPosition();
			labelIcon = this.entrepot.getLabelIconList().get(0);
			labelIcon.setBounds(positionLabel.x, positionLabel.y, taille, taille);
			
			this.add(labelIcon);
		}
	}
	public void setDomParser(DomParserProductionLine domParser) {

		if(this.domParser != domParser) {
			this.domParser = domParser;
			createListUsineSimulation();
			setUsineList();
		}
	}

	private void setUsineList() {

		UsineBuilder usineBuilder;
		
		usineBuilder = new UsineBuilder(domParser);
		this.listUsine = usineBuilder.getListUsine();
		this.entrepot = usineBuilder.getEntrepot();

	}
	public ArrayList<Usine> getListUsineSimulation() {

		return (ArrayList<Usine>) listUsineSimulation.clone();
	}

	public DomParserProductionLine getDomParser() {
		return domParser;
	}


}