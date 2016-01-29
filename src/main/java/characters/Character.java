package characters;

import java.util.Random;

import cards.*;
import iohandling.IO;
import players.*;


public abstract class Character{
	protected int strength;
	protected int craft;
	protected int gold;
	protected int life;
	
	public int position;
	public int map;
	
	protected int expS;
	protected int expC;
	protected final int NEXTLEV;
	
	public final Player PLAYER;
	public static Random randGen = new Random();

	protected  Character(Player p){
		expS = 0;
		expC = 0;
		NEXTLEV = 5;
		PLAYER = p;
	}
	static public Character draw(Player p){
		Character newChar;
		Random generator = new Random();
		int charNum = 3; /// iloœæ charakterów
		int ran = generator.nextInt(charNum )+1;
	//	ran = 1;
	///	System.out.println(ran);
		switch (ran){
			case 1 : newChar = new Warrior(p);
			break;
			case 2 : newChar = new Wizard(p);
			break;
			case 3 : newChar = new Elf(p);
			break;
			default : newChar = null;
			break;
		}
		return newChar;
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
			case "expS" : this.expS += i;
				break;
			case "expC" : this.expC += i;
				break;
			default : 
				break;
		}
		if (this.life < 1) this.die();
		if (expS > this.NEXTLEV){
			int increase = expS / this.NEXTLEV;
			expS %= this.NEXTLEV;
			this.strength += increase;
		}
		if (expC > this.NEXTLEV){
			int increase = expC / this.NEXTLEV;
			expC %= this.NEXTLEV;
			this.craft += increase;
		}
	}
	public boolean isAlive(){
		return this.life > 0;
	}
	public void die() {
		this.life = 0;
		IO.display("You die...");
	//	PlayerList.killPlayer(this.player);
	}
	public void printCard() {
		IO.display("<--- CHARACTER CARD --->");
		IO.display("Character: " + this.profession);
		IO.display("Character strength: " + this.strength + ", strength experience: " + this.expS);
		IO.display("Character craft: " + this.craft + ", craft experience: " + this.expC);
		IO.display("Character gold: " + this.gold);
		IO.display("Character life: " + this.life);
	}
	//////////////////////////////////
	public int rollOfDice(){
		return (randGen.nextInt(5)+1);
	}
	public int move(){
		boolean ifleft = true;
		int roll = rollOfDice();
		if(ifleft){
			this.position -= roll;
		}else{
			this.position += roll;
		}
		return this.position;
	}
	public void explore(int mapId, int fieldId) {
		
		//	int cardNum = MapHandler.explore(mapId, fieldId);
			int cardId = randGen.nextInt(CardHandler.getAdvCardAmount());
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
	//	if(CardHandler.getCardInfo("Special1", cardId))
		IO.display("Event");
		this.modify(CardHandler.getCardInfo("Special1", cardId), Integer.parseInt(CardHandler.getCardInfo("Special2", cardId)));
	}
	private void enemyCardHandle(int cardId) {
		IO.display("Enemy");
		this.fight(cardId);
	}
	public void fight(int cardId){
		String enemyName = CardHandler.getCardInfo("advCardName", cardId);
		String fightType = CardHandler.getCardInfo("Special1", cardId);
		int enemyIndex = Integer.parseInt(CardHandler.getCardInfo("Special2", cardId));

	//	int roll;
		
		int characterIndex = 0;

		if(fightType.equals("strength"))			characterIndex = this.strength;
		else if(fightType.equals("craft"))			characterIndex = this.craft;
		
		int enemyRes = rollFight(enemyName, fightType, enemyIndex);
		int characterRes = rollFight("You", fightType, characterIndex);		
		
		if(characterRes > enemyRes){
			IO.display("You win " + fightType + " fight with " + enemyName);
			IO.display("You get " + enemyIndex + " points of " + fightType + " experience.");
			if(fightType.equals("strength")) this.modify("expS", enemyIndex);
			if(fightType.equals("craft")) this.modify("expC", enemyIndex);
			
		}else if(characterRes == enemyRes){
			IO.display("You draw " + fightType + " fight with " + enemyName);
		}else if(characterRes < enemyRes){
			IO.display("You lose " + fightType + " fight with " + enemyName);
			this.modify("life", -1);
		}
	}
	protected int rollFight(String who, String fightType, int index) {
		IO.display(who + " " + fightType + " is " + index);
		int roll = this.rollOfDice();
		IO.display(who + " roll " + roll);
		int res = index + roll;
		IO.display("It gives " + res);
		return res;
	}	
	private void objectCardHandle(int cardId) {
		IO.display("Object");	
	}
	
	
	
	

	
	
	

}
