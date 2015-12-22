package cards;

import characters.Character;

public abstract class AdventureCard implements Card{
	String name;
	String description;
	String type;
	AdventureCard(String n, String d, String t){
		this.name = n;
		this.description = d;
		this.type = t;
	}
	abstract public void affect(Character character);
	public void printCard() {
		System.out.println("----ADVENTURE----");
		System.out.println(this.type);
		System.out.println("");
		System.out.println(this.name);
		System.out.println("");
		System.out.println(this.description);
		System.out.println("-----------------");
		
	}
}
