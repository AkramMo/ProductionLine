package industry;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Engine extends ComponentIndustry{

	private static final String ICONE_PATH = "src/ressources/moteur.png/";
	
	public Engine() {
		
		super(new Point(0,0), new Point(0,0), "moteur");
		this.componentIcon = new ImageIcon(ICONE_PATH);
		}

		@Override
		public ImageIcon getComponentIcon() {

			return this.componentIcon;
		}
}
