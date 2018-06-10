package simulation;

import java.awt.Graphics;
import javax.swing.JPanel;
import configuration.XMLParserProductionLine;
import selling.Sales;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private XMLParserProductionLine XMLParser;
	private SimulationDrawing simuDrawing;
	private Sales salesStrategy;

	public PanneauPrincipal() {

		this.simuDrawing = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawSimulation(g);
		//position.translate(vitesse.x, vitesse.y);
		repaint();
	}


	private void drawSimulation(Graphics g) {

		if(this.simuDrawing != null) {

			this.simuDrawing.drawPath(g);
			this.simuDrawing.drawUsine(this);

			if(salesNotEmpty()){
				
			this.simuDrawing.updateUsine();
			}
		}

	}

	

	public void updateMainPanel(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			this.simuDrawing = new SimulationDrawing(this.XMLParser);
		}else if(this.salesNotEmpty()){
			
			this.simuDrawing.updateListComponent(this.salesStrategy);
			
		}
	}

	public void setSalesStrategy(Sales salesStrategy) {
		
		this.salesStrategy = salesStrategy;
	
	}

	
	public boolean salesNotEmpty() {
		
		return this.salesStrategy.isNotEmpty();
	}
	
	
}