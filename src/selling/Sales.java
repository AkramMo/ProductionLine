package selling;

public class Sales {

	private SellingStrategy strategy;
	
	public Sales(SellingStrategy strategy) {
		
		this.strategy = strategy;
	}
	
	public void doASale() {
		
		strategy.saleStrategy();
	}
}
