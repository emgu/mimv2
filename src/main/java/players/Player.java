package players;

import maps.*;

import characters.Character;

public class Player {
	String name;
	Character charact;
	public int position;
	public int map;
	
	public Player(String n){
		this.name = n;
		this.charact = (Character)Character.draw();
		this.position = charact.startPosition;
		this.map = 0;
	}
	public void printPlayer(){
		System.out.println("\nPlayer: " + this.name);
		System.out.println("present location: " + this.position + " - " + MapHandler.fieldName(this.map, this.position));
		this.charact.printCard();
	}
	public String getName(){
		return this.name;
	}
	public String getProfession(){
		return this.charact.profession;
	}
	public int move(){
		System.out.print("Move from " + MapHandler.fieldName(this.map, this.position) + " to ");
		this.position = charact.move(this.position, false) % MapHandler.getMapSize(this.map);
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
