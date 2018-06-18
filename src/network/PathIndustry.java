package network;

// TODO: Auto-generated Javadoc
/**
 * The Class PathIndustry.
 * Represent a path between two building
 */
public class PathIndustry{

	/** The Constant FIELD_DE. */
	public final static String FIELD_DE = "de";
	
	/** The Constant FIELD_VERS. */
	public final static String FIELD_VERS = "vers";
	
	/** The initial ID. */
	private int initialID;
	
	/** The final ID. */
	private int finalID;
	
	/**
	 * Instantiates a new path industry.
	 *
	 * @param initialID the initial ID
	 * @param finalID the final ID
	 */
	public PathIndustry(int initialID, int finalID) {
		
		this.initialID = initialID;
		this.finalID = finalID;
	}

	/**
	 * Gets the initial ID.
	 *
	 * @return the initial ID
	 */
	public int getInitialID() {
		
		return initialID;
	}

	/**
	 * Gets the final ID.
	 *
	 * @return the final ID
	 */
	public int getFinalID() {
		
		return finalID;
	}

	/**
	 * Sets the initial ID.
	 *
	 * @param initialID the new initial ID
	 */
	public void setInitialID(int initialID) {
		
		this.initialID = initialID;
	}

	/**
	 * Sets the final ID.
	 *
	 * @param finalID the new final ID
	 */
	public void setFinalID(int finalID) {
		
		this.finalID = finalID;
	}
	
	
}
