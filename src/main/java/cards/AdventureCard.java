package cards;


import java.sql.ResultSet;

import characters.Character;

public abstract class AdventureCard implements Card{
	String name;
	String description;
	AdventureCard(String n, String d){
		this.name = n;
		this.description = d;

	}
	abstract public void affect(Character character);
	public void printCard() {
		System.out.println("----ADVENTURE----");
//		System.out.println("   - " + CardHandler.getCardInfo("type", cardId)+ " - ");
		System.out.println("");
		System.out.println(this.name);
		System.out.println("");
		System.out.println(this.description);
		System.out.println("-----------------");
		
	}
}
