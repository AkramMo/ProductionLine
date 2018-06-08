package simulation;

import java.awt.Graphics;
import javax.swing.JPanel;
import configuration.XMLParserProductionLine;
import selling.Sales;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private XMLParserProductionLine XMLParser;
	private SimulationDrawing simuDrawing;
	private Sales SalesStrategy;

	public PanneauPrincipal() {

		this.simuDrawing = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawSimulation(g);
		
		//position.translate(vitesse.x, vitesse.y);

	}


	private void drawSimulation(Graphics g) {

		if(this.simuDrawing != null) {
			this.simuDrawing.drawUsine(this);
			this.simuDrawing.drawPath(g);

			if(salesNotEmpty()){
				
			this.simuDrawing.updateUsine();
			}
		}

	}


	public void updateMainPanel(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			this.simuDrawing = new SimulationDrawing(this.XMLParser);
		}else {
			
			this.simuDrawing.updateListComponent();
		}
	}

	public void setSalesStrategy(Sales salesStrategy) {
		
		this.SalesStrategy = salesStrategy;
		System.out.println("Strategy set");
	}

	
	public boolean salesNotEmpty() {
		
		return this.SalesStrategy.isNotEmpty();
	}
	
	
}