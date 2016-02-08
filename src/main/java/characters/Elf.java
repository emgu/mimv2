package characters;

import iohandling.IO;
import maps.MapHandler;
import players.Player;

public class Elf extends Character{

	public Elf(){
		super();
		this.strength = 3;
		this.craft = 3;
		this.gold = 2;
		this.life = 4;
		
		this.position = 20; // Forrest
	//	this.profession = "Elf";
		
	};
	@Override
	public void printCard() {
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. As an Elf You can add 1 when You move.");
		IO.display("<---------------------->");
	}
	@Override
	public int move(boolean iL){
		boolean ifLeft = iL;
		int accPosition = this.position;
		int mapSize = MapHandler.getMapSize(this.map);
		int roll = rollOfDice();
		
		String choise = IO.getString("You move on " + roll + " fields.\n\rAdd 1? (press Y or y for yes):");
		if(choise.equals("Y") || choise.equals("y")) roll++;
		IO.display("You move on " + roll + " fields.");

		if(ifLeft){
			//accPosition -= roll;
			if(accPosition - roll < 0) accPosition += mapSize-roll;
			else accPosition -= roll;
		}else{
			//accPosition += roll;
			if(accPosition + roll > (mapSize - 1)) accPosition -= mapSize+roll;
			else accPosition += roll;
		}
		this.position = accPosition;
		return this.position;
	}
	
}
