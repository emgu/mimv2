package equipment;

import equipment.Weapon;

public class Axe extends Weapon{
	public int boost;

	public Axe(int b){ boost = b; }

	@Override
	public int getStrengthBoost() {
		return boost;
	}
}
