package simulation;

import java.awt.Graphics;
import javax.swing.JPanel;
import configuration.XMLParserProductionLine;
import selling.Sales;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private XMLParserProductionLine XMLParser;
	private SimulationProcess simulationProcess;
	private Sales salesStrategy;
	private boolean pathIsDrawn = false;

	public PanneauPrincipal() {

		this.simulationProcess = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawSimulation(g);
	}


	private void drawSimulation(Graphics g) {

		if(this.simulationProcess != null) {

			this.removeAll();
			this.simulationProcess.drawPath(g);
			this.simulationProcess.drawIndustry(g, this);

			if(salesNotEmpty()){

				this.simulationProcess.updateIndustry();
			}
		}

	}



	public void updateMainPanel(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			this.simulationProcess = new SimulationProcess(this.XMLParser);
		}else if(this.salesNotEmpty()){

			this.simulationProcess.updateListComponent(this.salesStrategy);
		}
	}

	public void setSalesStrategy(Sales salesStrategy) {

		this.salesStrategy = salesStrategy;
	}


	public boolean salesNotEmpty() {

		return this.salesStrategy.isNotEmpty();
	}


}