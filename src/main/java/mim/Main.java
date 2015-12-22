package mim;

import java.util.*;

import cards.*;
import data.*;
import iohandling.IO;
import maps.*;
import players.*;

public class Main {

	static final Scanner scan = new Scanner(System.in);
	static int PNUM = 0;
	
	
	static void db(){
		DBHandler.createDB("data/DBConfig.txt");
		
		MapHandler.create(DBHandler.getDB());
		CardHandler.create(DBHandler.getDB());

		DBHandler.createMapsList("data/MapsList.txt");
		DBHandler.createFields("data/Fields.txt");
		DBHandler.createMap("data/MainMap.txt");
		DBHandler.createAdventureCard("data/AdventureCards.txt");
		
		
	}
	
// IO controll functions:	

	private static int readInt() {
		try{
			return Integer.parseInt(scan.nextLine());
		}catch(NumberFormatException e){
			e.printStackTrace(System.out);
			return Integer.MIN_VALUE;
		}
	}
	private static String readString() {
		return scan.nextLine();
		
	}
	
// Main function:	
	public static void main(String[] args) {

		PlayerList.create();

		db();
		start();
		
		
		int turn = 1;
		while(turn<10){
			System.out.println("");
			System.out.println("Turn number " + turn + " :");
			for(Player p : PlayerList.getList()){
				System.out.println("");
				System.out.println("Player " + p.getName() + " - " + p.getProfession() + " :");
				p.move();
				System.out.println("");
				p.explore();
				System.out.println("");
				p.printPlayer();
			}
			System.out.println("End of turn number " + turn);
			System.out.println("");
			turn++;
		}
		
	}


	private static void start() {
		while (PNUM < 1 || PNUM > 4){
			PNUM = IO.getInt("Write players number (1-4):");
		}
		
		IO.display("Player number is: " + PNUM);
		
		for(int h = 1; h <= PNUM; h++){
			IO.display("Player number " + h);
			PlayerList.addPlayer(IO.getString("Write player number " + h + " name: "));
		}
		
		for(Player p : PlayerList.getList()){
			p.printPlayer();
		}
	}

}
