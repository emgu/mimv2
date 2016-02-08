package equipment;

public class Sword extends Weapon{
	public int boost;

	public Sword(int b){ boost = b; }

	@Override
	public int getStrengthBoost() {
		return boost;
	}
}
