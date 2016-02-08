package cards;

import java.sql.*;

import mim.Main;
import data.DataBase;
import iohandling.IO;

public class CardHandler extends DataBase{
	
	public CardHandler(String DBConfigFile) {
		super(DBConfigFile);
	}

	public static void printCard(int cardId){
		try {
			ResultSet card = getCard(cardId);
			IO.display("----ADVENTURE----");
			card.first();
			IO.display("   - " + card.getString("advCardType") + " - ");
			IO.display("");
			IO.display(card.getString("advCardName"));
			IO.display("");
			IO.display(card.getString("advCardDescription"));
			IO.display("-----------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getCardInfo(String info, int cardId){
		try {
			ResultSet res = getCard(cardId);
			res.first();
			return res.getString(info);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getAdvCardAmount() {
		try {
			ResultSet res = statement.executeQuery("SELECT COUNT(*) "
					+ "FROM mim_adventureCards");
			res.first();
			return res.getInt("COUNT(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public static ResultSet getCard(int cardId) {
		try {

			ResultSet res = statement.executeQuery("SELECT * "
					+ "FROM mim_adventureCards AS ac "
					+ "WHERE ac.advCardId = " + cardId);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
