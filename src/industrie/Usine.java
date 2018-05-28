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
	private int timeProduction;
	private int idUsine = 0;
	private LinkedList<String> labelPathList;
	private ArrayList<JLabel> labelIconList;
	private JLabel currentIcone;
	private Point position;
	private int stateIcon;
	private boolean stateUsine;


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

		this.stateUsine = !this.stateUsine;

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

	public ArrayList<JLabel> getLabelIcon() {

		return (ArrayList<JLabel>) labelIconList.clone();

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





}
