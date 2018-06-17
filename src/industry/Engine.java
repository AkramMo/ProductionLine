package industry;

import java.awt.Point;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class Engine.
 * @author Akram Mousselmal
 * @version 1.0.0
 */
public class Engine extends ComponentIndustry{

	/** The Constant ICONE_PATH. */
	private static final String ICONE_PATH = "src/ressources/moteur.png/";
	
	/**
	 * Instantiates a new engine.
	 */
	public Engine() {
		
		super(new Point(0,0), new Point(0,0), "moteur");
		this.componentIcon = new ImageIcon(ICONE_PATH);
		}

		/* (non-Javadoc)
		 * @see industry.ComponentIndustry#getComponentIcon()
		 */
		@Override
		public ImageIcon getComponentIcon() {

			return this.componentIcon;
		}
}
