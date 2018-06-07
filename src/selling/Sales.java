package selling;

public class Sales {

	private SellingStrategy strategy;
	
	public Sales(SellingStrategy strategy) {
		
		this.strategy = strategy;
	}
	
	public Sales() {
		
		this.strategy = null;
	}
	
	public void setSales(SellingStrategy strategy) {
		
		this.strategy = strategy;
	}
	
	public void doASale() {
		
		strategy.saleStrategy();
	}
	
	public boolean isNotEmpty() {
		
		if(this.strategy != null) {
			
			return true;
		}else {
			
			return false;
		}
		
		
	}
}
