package equipment;

public class Shield extends Protection{
	public int boost;
	
	public Shield(int b){ boost = b; }
	@Override
	public int getStrengthBoost() {
		return boost;
	}
}
