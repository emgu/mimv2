package players;

import java.util.*;

public class PlayerList {
	private static List<Player> playerList;
	private PlayerList(){
		playerList = new ArrayList<Player>();
	};
	private static class PlayerListHolder{
		private static final PlayerList INSTANCE = new PlayerList();
	}
	public static PlayerList create(){
		return PlayerListHolder.INSTANCE;
	}
	public static void addPlayer(String name){
		playerList.add(new Player(name));
	}
	public static List<Player> getList(){
		return playerList;
	}
}
