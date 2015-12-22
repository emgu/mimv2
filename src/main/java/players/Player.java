package players;

import maps.*;

import java.util.Random;

import characters.Character;
import characters.Elf;
import characters.Warrior;
import characters.Wizard;
import iohandling.IO;

public class Player {
	String name;
	//static CharacterBuilder charBuilder;
//	void setBuilder(CharacterBuilder)
	public Character charact;
	public int position;
	public int map;
	
	public void setCharacter(Character c){
		
	};
	public Player(String n){
		this.name = n;
		charBuilder = new CharacterBuilder();
		this.charact = draw();
		this.position = charact.startPosition;
		this.map = 0;
	}
	private Character draw() {
		Character newChar;
		Random generator = new Random();
		int charNum = 3; /// iloœæ charakterów
		int ran = generator.nextInt(charNum )+1;
		System.out.println(ran);
		switch (ran){
			case 1 : newChar = new Warrior();
			break;
			case 2 : newChar = new Wizard();
			break;
			case 3 : newChar = new Elf();
			break;
			default : newChar = null;
			break;
		}
		return newChar;
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
