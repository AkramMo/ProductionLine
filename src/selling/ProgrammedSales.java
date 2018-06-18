package selling;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgrammedSales.
 * Class that do sales steadily
 */
public class ProgrammedSales implements SellingStrategy {

	/** The counter. */
	private int counter;

	/**
	 * Instantiates a new programmed sales.
	 */
	public ProgrammedSales() {

		counter = 0;
	}

	/* (non-Javadoc)
	 * @see selling.SellingStrategy#saleStrategy()
	 */
	@Override
	public boolean saleStrategy() {

		counter++;

		//Return true after 600 calls
		if(counter == 600) {
			
			counter = 0;
			return true;
		}
		return false;
	}
}
