package simulation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import selling.ProgrammedSales;
import selling.RandomSales;
import selling.Sales;

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;
	private Sales salesStrategy;

	public PanneauStrategie(Sales salesStrategy) {

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Stratégie 1");
		JRadioButton strategie2 = new JRadioButton("Stratégie 2");	
		this.salesStrategy = salesStrategy;
		
		JButton boutonConfirmer = new JButton("Confirmer");

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			
			System.out.println("La stratégie de vente choisie est la " + getSelectedButtonText(groupeBoutons));
			
			if(getSelectedButtonText(groupeBoutons).equals("Stratégie 1")) {
				
				this.salesStrategy.setSales(new RandomSales());
				
			}else {
				
				this.salesStrategy.setSales(new ProgrammedSales());
			}
			
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);		
		add(strategie1);
		add(strategie2);		
		add(boutonConfirmer);
		add(boutonAnnuler);

	}

	/**
	 * Retourne le bouton sélectionné dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}
	
	public Sales getSalesStrategy() {
		
		return this.salesStrategy;
	}

}
