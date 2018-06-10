package industry;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Plane extends ComponentIndustry{

	private JLabel labelIcon;
	private static final String ICONE_PATH = "/ressources/avion.png/";
	
	public Plane() {
		
		super(new Point(0,0), new Point(0,0), "avion");
		
		try {
			BufferedImage classPathImage = ImageIO.read(getClass().getResourceAsStream(ICONE_PATH));
			

			this.labelIcon = new JLabel(new ImageIcon(classPathImage));
			
		} catch (IOException e) {
			
			System.err.println("Icone introuvable !");
		}
	}
	
	



	public JLabel getLabelIcon() {
		
		return this.labelIcon;
	}
}