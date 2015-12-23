package characters;

import java.util.Random;

import cards.*;
import iohandling.IO;


public abstract class Character implements Creature{
	protected int strength;
	protected int craft;
	protected int gold;
	protected int life;
	public String profession;
	
	public static  Random randGen = new Random();

	public int startPosition;
	
	static public Character draw(){
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

	public void die() {
		this.life = 0;
	}

	public void printCard() {
		IO.display("<--- CHARACTER CARD --->");
		IO.display("Character profession: " + this.profession);
		IO.display("Character strength: " + this.strength);
		IO.display("Character craft: " + this.craft);
		IO.display("Character gold: " + this.gold);
		IO.display("Character life: " + this.life);
	}
	
	public void fight(int cardId){
		String enemyName = CardHandler.getCardInfo("advCardName", cardId);
		String fightType = CardHandler.getCardInfo("Special1", cardId);
		int enemyIndex = Integer.parseInt(CardHandler.getCardInfo("Special2", cardId));
		
		int characterIndex = 0;
		int roll;
		
		if(fightType.equals("strength"))			characterIndex = this.strength;
		else if(fightType.equals("craft"))			characterIndex = this.craft;
		
		
		IO.display("Enemy " + fightType + " is " + enemyIndex);
		roll = this.rollOfDice();
		IO.display("Enemy roll " + roll);
		enemyIndex += roll;
		IO.display("It gives " + enemyIndex);
		
		
		IO.display("Your " + fightType + " is " + characterIndex);
		roll = this.rollOfDice();
		IO.display("You roll " + roll);
		characterIndex += roll;
		IO.display("It gives you " + characterIndex);
		
		if(characterIndex > enemyIndex){
			IO.display("You win " + fightType + " fight with " + enemyName);
		}else if(characterIndex == enemyIndex){
			IO.display("You draw " + fightType + " fight with " + enemyName);
		}else if(characterIndex < enemyIndex){
			IO.display("You lose " + fightType + " fight with " + enemyName);
			this.modify("life", -1);
		}
	}
	
	public int move(int from, boolean ifleft){
		if(ifleft){return from - rollOfDice();
		}else{return from + rollOfDice();
		}
	}
	
	public int rollOfDice(){
		return (randGen.nextInt(5)+1);
	}

	public void explore(int mapId, int fieldId) {
		
	//	int cardNum = MapHandler.explore(mapId, fieldId);
		int cardId = randGen.nextInt(CardHandler.getAdvCardAmounh());
		this.execute(cardId);
		
	}
	private void execute(int cardId) {
		CardHandler.printCard(cardId);
		String cardType = CardHandler.getCardInfo("advCardType", cardId);
		switch (cardType){
		case "Event" : this.eventCardHandle(cardId);
			break;
		case "Object" : this.objectCardHandle(cardId);
			break;
		case "Enemy" : this.enemyCardHandle(cardId);
			break;
		default : IO.display("Nothing happend.");
			return;
		}
//		card.affect(this);
	}

	private void eventCardHandle(int cardId) {
		IO.display("Event");
		this.modify(CardHandler.getCardInfo("Special1", cardId), Integer.parseInt(CardHandler.getCardInfo("Special2", cardId)));
	}

	private void enemyCardHandle(int cardId) {
		IO.display("Enemy");
		this.fight(cardId);
	}
	
	private void objectCardHandle(int cardId) {
		IO.display("Object");
		
	}
	public void modify(String toModify, int i) {
		switch (toModify){
			case "strength" : this.strength += i;
				break;
			case "craft" : this.craft += i;
				break;
			case "gold" : this.gold += i;
			
				break;
			case "life" : this.life += i;
				break;
			default : 
				break;
		}
		if (this.life < 1) this.die();
		
	}

}
