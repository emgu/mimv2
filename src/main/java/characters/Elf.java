package characters;

import iohandling.IO;
import maps.MapHandler;


public class Elf extends Character{

	public Elf(){
		super();
		this.strength = 3;
		this.craft = 3;
		this.gold = 2;
		this.life = 4;
		
		this.position = 20; // Forrest
		
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

		int accPosition = this.position;
		int mapSize = MapHandler.getMapSize(this.map);
		int roll = rollOfDice();
		
		// Elf ability
		if(IO.getString("You move on " + roll + " fields.\n\rAdd 1? (y for yes):").equals("y")) roll++;
		
		if(iL){
			IO.display("You move on " + roll + " fields left.");
			accPosition -= roll;
			if(accPosition < 0) accPosition += mapSize;
			
		}else{
			IO.display("You move on " + roll + " fields left.");
			accPosition += roll;
			if(accPosition > (mapSize - 1)) accPosition -= mapSize;
		}
		
		this.position = accPosition;
		return this.position;
	}

}
