package industrie;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import selling.Sales;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Entrepot extends Observable{


	public static final String FIELD_CAPACITY = "capacite";
	public static final String TYPE_USINE = "entrepot";

	//private ArrayList<ComponentIndustry> entryList
	private int capacity;
	private int quantity;
	private int idEntrepot = 0;
	private LinkedList<String> labelPathList;
	private ArrayList<JLabel> labelIconList;
	private Point position;
	private boolean capacityReached;

	public Entrepot( /*ArrayList<ComponentIndustry> entryList, */int capacity,
			LinkedList<String> labelPathList) {

		//this.entryList = entryList;
		this.capacity = capacity;
		this.labelPathList = (LinkedList<String>) labelPathList.clone();
		this.labelIconList = new ArrayList<JLabel>();
		this.capacityReached= false;

		setJLabelList();
	}

	public synchronized void updateStateCapacity(Sales salesStrategy) {


		if(quantity != capacity) {

			quantity++;
			
		}else if(capacityReached) {

			super.setChanged();
			super.notifyObservers();
			capacityReached = false;
		}
		
		if(quantity > 0 && salesStrategy.doASale()) {

				quantity--;
				
				
		}else if(quantity == capacity && capacityReached)

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

	public int getIdEntrepot() {
		return idEntrepot;
	}

	public void setIdEntrepot(int idEntrepot) {
		this.idEntrepot = idEntrepot;
	}

	/*
	public ArrayList<ComponentIndustry> getEntryList() {
		return entryList;
	}
	 */

	public int getCapacity() {
		return capacity;
	}


	public ArrayList<JLabel> getLabelIconList() {
		return labelIconList;
	}


	public Point getPosition() {
		return position;
	}



	public void setPosition(Point position) {
		this.position = position;
	}

}
