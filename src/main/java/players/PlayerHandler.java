package players;

import java.util.*;

public class PlayerHandler{
	
	private static List<Player> playerList;
	
	private PlayerHandler(){
		playerList = new ArrayList<Player>();
	};
	public static void addPlayer(String name){
		playerList.add(new Player(name));
	}
	public static void killPlayer(Player p){
		playerList.remove(p);
	}	
	public static List<Player> getList(){
		return playerList;
	}
	public static boolean allDead(){
		return playerList.isEmpty();
	}
	public static void cleanUp() {
		for(Player p : playerList){
			if(!p.charact.isAlive()){
				playerList.remove(p);
			}
		}
	}

}
