package characters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import cards.*;
import equipment.*;
import iohandling.IO;
import maps.MapHandler;
import players.*;


public abstract class Character{
	// 
	protected int strength;
	protected int craft;
	protected int gold;
	protected int life;
	// position
	public int position;
	public int map;
	// experience
	protected int expS;
	protected int expC;
	protected final int NEXTLEV;
	// stuff
	public Equipment<String, Obj> equipment;
	
	public static Random randGen = new Random();

	protected  Character(){
		map = 0; 		// all characters start in main map
		expS = 0;
		expC = 0;
		NEXTLEV = 5;
		equipment = new Equipment<String, Obj>(4);
	}
	static public Character draw(Player p){
		Character newChar;
		Random generator = new Random();
		int charNum = 3; /// iloœæ charakterów
		int ran = generator.nextInt(charNum )+1;
		ran = 3;
	///	System.out.println(ran);
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
	public void modify(String toModify, int i) {
		switch (toModify){
			case "strength" : this.strength += i;
				break;
				
			case "craft" : this.craft += i;
				break;
				
			case "gold" : this.gold += i;
				break;
				
			case "life" : this.life += i;
				if (this.life < 1) this.die();
				break;
				
			case "expS" : this.expS += i;
				if (expS > this.NEXTLEV){
					int increase = expS / this.NEXTLEV;
					expS %= this.NEXTLEV;
					this.strength += increase;
				}
				break;
				
			case "expC" : this.expC += i;
				if (expC > this.NEXTLEV){
					int increase = expC / this.NEXTLEV;
					expC %= this.NEXTLEV;
					this.craft += increase;
				}
				break;
			default : 
				break;
		}
	}
	public boolean isDead(){
		return this.life < 1;
	}
	public void die() {
//		this.life = 0;
		IO.display("You die...");
	//	PlayerList.killPlayer(this.player);
	}
	public void printCard() {
		IO.display("<--- CHARACTER CARD --->");
		IO.display("Character: " + this.getClass().getSimpleName());
		IO.display("Character strength: " + this.strength + this.equipment.printStrengthBoost("Weapon") + ", strength experience: " + this.expS);
		IO.display("Character craft: " + this.craft + this.equipment.printCraftBoost("Weapon") + ", craft experience: " + this.expC);
		IO.display("Character gold: " + this.gold);
		IO.display("Character life: " + this.life);
	}
	public void printEquipment() {
		IO.display("Equipment:");
		IO.display(equipment.printObj("Weapon"));
		IO.display(equipment.printObj("Armour"));
		IO.display(equipment.printObj("Shield"));
		IO.display(equipment.printObj("Helmet"));
		IO.display("<---------------------->");
	}
	//////////////////////////////////
	public int rollOfDice(){
		return (randGen.nextInt(5)+1);
	}
	public int move(boolean iL){
		boolean ifLeft = iL;
		int accPosition = this.position;
		IO.display("position"+accPosition);
		
		int mapSize = MapHandler.getMapSize(this.map);
		int roll = rollOfDice();
		IO.display("roll"+roll);
		if(ifLeft){
			//accPosition -= roll;
			if(accPosition - roll < 0) accPosition += mapSize-roll;
			else accPosition -= roll;
		}else{
			//accPosition += roll;
			if(accPosition + roll > (mapSize - 1)) accPosition -= mapSize+roll;
			else accPosition += roll;
		}
		this.position = accPosition;
		return this.position;
	}
	public void explore() {
		
		//	TODO handling other types of fields...
		
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
	}
	private void eventCardHandle(int cardId) {
	//	IO.display("Event" + CardHandler.getCardInfo("Special1", cardId));
	//	IO.display(Integer.parseInt(CardHandler.getCardInfo("Special2", cardId)));
		this.modify(CardHandler.getCardInfo("Special1", cardId), Integer.parseInt(CardHandler.getCardInfo("Special2", cardId)));
	}
	private void enemyCardHandle(int cardId) {
		IO.display("Enemy");
		this.fight(cardId);
	}
	protected void fight(int cardId){
		
		String enemyName = CardHandler.getCardInfo("advCardName", cardId);
		String fightType = CardHandler.getCardInfo("Special1", cardId);

		int enemyIndex = Integer.parseInt(CardHandler.getCardInfo("Special2", cardId));
		int characterIndex = 0;
		if(fightType.equals("strength"))			characterIndex = this.strength + this.equipment.strengthBoost("Weapon");
		else if(fightType.equals("craft"))			characterIndex = this.craft + this.equipment.craftBoost("Weapon");
		
		int enemyRes = rollFight(enemyName, fightType, enemyIndex);
		int characterRes = rollFight("You", fightType, characterIndex);		
		
		if(characterRes > enemyRes) winFight(fightType, enemyName, enemyIndex, cardId);
		else if(characterRes == enemyRes) drawFight(fightType, enemyName, enemyIndex, cardId);
		else if(characterRes < enemyRes) loseFight(fightType, enemyName, enemyIndex, cardId);
			
	}
	protected void winFight(String fightType, String enemyName, int enemyIndex, int cardId){
		IO.display("You win " + fightType + " fight with " + enemyName);
		IO.display("You get " + enemyIndex + " points of " + fightType + " experience.");
		if(fightType.equals("strength")) this.modify("expS", enemyIndex);
		if(fightType.equals("craft")) this.modify("expC", enemyIndex);
	}
	protected void drawFight(String fightType, String enemyName, int enemyIndex, int cardId){
		IO.display("You draw " + fightType + " fight with " + enemyName);
	}
	protected void loseFight(String fightType, String enemyName, int enemyIndex, int cardId){
		IO.display("You lost " + fightType + " fight with " + enemyName);
		
		// craft fight - always lose 1 life
		if(fightType.equals("craft")){
			this.modify("life", -1);
			return;
		}
		
		// strength fight - trying to avoid 1 point of life lose
		if(protectionAttempt("Armour")) return;
		if(protectionAttempt("Shield")) return;
		if(protectionAttempt("Helmet")) return;
		this.modify("life", -1);
	}
	private boolean protectionAttempt(String protObject) {
		if(equipment.containsKey(protObject)){
			IO.display("You have " + equipment.printObj(protObject));
			int toAvoid = 6-equipment.protectionBoost(protObject);
			IO.display("If you roll more than " + toAvoid + " You won't lose 1 point of life");
			if(IO.getString("Do you want to roll (y, for yes):").equals("y")){
				int roll = rollOfDice();
				IO.display("You roll " + roll);
				if(roll>toAvoid){
					IO.display("Armour protected You");
					return true;
				}IO.display("Armour didn't protect You");
			}	
		}return false;
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
		ResultSet res = CardHandler.getCard(cardId);
		
		String objType;
		try {
			res.first();
			objType = res.getString("Special1");
			String objName = res.getString("advCardName");
			int objBoost = res.getInt("Special3");		
			switch(objName){
				case "Axe" : this.equipment.put(objType, new Axe(objBoost));
				break;
				case "Sword" : this.equipment.put(objType, new Sword(objBoost));
				break;
				case "Armour" : this.equipment.put(objType, new Armour(objBoost));
				break;
				case "Shield" : this.equipment.put(objType, new Shield(objBoost));
				break;
				case "Helmet" : this.equipment.put(objType, new Helmet(objBoost));
				break;
				default : break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
