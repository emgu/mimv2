package mim;

import java.util.*;

import javax.swing.JFrame;

import cards.*;
import data.*;
import equipment.Sword;
import GUI.*;
import iohandling.*;
import maps.*;
import players.*;
import equipment.*;

public class Main {

	static int PNUM = 0;
	static DataBase DB;
	
	static void dbStart(){
		// creating table with all available maps list - Builder
		DB.setTabCreator(new MapsListTabCreator("data/MapsList.txt"));
		DB.createTab();
		DB.setTabCreator(new FieldsTabCreator("data/Fields.txt"));
		DB.createTab();
		DB.setTabCreator(new MainMapTabCreator("data/MainMap.txt"));
		DB.createTab();
		DB.setTabCreator(new AdventureTabCreator("data/AdventureCards.txt"));
		DB.createTab();
	}
	
// Main function:	
	public static void main(String[] args) {
		
		DB = DataBase.getInstance("data/DBConfig.txt");
		PlayerHandler.getInstance();
		IO.getInstance();
		
		dbStart();
		playersStart();

		int turn = 1;
		Player p;
		Iterator<Player> itr;
		
		while(turn<30 && !PlayerHandler.allDead()){
	
			IO.display("");
			IO.display("Turn number " + turn + " :");
			
			itr = PlayerHandler.getList().iterator();
			
			while(itr.hasNext()){
			
				p = itr.next();
				
				IO.display("");
				IO.display("Player " + p.name + " - " + p.character.getClass().getSimpleName() + " :");
				
				p.move();
				IO.display("");
				
				p.explore();
				IO.display("");
				
				p.printPlayer();
				IO.getString();
				
				if(p.character.isDead()) itr.remove();

			}
			
			IO.display("End of turn number " + turn);
			IO.display("");
			turn++;
		}

	}

	private static void playersStart() {
		while (PNUM < 1 || PNUM > 4){
			PNUM = IO.getInt("Write players number (1-4):");
		}
		IO.display("Player number is: " + PNUM);
		
		for(int h = 1; h <= PNUM; h++){
			IO.display("Player number " + h);
			PlayerHandler.addPlayer(new Player(IO.getString("Write player number " + h + " name: ")));
		}
		
		for(Player p : PlayerHandler.getList()){
			p.printPlayer();
		}
	}

}
