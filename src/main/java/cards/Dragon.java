package cards;


import characters.Character;

public class Dragon extends AdventureCard{

	public Dragon(String n, String d, String t){
		super(n, d, t);
	};
	
	public void die() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void affect(Character character) {
		character.modify("life", -1);
	}
	
}
