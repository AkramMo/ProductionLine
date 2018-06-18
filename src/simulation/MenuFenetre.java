package simulation;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import configuration.XMLParserProductionLine;
import selling.Sales;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuFenetre.
 */
public class MenuFenetre extends JMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MENU_FICHIER_TITRE. */
	private static final String MENU_FICHIER_TITRE = "Fichier";
	
	/** The Constant MENU_FICHIER_CHARGER. */
	private static final String MENU_FICHIER_CHARGER = "Charger";
	
	/** The Constant MENU_FICHIER_QUITTER. */
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	
	/** The Constant MENU_SIMULATION_TITRE. */
	private static final String MENU_SIMULATION_TITRE = "Simulation";
	
	/** The Constant MENU_SIMULATION_CHOISIR. */
	private static final String MENU_SIMULATION_CHOISIR = "Choisir";
	
	/** The Constant MENU_AIDE_TITRE. */
	private static final String MENU_AIDE_TITRE = "Aide";
	
	/** The Constant MENU_AIDE_PROPOS. */
	private static final String MENU_AIDE_PROPOS = "� propos de...";
	
	/** The XML parser. */
	private XMLParserProductionLine XMLParser;
	
	/** The Sales strategy. */
	private Sales SalesStrategy = new Sales();

	/**
	 * Instantiates a new menu fenetre.
	 */
	public MenuFenetre() {
		ajouterMenuFichier();
		ajouterMenuSimulation();
		ajouterMenuAide();
	}

	/**
	 * Cr�er le menu de Fichier.
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("S�lectionnez un fichier de configuration");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Cr�er un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				this.XMLParser = new XMLParserProductionLine(selectedFile);
				
			}
		});
		
		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		menuFichier.add(menuCharger);
		menuFichier.add(menuQuitter);
		
		add(menuFichier);

	}

	/**
	 * Cr�er le menu de Simulation.
	 */
	private void ajouterMenuSimulation() {
		
		
		JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);
		JMenuItem menuChoisir = new JMenuItem(MENU_SIMULATION_CHOISIR);
		menuSimulation.add(menuChoisir);

		menuChoisir.addActionListener((ActionEvent e) -> {
			// Ouvrir la fen�tre de s�lection
			
			
			@SuppressWarnings("unused")
			FenetreStrategie fenetreStrategy = new FenetreStrategie(this.SalesStrategy);
			
		
		});
		add(menuSimulation);

	}


	/**
	 * Cr�er le menu Aide.
	 */
	private void ajouterMenuAide() {
		JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
		JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
		menuAide.add(menuPropos);

		menuPropos.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>" + "<br>"
							+ "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>");
		});
		add(menuAide);
	}
	
	/**
	 * Gets the XML parser.
	 *
	 * @return the XML parser
	 */
	public XMLParserProductionLine getXMLParser() {
		
		return this.XMLParser;
	}
	
	/**
	 * Gets the sales strategy.
	 *
	 * @return the sales strategy
	 */
	public Sales getSalesStrategy() {
		
		return SalesStrategy;
	}
	
	/**
	 * Checks if is sales set.
	 *
	 * @return true, if is sales set
	 */
	public boolean isSalesSet() {
		
		return this.SalesStrategy.isNotEmpty();
	}

	

}
