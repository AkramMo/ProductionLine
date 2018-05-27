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
		// Faire en sorte que le X de la fen�tre ferme la fen�tre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		// Rendre la fen�tre visible
		setVisible(true);
		// Mettre la fen�tre au centre de l'�cran
		setLocationRelativeTo(null);
		// Emp�cher la redimension de la fen�tre
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
		}
		
	}
}
