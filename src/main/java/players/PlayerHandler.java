package players;

import java.util.*;

import iohandling.IO;

public class PlayerHandler{
	
	private static PlayerHandler instance = null;
	private static List<Player> playerList;
	
	private PlayerHandler(){
		playerList = new ArrayList<Player>();
	}
	//Singleton
	public static PlayerHandler getInstance(){
		if (instance == null){
			instance = new PlayerHandler();
			return instance;
		}
		return instance;
	}

	public static void addPlayer(Player newPlayer){
		playerList.add(newPlayer);
	}
	public static void removePlayer(Player p){
		playerList.remove(p);
	}	
	public static List<Player> getList(){
		return playerList;
	}
	public static boolean allDead(){
		return playerList.isEmpty();
	}
	public static void cleanUp() {
		Player toErase;
		for(Player p : playerList){
			if(p.character.isDead()){
				toErase = p;
			}
		}
	//	if(toErase)
	//	Iterator<Player> itr = playerList.iterator();
	//	while(itr.hasNext()){
	//		if(itr.next().character.isDead()) itr.remove();;
	//	}
	}

}
