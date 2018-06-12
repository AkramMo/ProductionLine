package industry;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Industry implements Observer {

	public static final String FIELD_USINE = "usine";
	public static final String FIELD_ICONES = "icones";
	public static final String FIELD_ICONE = "icone";
	public static final String FIELD_SORTIE = "sortie";
	public static final String FIELD_ENTREE = "entree";
	public static final String FIELD_INTERVAL = "interval-production";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_PATH = "path";
	public static final String FIELD_QUANTITE = "quantite";
	public static final String FIELD_ID = "id";
	public static final String FIELD_X = "x";
	public static final String FIELD_Y = "y";



	private String industryType;
	private ArrayList<ComponentIndustry> entryList;
	private ComponentIndustry componentOut;
	private boolean stateProductionComponent = false;
	private boolean stateStore = false;
	private int timeProduction;
	private int currentTime = 0;
	private int idIndustry = 0;
	private LinkedList<String> labelPathList;
	private ArrayList<ImageIcon> imageList;
	private Point position;
	private int stateIcon;
	private Store store;


	@SuppressWarnings("unchecked")
	public Industry(String industryType, ArrayList<ComponentIndustry> entryList, int timeProduction,
			LinkedList<String> labelPathList){

		this.industryType = industryType;
		this.entryList = (ArrayList<ComponentIndustry>) entryList.clone();
		this.timeProduction = timeProduction;
		this.componentOut = null;
		this.labelPathList = (LinkedList<String>) labelPathList.clone();
		this.imageList = new ArrayList<ImageIcon>();
		this.stateIcon = 0;
		setImageIconList();

	}

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


	private void setImageIconList(){

		ImageIcon imageTMP;

		for(int i = 0; i < this.labelPathList.size(); i++) {

			imageTMP = new ImageIcon(this.labelPathList.get(i));

			this.imageList.add(imageTMP);
		}
	}
	
	public void updateIndustry() {

		if(verifyEntry()) {

			updateTime();
		}
	}

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

	public void resetEntry() {

		for(int i = 0; i < this.entryList.size(); i++) {

			this.entryList.get(i).resetEntry();
		}
	}

	public int getIdIndustry() {

		return idIndustry;
	}

	public void setIdIndustry(int idIndustry) {

		this.idIndustry= idIndustry;
	}

	public int getTimeProduction() {

		return timeProduction;
	}

	public void setTimeProduction(int timeProduction) {

		this.timeProduction = timeProduction;
	}

	public  ImageIcon getIconByState() {

		return  imageList.get(stateIcon);
	}

	@SuppressWarnings("unchecked")
	public LinkedList<String> getLabelPathList() {

		return (LinkedList<String>) labelPathList.clone();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ComponentIndustry> getEntry() {

		return (ArrayList<ComponentIndustry>) this.entryList.clone();
	}

	@SuppressWarnings("unchecked")
	public void setEntry(ArrayList<ComponentIndustry> entry) {

		this.entryList = (ArrayList<ComponentIndustry>) entry.clone();
	}



	@SuppressWarnings("unchecked")
	public ArrayList<ImageIcon> getImageIconList() {

		return (ArrayList<ImageIcon>) this.imageList.clone();
	}

	public String getComponentOutToCreate() {

		if(this.stateProductionComponent) {

			this.stateProductionComponent = false;
			this.stateIcon = 0;
			return componentOut.getType();

		}else {

			return null;
		}
	}

	public String getComponentType() {

		return componentOut.getType();
	}

	public Point getPosition() {

		return position;
	}

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

	public void setPosition(Point position) {

		this.position = position;
	}


	public String getIndustryType() {

		return industryType;
	}


	public void updateEntryByType(String typeComponent) {

		for(int i = 0; i < this.entryList.size(); i++) {

			if(this.entryList.get(i).getType().equals(typeComponent) && !this.entryList.get(i).qtyReached()) {

				this.entryList.get(i).updateQuantity();

			}
		}

	}
}
