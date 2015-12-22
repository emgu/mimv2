package cards;

import characters.Character;

public class Gold extends AdventureCard{

	public Gold(String n, String d, String t){
		super(n, d, t);
	};
	@Override
	public void affect(Character character) {
		character.modify("gold", 1);
	}
	public void printCard() {
		// TODO Auto-generated method stub
		
	}
	public void die() {
		// TODO Auto-generated method stub
		
	}
}
