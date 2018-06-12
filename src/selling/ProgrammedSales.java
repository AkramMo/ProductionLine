package selling;

public class ProgrammedSales implements SellingStrategy {

	private int counter;

	public ProgrammedSales() {

		counter = 0;
	}

	@Override
	public boolean saleStrategy() {

		counter++;

		if(counter == 600) {
			
			counter = 0;
			return true;
		}
		return false;
	}
}
