package industry;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 * The Class Industry.
 * Represent the industry of my production line
 * @author Akram Mousselmal
 * @version 1.0.0
 */
public class Industry implements Observer {

	/** The Constant FIELD_USINE. */
	public static final String FIELD_USINE = "usine";
	
	/** The Constant FIELD_ICONES. */
	public static final String FIELD_ICONES = "icones";
	
	/** The Constant FIELD_ICONE. */
	public static final String FIELD_ICONE = "icone";
	
	/** The Constant FIELD_SORTIE. */
	public static final String FIELD_SORTIE = "sortie";
	
	/** The Constant FIELD_ENTREE. */
	public static final String FIELD_ENTREE = "entree";
	
	/** The Constant FIELD_INTERVAL. */
	public static final String FIELD_INTERVAL = "interval-production";
	
	/** The Constant FIELD_TYPE. */
	public static final String FIELD_TYPE = "type";
	
	/** The Constant FIELD_PATH. */
	public static final String FIELD_PATH = "path";
	
	/** The Constant FIELD_QUANTITE. */
	public static final String FIELD_QUANTITE = "quantite";
	
	/** The Constant FIELD_ID. */
	public static final String FIELD_ID = "id";
	
	/** The Constant FIELD_X. */
	public static final String FIELD_X = "x";
	
	/** The Constant FIELD_Y. */
	public static final String FIELD_Y = "y";



	/** The industry type. */
	private String industryType;
	
	/** The entry list. */
	private ArrayList<ComponentIndustry> entryList;
	
	/** The component out. */
	private ComponentIndustry componentOut;
	
	/** The state production component. */
	private boolean stateProductionComponent = false;
	
	/** The state store. */
	private boolean stateStore = false;
	
	/** The time production. */
	private int timeProduction;
	
	/** The current time. */
	private int currentTime = 0;
	
	/** The id industry. */
	private int idIndustry = 0;
	
	/** The path list. */
	private LinkedList<String> pathList;
	
	/** The image list. */
	private ArrayList<ImageIcon> imageList;
	
	/** The position. */
	private Point position;
	
	/** The state icon. */
	private int stateIcon;
	
	/** The store. */
	private Store store;


	/**
	 * Instantiates a new industry.
	 *
	 * @param industryType the industry type
	 * @param entryList the entry list
	 * @param timeProduction the time production
	 * @param pathList the path list
	 */
	@SuppressWarnings("unchecked")
	public Industry(String industryType, ArrayList<ComponentIndustry> entryList, int timeProduction,
			LinkedList<String> pathList){

		this.industryType = industryType;
		this.entryList = (ArrayList<ComponentIndustry>) entryList.clone();
		this.timeProduction = timeProduction;
		this.componentOut = null;
		this.pathList = (LinkedList<String>) pathList.clone();
		this.imageList = new ArrayList<ImageIcon>();
		this.stateIcon = 0;
		setImageIconList();

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		this.store = (Store) arg0;

		if(this.store.itIsFull() && !this.stateStore) {

			this.timeProduction = this.timeProduction* 5;
			this.stateStore = true;
		}else if(this.stateStore){

			this.timeProduction = this.timeProduction/5;
			this.stateStore = false;
		}

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
	 * Update industry and his states.
	 */
	public void updateIndustry() {

		if(verifyEntry()) {

			updateTime();
		}
	}

	/**
	 * Verify entry has been reached.
	 *
	 * @return true, if successful
	 */
	private boolean verifyEntry() {

		for(int i = 0; i < this.entryList.size(); i++) {

			if(this.entryList.get(i).qtyReached()) {

				continue;
			}else {

				return false;
			}
		}

		return true;
	}

	/**
	 * Update time of production.
	 */
	private void updateTime() {

		if(this.currentTime < this.timeProduction ) {

			this.currentTime++;

			if(this.currentTime <= (timeProduction/3)) {

				this.stateIcon = 0;

			}else if(this.currentTime > (timeProduction/3) && this.currentTime <= ((timeProduction/3)*2)) {

				this.stateIcon = 1;

			}else if( this.currentTime > ((timeProduction/3)*2) && this.currentTime < (timeProduction)){

				this.stateIcon = 2;

			}else {

				this.stateIcon =3;
			}

		}else {

			this.currentTime = 0;
			this.stateProductionComponent = true;
			resetEntry();
		}

	}

	/**
	 * Reset entry to zero.
	 */
	public void resetEntry() {

		for(int i = 0; i < this.entryList.size(); i++) {

			this.entryList.get(i).resetEntry();
		}
	}

	/**
	 * Gets the id industry.
	 *
	 * @return the id industry
	 */
	public int getIdIndustry() {

		return idIndustry;
	}

	/**
	 * Sets the id industry.
	 *
	 * @param idIndustry the new id industry
	 */
	public void setIdIndustry(int idIndustry) {

		this.idIndustry= idIndustry;
	}

	/**
	 * Gets the time production.
	 *
	 * @return the time production
	 */
	public int getTimeProduction() {

		return timeProduction;
	}

	/**
	 * Sets the time production.
	 *
	 * @param timeProduction the new time production
	 */
	public void setTimeProduction(int timeProduction) {

		this.timeProduction = timeProduction;
	}

	/**
	 * Gets the icon by state.
	 *
	 * @return the icon by state
	 */
	public  ImageIcon getIconByState() {

		return  imageList.get(stateIcon);
	}

	/**
	 * Gets the path list.
	 *
	 * @return the path list
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<String> getPathList() {

		return (LinkedList<String>) pathList.clone();
	}

	/**
	 * Gets the entry.
	 *
	 * @return the entry
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ComponentIndustry> getEntry() {

		return (ArrayList<ComponentIndustry>) this.entryList.clone();
	}

	/**
	 * Sets the entry.
	 *
	 * @param entry the new entry
	 */
	@SuppressWarnings("unchecked")
	public void setEntry(ArrayList<ComponentIndustry> entry) {

		this.entryList = (ArrayList<ComponentIndustry>) entry.clone();
	}



	/**
	 * Gets the image icon list.
	 *
	 * @return the image icon list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ImageIcon> getImageIconList() {

		return (ArrayList<ImageIcon>) this.imageList.clone();
	}

	/**
	 * Gets the component out to create.
	 *
	 * @return the component out to create
	 */
	public String getComponentOutToCreate() {

		if(this.stateProductionComponent) {

			this.stateProductionComponent = false;
			this.stateIcon = 0;
			return componentOut.getType();

		}else {

			return null;
		}
	}

	/**
	 * Gets the component type.
	 *
	 * @return the component type
	 */
	public String getComponentType() {

		return componentOut.getType();
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
	 * Sets the component out.
	 *
	 * @param componentOut the new component out
	 */
	public void setComponentOut(ComponentIndustry componentOut) {

		if(componentOut != null) {
			switch (componentOut.getType()) {
			case "metal":
				this.componentOut = new Metal();
				break;
			case "avion":
				this.componentOut = new Plane();
				break;
			case "aile":
				this.componentOut = new Wing();
				break;
			case "moteur":
				this.componentOut = new Engine();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Point position) {

		this.position = position;
	}


	/**
	 * Gets the industry type.
	 *
	 * @return the industry type
	 */
	public String getIndustryType() {

		return industryType;
	}


	/**
	 * Update entry by type.
	 *
	 * @param typeComponent the type component
	 */
	public void updateEntryByType(String typeComponent) {

		for(int i = 0; i < this.entryList.size(); i++) {

			if(this.entryList.get(i).getType().equals(typeComponent) && !this.entryList.get(i).qtyReached()) {

				this.entryList.get(i).updateQuantity();

			}
		}

	}
}
