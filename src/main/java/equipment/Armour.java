package equipment;

public class Armour extends Protection{
	public int boost;
	
	public Armour(int b){ boost = b; }
	@Override
	public int getStrengthBoost() {
		return boost;
	}
}