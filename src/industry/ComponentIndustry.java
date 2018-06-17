package industry;

import java.awt.Point;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentIndustry.
 * @author Akram Mousselmal
 * @version 1.0.0
 */
public abstract class  ComponentIndustry {

	/** The speed. */
	private Point speed;
	
	/** The position. */
	private Point position;
	
	/** The desired quantity. */
	private int desiredQuantity = 0;
	
	/** The quantity. */
	private int quantity = 0;
	
	/** The type component. */
	private String typeComponent;
	
	/** The component icon. */
	protected ImageIcon componentIcon = null;

	/**
	 * Instantiates a new component industry.
	 *
	 * @param speed the speed
	 * @param position the position
	 * @param type the type
	 */
	public ComponentIndustry(Point speed,	Point position, String type){

		this.speed = new Point(speed.x, speed.y);
		this.position = new Point(position.x, position.y);
		this.typeComponent = type;

	}

	/**
	 * Gets the component icon.
	 *
	 * @return the component icon
	 */
	public ImageIcon getComponentIcon() {

		return this.componentIcon;
	}

	/**
	 * Sets the speed and position.
	 *
	 * @param initialPos the initial position
	 * @param finalPos the final position
	 */
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
		
		this.speed.x = x;
		this.speed.y = y;

	}

	/**
	 * http://icwww.epfl.ch/~sam/ipo/series/serie03/ex6/files/solution/PGDC.java
	 * Return the smalest common divider between two numbers.
	 * @param x the x
	 * @param y the y
	 * @return the int
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

	/**
	 * Translate the position of the component.
	 */
	public void translatePosition() {

		this.position.translate(this.speed.x, this.speed.y);
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Point getPosition() {

		return position;
	}

	/**
	 * Update quantity.
	 */
	public void updateQuantity() {
		
		if(!qtyReached()) {
			
			this.quantity ++;
		}
	}


	/**
	 * Qty reached.
	 *
	 * @return true, if max quantity reached
	 */
	public boolean qtyReached() {

		return this.desiredQuantity == this.quantity;
	}

	/**
	 * Reset quantity of entry.
	 */
	public void resetEntry() {

		this.quantity = 0;
	}
	
	/**
	 * Sets the desired quantity.
	 *
	 * @param desiredQuantity the new desired quantity
	 */
	public void setDesiredQuantity(int desiredQuantity) {

		this.desiredQuantity = desiredQuantity;
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {

		return this.typeComponent;
	}


}
