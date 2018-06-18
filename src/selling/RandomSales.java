package selling;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomSales.
 * Do sales with a probality of 1/1150 
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
		
		// return true if rdm.nextInt equals 12
		return x == rdm.nextInt(1150);
	}
}
