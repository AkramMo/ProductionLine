package industrie;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public abstract class  ComponentIndustry implements typeIndustry{

	private Point vitesse;
	private Point position;
	private int desiredQuantity = 0;
	private int desiredCapacity = 0;
	private int quantity = 0;
	private String typeComponent;
	protected JLabel labelIcon = null;

	public ComponentIndustry(Point vitesse,	Point position, String type){

		this.vitesse = new Point(vitesse.x, vitesse.y);
		this.position = new Point(position.x, position.y);
		this.typeComponent = type;

	}

	public JLabel getLabelIcon() {

		return this.labelIcon;
	}

	public void setVitesseAndPosition(Point initialPos, Point finalPos) {

		int x = Math.abs(finalPos.x - initialPos.x);
		int y = Math.abs(finalPos.y - initialPos.y);

		this.position.x = initialPos.x;
		this.position.y = initialPos.y;
		if( x == 0) {

			y = 1;
		}else if( y == 0) {

			x = 1;
		}else {
			x = x/commonDivider(x, y);
			y = y/commonDivider(x, y);
		}
		this.vitesse.x = x;
		this.vitesse.y = y;

	}

	/**
	 * http://icwww.epfl.ch/~sam/ipo/series/serie03/ex6/files/solution/PGDC.java
	 * @param x
	 * @param y
	 * @return
	 */
	private int commonDivider(int x, int y) {

		while (x != y) {
			if (x > y) {
				x = x - y;
			} else {
				y = y - x;
			}
		}

		return x;
	}

	public void translatePosition() {

		this.position.translate(this.vitesse.x, this.vitesse.y);
	}

	public Point getVitesse() {
		return vitesse;
	}

	public Point getPosition() {
		return position;
	}

	public void setVitesse(Point vitesse) {
		this.vitesse = vitesse;
	}



	public boolean qtyReached() {

		return this.desiredQuantity == this.quantity;
	}

	public void resetEntry() {

		this.quantity = 0;
	}
	public void setDesiredQuantity(int desiredQuantity) {

		this.desiredQuantity = desiredQuantity;
	}

	public void setDesiredCapacity(int desiredCapacity) {

		this.desiredCapacity = desiredCapacity;
	}


	@Override
	public String getType() {

		return this.typeComponent;
	}


}
