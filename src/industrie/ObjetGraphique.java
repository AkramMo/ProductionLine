package industrie;

import java.awt.Graphics;
import java.awt.Point;

public interface ObjetGraphique {

	public int idObjetGraphique = 0;
	public String[] pathIcone = new String[4];
	public Point position = new Point();
	
	public void paintObject();
}
