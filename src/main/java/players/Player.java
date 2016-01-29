package players;

import maps.*;

import characters.Character;
import iohandling.IO;

public class Player {
	String name;
	public Character character;
	
	public void setCharacter(Character c){
		
	};
	public Player(String n){
		this.name = n;
		this.character = Character.draw(this);
	}
	
	public void printPlayer(){
		IO.display("\nPlayer: " + this.name);
		IO.display("present location: " + this.character.position + " - " + MapHandler.fieldName(this.character.map, this.character.position));
		this.character.printCard();
	}
	public String getName(){
		return this.name;
	}

	public int move(){

		this.character.move(from, ifleft)
		/*int newPosition = character.move(this.position, false) % MapHandler.getMapSize(this.map);
		System.out.print("Move from " + MapHandler.fieldName(this.map, this.position) + " to ");
		this.position = newPosition;
		System.out.print(MapHandler.fieldName(this.map, this.position) + ":");
		System.out.println("");
		
		return this.position;*/
	}
	public void explore() {
	//	MapHandler.printField(this.map, this.position);
	//	Field field = MapHandler.getField(this.map, this.position)
		System.out.println("Exploration:");
		this.character.explore(this.map, this.position);
	};
	
}
