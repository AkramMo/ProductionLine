package industrie;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class Entrepot extends Observable{

	
	public static final String FIELD_CAPACITY = "capacite";
	
	private static final String TYPE_USINE = "entrepot";
	private ArrayList<ComponentIndustry> entryList;
	private int capacity;
	private int idEntrepot = 0;
	private ArrayList<JLabel> labelIconList;
	private Point position;
	
	
	public Entrepot( ArrayList<ComponentIndustry> entryList, int capacity,
			ArrayList<JLabel> labelIconList) {
		
		this.entryList = entryList;
		this.capacity = capacity;
		this.labelIconList = labelIconList;
	}
	
	public int getIdEntrepot() {
		return idEntrepot;
	}

	public void setIdEntrepot(int idEntrepot) {
		this.idEntrepot = idEntrepot;
	}


	public ArrayList<ComponentIndustry> getEntryList() {
		return entryList;
	}


	public int getCapacity() {
		return capacity;
	}


	public ArrayList<JLabel> getLabelIconList() {
		return labelIconList;
	}


	public Point getPosition() {
		return position;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public void setPosition(Point position) {
		this.position = position;
	}


	
	

}
