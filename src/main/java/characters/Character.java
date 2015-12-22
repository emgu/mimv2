package characters;

import java.util.Random;

import cards.*;
import iohandling.IO;


public abstract class Character implements Creature{
	protected int strength;
	protected int inteligence;
	protected int gold;
	protected int life;
	public String profession;
	
	public static  Random randGen = new Random();

	public int startPosition;
	

	public void die() {
		this.life = 0;

		
	}

	public void printCard() {
		IO.display("<--- CHARACTER CARD --->");
		IO.display("Character profession: " + this.profession);
		IO.display("Character strength: " + this.strength);
		IO.display("Character intelligence: " + this.inteligence);
		IO.display("Character gold: " + this.gold);
		IO.display("Character life: " + this.life);
	}
	
	public void fight(Card enemy){
		
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
		AdventureCard card;
		switch (cardId){
			case 0 : card = new Thief(	CardHandler.getCardInfo("advCardName", cardId),
										CardHandler.getCardInfo("advCardDescription", cardId),
										CardHandler.getCardInfo("advCardName", cardId)
										);
			break;
			case 1 : card = new Gold(	CardHandler.getCardInfo("advCardName", cardId),
										CardHandler.getCardInfo("advCardDescription", cardId),
										CardHandler.getCardInfo("advCardType", cardId)
									);
			break;
			case 2 : card = new WisdomBook(	CardHandler.getCardInfo("advCardName", cardId),
											CardHandler.getCardInfo("advCardDescription", cardId),
											CardHandler.getCardInfo("advCardType", cardId)
										);
			break;
			case 3 : card = new Sword(		CardHandler.getCardInfo("advCardName", cardId),
											CardHandler.getCardInfo("advCardDescription", cardId),
											CardHandler.getCardInfo("advCardType", cardId)
									);
			break;
			case 4 : card = new Dragon(		CardHandler.getCardInfo("advCardName", cardId),
											CardHandler.getCardInfo("advCardDescription", cardId),
											CardHandler.getCardInfo("advCardType", cardId)
									);
			break;
			default : card = null;
					IO.display("Nothing happend.");
					return;
		}
		card.printCard();
		this.execute(card);
		
	}
	private void execute(AdventureCard card) {
		card.affect(this);
	}
	public void modify(String toModify, int i) {
		switch (toModify){
			case "strength" : this.strength += i;
				break;
			case "inteligence" : this.inteligence += i;
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
