package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);
	private PanneauPrincipal panneauPrincipal;
	private MenuFenetre menuFenetre;
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("TEST")) {
			

			//Update the main panel if we upload a new file
			updateMainPanel(menuFenetre, panneauPrincipal);
			repaint();
			//System.out.println("");
		}
	}
	
	private void updateMainPanel(MenuFenetre menuFenetre, PanneauPrincipal panneauPrincipal) {
		
		if(menuFenetre.getDomParser() != null) {
			
			panneauPrincipal.setDomParser(menuFenetre.getDomParser());
			panneauPrincipal.setUsineList();
		}
		
	}
}
