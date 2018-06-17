package industry;

import java.awt.Point;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class Wing.
 * @author Akram Mousselmal
 * @version 1.0.0
 */
public class Wing extends ComponentIndustry{

	/** The Constant ICONE_PATH. */
	private static final String ICONE_PATH = "src/ressources/aile.png/";
	
	/**
	 * Instantiates a new wing.
	 */
	public Wing() {
		
		super(new Point(0,0), new Point(0,0), "aile");
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
