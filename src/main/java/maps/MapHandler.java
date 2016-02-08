package maps;

import java.sql.*;

import mim.Main;
import data.DataBase;
import iohandling.IO;

public class MapHandler extends DataBase{
	
	public MapHandler(String DBConfigFile) {
		super(DBConfigFile);
	}
	
	public static void printField(int mapId, int fieldId){
		try {
			ResultSet res = getField(mapId, fieldId);
			res.first();
			
			IO.display("-----------------");
			IO.display(res.getString("name"));	
			IO.display(res.getString("description"));
			IO.display("-----------------");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void printMap(int mapId) {
		int mapSize = getMapSize(mapId);
		for(int index = 0; index < mapSize; index++){
			printField(mapId, index);
			IO.display("");;
		}
	}
	public static String getFieldInfo(int mapId, int fieldId, String info) {
		try {
			ResultSet res = getField(mapId, fieldId);
			res.first();
			return res.getString(info);
		} catch (SQLException e) {
			IO.display(mapId);
			IO.display(fieldId);
			IO.display(info);
			
			e.printStackTrace();
			return null;
		}
	}
	public static String getMapInfo(int mapId, String info) {
		try {
			ResultSet res;
			res = statement.executeQuery("SELECT *"
					+ " FROM mim_mapslist"
					+ " WHERE mapId = " + mapId);
			res.first();
			return res.getString(info);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getMapSize(int mapId) {
		try {
			String mapDB = getMapInfo(mapId, "mapDBName");
			
			ResultSet res = statement.executeQuery("SELECT COUNT(*) "
					+ "FROM " + mapDB);
			res.first();
			return res.getInt("COUNT(*)");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static ResultSet getField(int mapId, int fieldId) {
		try {
			
			String mapDB = getMapInfo(mapId, "mapDBname"); //mim_mainmap
			String fieldsDB = getMapInfo(mapId, "mapFieldsDBName");// mim_fields

			ResultSet res = statement.executeQuery("SELECT * "
					+ "FROM " + mapDB + " AS map, " + fieldsDB + " AS f "
					+ "WHERE map.fieldID = f.fieldID AND "
					+ "map.mainMapFieldId = " + fieldId);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
