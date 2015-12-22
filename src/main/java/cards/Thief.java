package cards;

import characters.Character;

public class Thief extends AdventureCard{

	public Thief(String n, String d, String t){
		super(n, d,t);
	};
	
	public void die() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void affect(Character character) {
		character.modify("gold", -1);
	}
	
}
