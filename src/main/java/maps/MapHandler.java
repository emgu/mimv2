package maps;

import java.sql.*;

import data.*;

public class MapHandler {
	
	static DataBase DB;
	
	public static void create(DataBase db) {
		DB = db;
	}
	
	public static int getMapSize(int mapId) {
		return DB.mapSize(mapId);
	}
	
	public static String fieldName(int mapId, int fieldId) {
		try {
			ResultSet res = DB.getField(mapId, fieldId);
			res.first();
			return res.getString("name") + "(" + fieldId + ")";
				
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static public void printField(int mapId, int fieldId){
		
		try {
			ResultSet res = DB.getField(mapId, fieldId);
			System.out.println("-----------------");
			res.first();
		//	System.out.println(res.getString("mainMapFieldId"));
			System.out.println(res.getString("name"));
			
			System.out.println(res.getString("description"));
			System.out.println("-----------------");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void printMap(int mapId) {
		int mapSize = MapHandler.getMapSize(mapId);
		for(int index = 0; index < mapSize; index++){
			MapHandler.printField(mapId, index);
			System.out.println("");;
		}
	}

	public static int explore(int mapId, int fieldId) {
		try {
		ResultSet res = DB.getField(mapId, fieldId);
			res.first();
			return res.getInt("cardNum");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
}
