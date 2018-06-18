package simulation;

// TODO: Auto-generated Javadoc
/**
 * The Class Simulation.
 */
public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
