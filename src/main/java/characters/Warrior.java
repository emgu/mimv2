package characters;

import iohandling.IO;

public class Warrior extends Character{

	public Warrior(){
		this.strength = 4;
		this.inteligence = 2;
		this.gold = 2;
		this.life = 4;
		
		this.startPosition = 0; // Tavern
		this.profession = "Warrior";
		
	};
	@Override
	public void printCard() {
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. ");
		IO.display("<---------------------->");
	}
	
	
}
