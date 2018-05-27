package industrie;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
	
	public int getIdUsine() {
		return idUsine;
	}

	public void setIdUsine(int idUsine) {
		this.idUsine = idUsine;
	}

	private String typeUsine;
	private ArrayList<ComponentIndustry> entryList;
	private ComponentIndustry componentOut;
	private int timeProduction;
	private int idUsine = 0;
	private ArrayList<JLabel> labelIconList;
	private Point position;
	
	
	public Usine(String typeUsine, ArrayList<ComponentIndustry> entryList, int timeProduction,
			ArrayList<JLabel> labelIconList){
		
		this.typeUsine = typeUsine;
		this.entryList = entryList;
		this.timeProduction = timeProduction;
		this.componentOut = null;
		this.labelIconList = labelIconList;
		
	}
	
	public JLabel getLabelIcon(int iconPosition) {
		
		return labelIconList.get(iconPosition);
		
	}

	public List<ComponentIndustry> getEntry() {
		return entryList;
	}

	public void setEntry(ArrayList<ComponentIndustry> entry) {
		this.entryList = entry;
	}

	public ComponentIndustry getComponentOut() {
		return componentOut;
	}

	public Point getPosition() {
		return position;
	}

	public void setComponentOut(ComponentIndustry componentOut) {
		this.componentOut = componentOut;
	}

	public void setPosition(Point position) {
		this.position = position;
	}


	public String getTypeUsine() {
		return typeUsine;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
	

}
