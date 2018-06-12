package industry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import selling.Sales;
import javax.swing.ImageIcon;

public class Store extends Observable{


	public static final String FIELD_CAPACITY = "capacite";
	public static final String TYPE_USINE = "entrepot";
	
	private int capacity;
	private int quantity;
	private int idStore = 0;
	private LinkedList<String> labelPathList;
	private ArrayList<ImageIcon> imageList;
	private Point position;
	private int stateIcon = 0;

	@SuppressWarnings("unchecked")
	public Store( int capacity,LinkedList<String> labelPathList) {

		//this.entryList = entryList;
		this.capacity = capacity;
		this.labelPathList = (LinkedList<String>) labelPathList.clone();
		this.imageList = new ArrayList<ImageIcon>();
		setImageIconList();
	}

	public synchronized void setQuantity() {

		if(this.quantity != capacity) {

			this.quantity++;
			updateLabel();
			setChanged();
			notifyObservers();
		}
	}

	public void updateLabel() {
			
			if(this.quantity <= (capacity/3)) {

				this.stateIcon = 0;

			}else if(this.quantity > (capacity/3) && this.quantity <= ((capacity/3)*2)) {

				this.stateIcon = 1;

			}else if( this.quantity > ((capacity/3)*2) && this.quantity < (capacity)){

				this.stateIcon = 2;

			}else {

				this.stateIcon =3;
			}
	}
	
	public synchronized void doASales(Sales salesStrategy) {

		if(salesStrategy != null ) {
			if(salesStrategy.doASale() && quantity > 0) {
				
				System.out.println("Une vente a été effectué ! ");
				quantity--;
				setChanged();
				notifyObservers();
			}
		}
	}

	public boolean itIsFull() {

		return this.capacity == this.quantity;
	}


	public int getQuantity() {
		
		return this.quantity;
	}

	
	private void setImageIconList(){

		ImageIcon imageTMP;

		for(int i = 0; i < this.labelPathList.size(); i++) {

			imageTMP = new ImageIcon(this.labelPathList.get(i));

			this.imageList.add(imageTMP);
		}
	}

	public int getIdStore() {
		
		return idStore;
	}

	public void setIdStore(int idStore) {
		
		this.idStore = idStore;
	}

	public int getCapacity() {
		return capacity;
	}


	public ImageIcon getIconByState() {
		
		return imageList.get(this.stateIcon);
	}


	public Point getPosition() {
		return position;
	}



	public void setPosition(Point position) {
		this.position = position;
	}

}
