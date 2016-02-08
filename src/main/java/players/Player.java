package players;

import maps.*;

import characters.*;
import characters.Character;
import iohandling.IO;

public class Player {
	public String name;
	public Character character;
	
	public Player(String n){
		this.name = n;
		this.character = Character.draw(this);
	}
	
	public void printPlayer(){
		IO.display("\nPlayer: " + this.name);
		IO.display("Present location: " + this.character.position + " - " 
				   + MapHandler.getFieldInfo(this.character.map, this.character.position, "name"));
		this.character.printCard();
		this.character.printEquipment();
	}

	public void move(){
		this.character.move(true);
	}
	public void explore() {
		IO.display("Exploration:");
		this.character.explore();
	}
	
}
