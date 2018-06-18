package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class FenetrePrincipale.
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant TITRE_FENETRE. */
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	
	/** The Constant DIMENSION. */
	private static final Dimension DIMENSION = new Dimension(700, 700);
	
	/** The panneau principal. */
	private PanneauPrincipal panneauPrincipal;
	
	/** The menu fenetre. */
	private MenuFenetre menuFenetre;
	
	/** The program state. */
	private boolean programState = false;
	
	/**
	 * Instantiates a new fenetre principale.
	 */
	public FenetrePrincipale() {
		this.panneauPrincipal = new PanneauPrincipal();
		this.menuFenetre = new MenuFenetre();

		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);
		// Faire en sorte que le X de la fenêtre ferme la fenêtre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		// Rendre la fenêtre visible
		setVisible(true);
		// Mettre la fenêtre au centre de l'écran
		setLocationRelativeTo(null);
		// Empêcher la redimension de la fenêtre
		setResizable(false);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Un tour")) {

			updateMainPanel(menuFenetre, panneauPrincipal);
			repaint();
		}
	}

	/**
	 * Update main panel.
	 *
	 * @param menuFenetre the menu fenetre
	 * @param panneauPrincipal the panneau principal
	 */
	private void updateMainPanel(MenuFenetre menuFenetre, PanneauPrincipal panneauPrincipal) {

		if(menuFenetre.getXMLParser() != null && !programState ) {

			panneauPrincipal.updateMainPanel(menuFenetre.getXMLParser());
			if(menuFenetre.isSalesSet()) {

				panneauPrincipal.setSalesStrategy(menuFenetre.getSalesStrategy());
				programState = true;
			}
		}else if(programState){

			panneauPrincipal.updateMainPanel();
		}
	}
}
