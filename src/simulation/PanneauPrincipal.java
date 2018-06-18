package simulation;

import java.awt.Graphics;
import javax.swing.JPanel;
import configuration.XMLParserProductionLine;
import selling.Sales;

// TODO: Auto-generated Javadoc
/**
 * The Class PanneauPrincipal.
 */
public class PanneauPrincipal extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The XML parser. */
	private XMLParserProductionLine XMLParser;
	
	/** The simulation process. */
	private SimulationProcess simulationProcess;
	
	/** The sales strategy. */
	private Sales salesStrategy;

	/**
	 * Instantiates a new panneau principal.
	 */
	public PanneauPrincipal() {

		this.simulationProcess = null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawSimulation(g);
		repaint();
	}


	/**
	 * Draw simulation.
	 *
	 * @param g the g
	 */
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



	/**
	 * Update main panel.
	 *
	 * @param XMLParser the XML parser
	 */
	public void updateMainPanel(XMLParserProductionLine XMLParser) {

		if(this.XMLParser != XMLParser) {

			this.XMLParser = XMLParser;
			this.simulationProcess = new SimulationProcess(this.XMLParser);
		}else if(this.salesNotEmpty()){

			this.simulationProcess.updateListComponent(this.salesStrategy);
		}
	}



	/**
	 * Update main panel.
	 */
	public void updateMainPanel() {

		if(this.salesNotEmpty()) {
			this.simulationProcess.updateListComponent(this.salesStrategy);
		}
	}
	
	/**
	 * Sets the sales strategy.
	 *
	 * @param salesStrategy the new sales strategy
	 */
	public void setSalesStrategy(Sales salesStrategy) {

		this.salesStrategy = salesStrategy;
	}


	/**
	 * Sales not empty.
	 *
	 * @return true, if successful
	 */
	public boolean salesNotEmpty() {

		return this.salesStrategy != null;
	}


}