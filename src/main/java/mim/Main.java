package mim;

import java.util.*;

import javax.swing.JFrame;

import cards.*;
import data.*;
import GUI.*;
import iohandling.IO;
import maps.*;
import players.*;

public class Main {

	static final Scanner SCAN = new Scanner(System.in);
	static int PNUM = 0;
	protected static DataBase DB;
	
	static void dbStart(){
		
		DB = new DataBase("data/DBConfig.txt");
		
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



		dbStart();
		playersStart();

		
		int turn = 1;
		while(turn<30 && !PlayerHandler.allDead()){
		
			System.out.println("");
			System.out.println("Turn number " + turn + " :");
			for(Player p : PlayerHandler.getList()){
				System.out.println("");
				System.out.println("Player " + p.getName() + " - " + p.getProfession() + " :");
				p.move();
				System.out.println("");
				p.explore();
				System.out.println("");
				p.printPlayer();
				IO.getString();
			}
			
			System.out.println("End of turn number " + turn);
			System.out.println("");
			PlayerHandler.cleanUp();
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
			IO.display("Write player number " + h + " name: ");
			
			PlayerHandler.addPlayer(IO.getString());
		}
		
		for(Player p : PlayerHandler.getList()){
			p.printPlayer();
		}
	}

}
