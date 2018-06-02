package simulation;

import java.awt.Graphics;
import javax.swing.JPanel;
import configuration.XMLParserProductionLine;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private XMLParserProductionLine XMLParser;
	private SimulationDrawing simuDrawing;

	public PanneauPrincipal() {

		this.simuDrawing = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawSimulation();
		//g.drawLine(100, 100, 250, 250);
		//position.translate(vitesse.x, vitesse.y);

	}


	private void drawSimulation() {

		if(this.simuDrawing != null) {
			this.simuDrawing.drawUsine(this);
		}

	}


	public void updateMainPanel(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			this.simuDrawing = new SimulationDrawing(this.XMLParser);
		}
	}


}