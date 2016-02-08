package characters;

import cards.CardHandler;
import iohandling.IO;
import players.Player;

public class Warrior extends Character{

	public Warrior(){
		super();
		this.strength = 4;
		this.craft = 2;
		this.gold = 2;
		this.life = 4;
		
		this.position = 0; // Tavern
	//	this.profession = "Warrior";
		
	};
	@Override
	public void printCard() {
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. As a great fighter, You can roll a dice 2 times\n\rand chose better result, while strength fight.");
		IO.display("<---------------------->");
	}
	@Override
	protected int rollFight(String who, String fightType, int index) {
		if(who.equals("You") && fightType.equals("strength")){
			IO.display(who + " " + fightType + " is " + index);
			int roll = this.rollOfDice();
			IO.display(who + " roll " + roll);
			String choise = IO.getString("Do you want to roll one more time? (press Y or y, for yes):");
			if(choise.equals("Y") || choise.equals("y")){ 
				int roll2 = this.rollOfDice();
				IO.display("Your second roll is " + roll2);
				choise = IO.getString("Do You want to change first roll  on second roll? (press Y or y, for yes):");
				if(choise.equals("Y") || choise.equals("y")) roll = roll2;
			}
			int res = index + roll;
			IO.display("It gives " + res);
			return res;
		}else{
			return super.rollFight(who, fightType, index);
		}
	}
	
}


