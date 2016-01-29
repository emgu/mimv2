package cards;

import java.sql.*;

import mim.Main;

public class CardHandler extends Main{
	
	public static void printCard(int cardId){
		try {
			ResultSet card = DB.getCard(cardId);
			System.out.println("----ADVENTURE----");
			card.first();
			System.out.println("   - " + card.getString("advCardType") + " - ");
			System.out.println("");
			System.out.println(card.getString("advCardName"));
			System.out.println("");
			System.out.println(card.getString("advCardDescription"));
			System.out.println("-----------------");

	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getCardInfo(String info, int cardId){
		try {
			ResultSet card = DB.getCard(cardId);
			card.first();
			return card.getString(info);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getAdvCardAmount() {
		return DB.advCardAmount();
	}
	
}
