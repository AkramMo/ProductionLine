package industry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import selling.Sales;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class Store
 * @author Akram Mousselmal
 * @version 1.0.0.
 */
public class Store extends Observable{


	/** The Constant FIELD_CAPACITY. */
	public static final String FIELD_CAPACITY = "capacite";
	
	/** The Constant TYPE_USINE. */
	public static final String TYPE_USINE = "entrepot";
	
	/** The capacity. */
	private int capacity;
	
	/** The quantity. */
	private int quantity;
	
	/** The id store. */
	private int idStore = 0;
	
	/** The path list. */
	private LinkedList<String> pathList;
	
	/** The image list. */
	private ArrayList<ImageIcon> imageList;
	
	/** The position. */
	private Point position;
	
	/** The state icon. */
	private int stateIcon = 0;

	/**
	 * Instantiates a new store.
	 *
	 * @param capacity the capacity
	 * @param pathList the path list
	 */
	@SuppressWarnings("unchecked")
	public Store( int capacity,LinkedList<String> pathList) {

		//this.entryList = entryList;
		this.capacity = capacity;
		this.pathList = (LinkedList<String>) pathList.clone();
		this.imageList = new ArrayList<ImageIcon>();
		setImageIconList();
	}

	/**
	 * Sets the quantity.
	 */
	public synchronized void setQuantity() {

		if(this.quantity != capacity) {

			this.quantity++;
			updateIcon();
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Update icon.
	 */
	public void updateIcon() {
			
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
	
	/**
	 * Do A sales.
	 *
	 * @param salesStrategy the sales strategy
	 */
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

	/**
	 * It is full.
	 *
	 * @return true, if successful
	 */
	public boolean itIsFull() {

		return this.capacity == this.quantity;
	}


	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		
		return this.quantity;
	}

	
	/**
	 * Sets the image icon list.
	 */
	private void setImageIconList(){

		ImageIcon imageTMP;

		for(int i = 0; i < this.pathList.size(); i++) {

			imageTMP = new ImageIcon(this.pathList.get(i));

			this.imageList.add(imageTMP);
		}
	}

	/**
	 * Gets the id store.
	 *
	 * @return the id store
	 */
	public int getIdStore() {
		
		return idStore;
	}

	/**
	 * Sets the id store.
	 *
	 * @param idStore the new id store
	 */
	public void setIdStore(int idStore) {
		
		this.idStore = idStore;
	}

	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}


	/**
	 * Gets the icon by state.
	 *
	 * @return the icon by state
	 */
	public ImageIcon getIconByState() {
		
		return imageList.get(this.stateIcon);
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
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

}
