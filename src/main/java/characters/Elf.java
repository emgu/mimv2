package characters;

import iohandling.IO;
import players.Player;

public class Elf extends Character{

	public Elf(Player p){
		super(p);
		this.strength = 3;
		this.craft = 3;
		this.gold = 2;
		this.life = 4;
		
		this.startPosition = 20; // Forrest
		this.profession = "Elf";
		
	};
	@Override
	public void printCard() {
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. As an Elf You can add 1 when You move.");
		IO.display("<---------------------->");
	}
	@Override
	public int move(int from, boolean ifleft){
		int roll = rollOfDice();
		
		String choise = IO.getString("You move on " + roll + " fields.\n\rAdd 1? (press Y or y for yes):");
		if(choise.equals("Y") || choise.equals("y")) roll++;
		IO.display("You move on " + roll + " fields.");

		if(ifleft){return from - roll;
		}else{return from + roll;
		}
	}
	
}
