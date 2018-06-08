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

	public ComponentIndustry(Point vitesse,	Point position, String type){

		this.vitesse = new Point(vitesse.x, vitesse.y);
		this.position = new Point(position.x, position.y);
		this.typeComponent = type;

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

	public void setPosition(Point position) {
		this.position = position;
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
