package network;

public class PathIndustry{

	public final static String FIELD_DE = "de";
	public final static String FIELD_VERS = "vers";
	
	private int initialID;
	private int finalID;
	
	public PathIndustry(int initialID, int finalID) {
		
		this.initialID = initialID;
		this.finalID = finalID;
	}

	public int getInitialID() {
		
		return initialID;
	}

	public int getFinalID() {
		
		return finalID;
	}

	public void setInitialID(int initialID) {
		
		this.initialID = initialID;
	}

	public void setFinalID(int finalID) {
		
		this.finalID = finalID;
	}
	
	
}
