package players;

import maps.*;

import characters.Character;
import iohandling.IO;

public class Player {
	String name;
	public Character charact;
	public int position;
	public int map;
	
	public void setCharacter(Character c){
		
	};
	public Player(String n){
		this.name = n;
		this.charact = Character.draw(this);
		this.position = charact.startPosition;
		this.map = 0;
	}
	
	public void printPlayer(){
		IO.display("\nPlayer: " + this.name);
		IO.display("present location: " + this.position + " - " + MapHandler.fieldName(this.map, this.position));
		this.charact.printCard();
	}
	public String getName(){
		return this.name;
	}
	public String getProfession(){
		return this.charact.profession;
	}
	public int move(){
		int newPosition = charact.move(this.position, false) % MapHandler.getMapSize(this.map);
		System.out.print("Move from " + MapHandler.fieldName(this.map, this.position) + " to ");
		this.position = newPosition;
		System.out.print(MapHandler.fieldName(this.map, this.position) + ":");
		System.out.println("");
		
		
		return this.position;
	}
	public void explore() {
	//	MapHandler.printField(this.map, this.position);
	//	Field field = MapHandler.getField(this.map, this.position)
		System.out.println("Exploration:");
		this.charact.explore(this.map, this.position);
	};
	
}
