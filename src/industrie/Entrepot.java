package industrie;

import java.util.Observable;
import java.util.Observer;

public class Entrepot extends Observable implements ObjetGraphique{

	
	private ComponentIndustry component;
	private int capacity;
	
	public Entrepot(ComponentIndustry component, int capacity) {
		
		this.component = component;
		this.capacity = capacity;
	}
	
	@Override
	public void paintObject() {
		// TODO Auto-generated method stub
		
	}

}
