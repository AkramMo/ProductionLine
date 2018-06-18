package simulation;

import java.awt.Dimension;

import javax.swing.JFrame;

import selling.Sales;

// TODO: Auto-generated Javadoc
/**
 * The Class FenetreStrategie.
 */
public class FenetreStrategie extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant TITRE_FENETRE. */
	private static final String TITRE_FENETRE = "Sélectionnez votre stratégie de vente";
	
	/** The Constant DIMENSION. */
	private static final Dimension DIMENSION = new Dimension(250, 100);
	
	/** The panneau strategie. */
	private PanneauStrategie panneauStrategie;

	/**
	 * Instantiates a new fenetre strategie.
	 *
	 * @param salesStrategy the sales strategy
	 */
	public FenetreStrategie(Sales salesStrategy) {
		this.panneauStrategie = new PanneauStrategie(salesStrategy);
		add(panneauStrategie);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
}
