package simulation;

import javax.swing.SwingWorker;

// TODO: Auto-generated Javadoc
/**
 * The Class Environnement.
 */
public class Environnement extends SwingWorker<Object, String> {
	
	/** The actif. */
	private boolean actif = true;
	
	/** The Constant DELAI. */
	private static final int DELAI = 1;
	
	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			
			
			firePropertyChange("Un tour", null, "Ceci est un tour");
		}
		return null;
	}

}