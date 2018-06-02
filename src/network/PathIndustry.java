package network;

public class PathIndustry{

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
