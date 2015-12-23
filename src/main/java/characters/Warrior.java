package characters;

import cards.CardHandler;
import iohandling.IO;

public class Warrior extends Character{

	public Warrior(){
		this.strength = 4;
		this.craft = 2;
		this.gold = 2;
		this.life = 4;
		
		this.startPosition = 0; // Tavern
		this.profession = "Warrior";
		
	};
	@Override
	public void printCard() {
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. As a great fighter, You can roll a dice 2 times\n\rand chose better result, while strength fight.");
		IO.display("<---------------------->");
	}
	@Override
	public void fight(int cardId){
		String enemyName = CardHandler.getCardInfo("advCardName", cardId);
		String fightType = CardHandler.getCardInfo("Special1", cardId);
		int enemyIndex = Integer.parseInt(CardHandler.getCardInfo("Special2", cardId));
		
		int characterIndex = 0;
		int roll;
		int roll2;
		
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
		String choise = IO.getString("Do you want to roll one more time? (press Y or y for yes):");
		if(choise.equals("Y") || choise.equals("y")){ roll2 = this.rollOfDice();
			IO.display("Your second roll is " + roll2);
			choise = IO.getString("Do You want to change first roll  on second roll? (press Y or y for yes):");
			if(choise.equals("Y") || choise.equals("y")) roll = roll2;
		}
	
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
	
}


