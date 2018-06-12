package industry;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class  ComponentIndustry {

	private Point speed;
	private Point position;
	private int desiredQuantity = 0;
	private int quantity = 0;
	private String typeComponent;
	protected ImageIcon componentIcon = null;

	public ComponentIndustry(Point speed,	Point position, String type){

		this.speed = new Point(speed.x, speed.y);
		this.position = new Point(position.x, position.y);
		this.typeComponent = type;

	}

	public ImageIcon getComponentIcon() {

		return this.componentIcon;
	}

	public void setSpeedAndPosition(Point initialPos, Point finalPos) {

		int x = finalPos.x - initialPos.x;
		int y = finalPos.y - initialPos.y;
		int commonDivider;

		this.position.x = initialPos.x;
		this.position.y = initialPos.y;

		if( x == 0) {

			y = 1;
		}else if( y == 0) {

			x = 1;
		}else {
			commonDivider = commonDivider(Math.abs(x), Math.abs(y));
			x = x/commonDivider;
			y = y/commonDivider;
		}
		
		this.speed.x = x*2;
		this.speed.y = y*2;

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

		this.position.translate(this.speed.x, this.speed.y);
	}

	public Point getPosition() {

		return position;
	}

	public void updateQuantity() {
		
		if(!qtyReached()) {
			
			this.quantity ++;
		}
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


	public String getType() {

		return this.typeComponent;
	}


}
