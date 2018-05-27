package industrie;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class ComponentIndustry{

	private Point vitesse;
	private Point position;
	private int desiredQuantity = 0;
	private int desiredCapacity;
	private int quantity = 0;
	
	public ComponentIndustry(Point vitesse,	Point position){

		this.vitesse = vitesse;
		this.position = position;

	}
	
	public int getDesiredCapacity() {
		return desiredCapacity;
	}



	public void setDesiredCapacity(int desiredCapacity) {
		this.desiredCapacity = desiredCapacity;
	}
	
	public int getQuantity() {
		
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}
	
	public int getDesiredQuantity() {
		return desiredQuantity;
	}

	public void setDesiredQuantity(int desiredQuantity) {
		this.desiredQuantity = desiredQuantity;
	}

	
}
