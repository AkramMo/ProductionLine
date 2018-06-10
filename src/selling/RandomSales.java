package selling;

import java.util.Random;

public class RandomSales implements SellingStrategy {
	
	
	public RandomSales() {
		
		
	}
	@Override
	public boolean saleStrategy() {
		int x = 12;
		
		Random rdm = new Random();
		
		
		return x == rdm.nextInt(250);
	}

}
