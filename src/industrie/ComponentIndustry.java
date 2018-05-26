package industrie;

import java.util.Observable;
import java.util.Observer;

public class ComponentIndustry implements Observer, ObjetGraphique{

	private String componentType;
	
	public ComponentIndustry(String componentType) {
		
		this.componentType = componentType;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintObject() {
		// TODO Auto-generated method stub
		
	}

}
