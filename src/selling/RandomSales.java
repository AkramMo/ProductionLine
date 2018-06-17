package selling;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomSales.
 */
public class RandomSales implements SellingStrategy {
	
	
	/**
	 * Instantiates a new random sales.
	 */
	public RandomSales() {
		
		
	}
	
	/* (non-Javadoc)
	 * @see selling.SellingStrategy#saleStrategy()
	 */
	@Override
	public boolean saleStrategy() {
		
		// Number not significant
		int x = 12;
		
		Random rdm = new Random();
		return x == rdm.nextInt(1150);
	}
}
