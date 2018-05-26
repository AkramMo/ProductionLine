package industrie;

import java.util.Observable;
import java.util.Observer;

public class Usine implements Observer, ObjetGraphique {

	
	private String exit;
	private String[] entry;
	private int quantity;
	private ComponentIndustry component;
	private int timeProduction;
	
	public Usine(String exit, String[] entry, int quantity, int timeProduction){
		
		this.exit = exit;
		this.entry = entry;
		this.quantity = quantity;
		this.timeProduction = timeProduction;
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintObject() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
