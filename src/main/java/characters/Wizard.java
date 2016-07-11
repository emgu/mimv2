package characters;

import iohandling.IO;
import cards.CardHandler;


public class Wizard extends Character{
		
	public Wizard(){
		super();
		this.strength = 2;
		this.craft = 4;
		this.gold = 2;
		this.life = 4;
		
		this.position = 6; // Town

	};
	@Override
	public void printCard() {
		
		super.printCard();
		
		IO.display("<--- SPECIAL ABILITIES --->");
		IO.display("1. You have extaordinary perception. This allows You to ignore Events.");
		IO.display("<---------------------->");
	}
	@Override
	public void eventCardHandle(int cardId) {

		// Wizard ability
		if(IO.getString("Do You want to Ignore event? (y for yes):").equals("y")){
			IO.display("You avoid " + CardHandler.getCardInfo("advCardName", cardId));
			return;
		}
			
		this.modify(CardHandler.getCardInfo("Special1", cardId), Integer.parseInt(CardHandler.getCardInfo("Special2", cardId)));
	}
}
