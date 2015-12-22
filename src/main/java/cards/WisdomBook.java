package cards;

import characters.Character;

public class WisdomBook extends AdventureCard{

	public WisdomBook(String n, String d, String t){
		super(n, d, t);
	};
	
	public void die() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void affect(Character character) {
		character.modify("inteligence", 1);
	}
	
}