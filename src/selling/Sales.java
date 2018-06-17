package selling;

// TODO: Auto-generated Javadoc
/**
 * The Class Sales.
 */
public class Sales {

	/** The strategy. */
	private SellingStrategy strategy;
	
	/**
	 * Instantiates a new sales.
	 *
	 * @param strategy the strategy
	 */
	public Sales(SellingStrategy strategy) {
		
		this.strategy = strategy;
	}
	
	/**
	 * Instantiates a new sales.
	 */
	public Sales() {
		
		this.strategy = null;
	}
	
	/**
	 * Sets the sales.
	 *
	 * @param strategy the new sales
	 */
	public void setSales(SellingStrategy strategy) {
		
		this.strategy = strategy;
	}
	
	/**
	 * Do A sale.
	 *
	 * @return true, if successful
	 */
	public boolean doASale() {
		
		return strategy.saleStrategy();
	}
	
	/**
	 * Checks if is not empty.
	 *
	 * @return true, if is not empty
	 */
	public boolean isNotEmpty() {
		
		return this.strategy != null;
	}
}
