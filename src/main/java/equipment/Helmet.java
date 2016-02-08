package equipment;

public class Helmet extends Protection{
	public int boost;
	
	public Helmet(int b){ boost = b; }
	@Override
	public int getStrengthBoost() {
		return boost;
	}
}
