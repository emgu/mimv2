package characters;

import iohandling.IO;
import players.Player;

public class Wizard extends Character{
		
	public Wizard(Player p){
		super(p);
		this.strength = 2;
		this.craft = 4;
		this.gold = 2;
		this.life = 4;
		
		this.startPosition = 6; // Town
		this.profession = "Wizzard";
		
	};
	@Override
	public void printCard() {
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. ");
		IO.display("<---------------------->");
	}
	
}
