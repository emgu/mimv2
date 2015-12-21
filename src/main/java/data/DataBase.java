package data;

import java.io.*;
import java.sql.*;

public class DataBase {
	static DataBase inst = null;
	String url;
	String user;
	String password;
	String dbName;
	Connection conection;
	protected Statement statement;	
	
	public ResultSet result;
	// constructor
	private DataBase(String DBConfigFile){
		
		try {
			tabCreator = null;
			System.out.println("constructor of DB");
			BufferedReader dbConfigFile = new BufferedReader(new FileReader(DBConfigFile));
			this.url = dbConfigFile.readLine();
			this.user = dbConfigFile.readLine();
			this.password = dbConfigFile.readLine();
			this.dbName = dbConfigFile.readLine();
			dbConfigFile.close();
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conection = DriverManager.getConnection(url, user, password);
			this.statement = conection.createStatement();
			
			if(!statement.executeQuery("SHOW DATABASES LIKE '" + dbName + "'").next()){
				this.statement.execute("CREATE DATABASE " + this.dbName);
			}
			this.statement.execute("USE " + this.dbName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Singleton
	static DataBase createDB(String DBConfigFile){
		if(inst == null){
			System.out.println("create DB attempt");
			inst = new DataBase(DBConfigFile);
			return inst;
		}
		return inst;
	}
	
	// Strategy of tab creation
	TabCreator tabCreator;// strategy
	void createTab(){
		if (tabCreator == null){
			System.out.println("tabCreator strategy not exist!!!");
			return;
		}
		tabCreator.createTab(this.statement);
	};
	void setTabCreator(TabCreator currentStrategy){
		this.tabCreator = currentStrategy;
	}
	/////////////////////////////////////////
	/////////////////////// MAPS /////////////////////////////////	
	public ResultSet getField(int mapId, int index) {
		try {
			
			String mapDB = getMapInfo(mapId, "mapDBname");
			String fieldsDB = getMapInfo(mapId, "mapFieldsDBName");

			result = statement.executeQuery("SELECT * "
					+ "FROM " + mapDB + " AS mm, " + fieldsDB + " AS f "
					+ "WHERE mm.fieldID = f.fieldID AND "
					+ "mm.mainMapFieldId = " + index);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int mapSize(int mapId) {
		try {
			String mapDB = getMapInfo(mapId, "mapDBname");
			result = statement.executeQuery("SELECT COUNT(*) "
					+ "FROM " + mapDB);
			result.first();
			return result.getInt("COUNT(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	// "info" is name of wanted field
	private String getMapInfo(int mapId, String info) {
		try {
			ResultSet mapInfo;
			mapInfo = statement.executeQuery("SELECT *"
					+ " FROM mim_mapsList"
					+ " WHERE mapId = " + mapId);
			mapInfo.first();
			return mapInfo.getString(info);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/////////////////////// CARDS /////////////////////////////////
	public ResultSet getCard(int cardId) {
		try {

			result = statement.executeQuery("SELECT * "
					+ "FROM mim_adventurecards AS ac "
					+ "WHERE ac.advCardId = " + cardId);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int advCardAmount() {
		try {
			result = statement.executeQuery("SELECT COUNT(*) "
					+ "FROM mim_adventurecards");
			result.first();
			return result.getInt("COUNT(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}