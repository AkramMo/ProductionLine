package industry;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Metal extends ComponentIndustry{

	
	private static final String ICONE_PATH = "/ressources/metal.png/";
	
	public Metal() {
		
		super(new Point(0,0), new Point(0,0), "metal");
		
		try {
			BufferedImage classPathImage = ImageIO.read(getClass().getResourceAsStream(ICONE_PATH));
			

			this.labelIcon = new JLabel(new ImageIcon(classPathImage));
			
		} catch (IOException e) {
			
			System.err.println("Icone introuvable !");
		}
	}
	
	@Override
	public JLabel getLabelIcon() {
		
		return this.labelIcon;
	}
}