package industrie;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Usine implements Observer {

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



	private String typeUsine;
	private ArrayList<ComponentIndustry> entryList;
	private ComponentIndustry componentOut;
	private boolean stateProductionComponent = false;
	private boolean stateEntrepot = false;
	private int timeProduction;
	private int currentTime = 0;
	private int idUsine = 0;
	private LinkedList<String> labelPathList;
	private ArrayList<JLabel> labelIconList;
	private Point position;
	private int stateIcon;
	private boolean stateUsine;
	private Entrepot entrepot;


	public Usine(String typeUsine, ArrayList<ComponentIndustry> entryList, int timeProduction,
			LinkedList<String> labelPathList){

		this.typeUsine = typeUsine;
		this.entryList = (ArrayList<ComponentIndustry>) entryList.clone();
		this.timeProduction = timeProduction;
		this.componentOut = null;
		this.labelPathList = (LinkedList<String>) labelPathList.clone();
		this.labelIconList = new ArrayList<JLabel>();
		this.stateIcon = 0;
		this.stateUsine = true;
		setJLabelList();

	}

	@Override
	public void update(Observable arg0, Object arg1) {

		this.entrepot = (Entrepot) arg0;
		
		if(this.entrepot.itIsFull() && !this.stateEntrepot) {
			
			this.timeProduction = this.timeProduction* 5;
			this.stateEntrepot = true;
		}else if(this.stateEntrepot){
			
			this.timeProduction = this.timeProduction/5;
			this.stateEntrepot = false;
		}

	}

	private void setJLabelList() {

		BufferedImage classPathImage;
		JLabel labelTMP;
		
		for(int i = 0; i < this.labelPathList.size(); i++) {
			
			try {
				classPathImage = ImageIO.read(getClass().getResourceAsStream(this.labelPathList.get(i)));
				labelTMP = new JLabel(new ImageIcon(classPathImage));
				labelIconList.add(labelTMP);

			} catch (IOException e) {

				System.err.println("Icone introuvable !");
			}
		}
	}

	public void updateUsine() {

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

	public int getIdUsine() {
		return idUsine;
	}

	public void setIdUsine(int idUsine) {
		this.idUsine = idUsine;
	}

	public int getTimeProduction() {
		return timeProduction;
	}

	public void setTimeProduction(int timeProduction) {
		this.timeProduction = timeProduction;
	}

	public  JLabel getLabelIcon() {

		if(stateIcon == 3) {
			int x = 2;
		}
		return  labelIconList.get(stateIcon);

	}

	public LinkedList<String> getLabelPathList() {

		return (LinkedList<String>) labelPathList.clone();
	}

	public ArrayList<ComponentIndustry> getEntry() {

		return (ArrayList<ComponentIndustry>) this.entryList.clone();
	}

	public void setEntry(ArrayList<ComponentIndustry> entry) {

		this.entryList = (ArrayList<ComponentIndustry>) entry.clone();
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
				this.componentOut = new Avion();
				break;
			case "aile":
				this.componentOut = new Aile();
				break;
			case "moteur":
				this.componentOut = new Moteur();
				break;
			default:
				break;
			}
		}
	}

	public void setPosition(Point position) {
		this.position = position;
	}


	public String getTypeUsine() {
		return typeUsine;
	}

	
	public void updateEntryByType(String typeComponent) {
		
		for(int i = 0; i < this.entryList.size(); i++) {
			
			if(this.entryList.get(i).getType().equals(typeComponent) && !this.entryList.get(i).qtyReached()) {
				
				this.entryList.get(i).updateQuantity();
				
			}
		}
		
	}




}
