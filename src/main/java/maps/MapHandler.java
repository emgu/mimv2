package maps;

import java.sql.*;

import mim.Main;

public class MapHandler extends Main{
	
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
	public static void printField(int mapId, int fieldId){
		
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

}
