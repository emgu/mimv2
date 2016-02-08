package data;

import java.io.*;
import java.sql.*;

import iohandling.IO;

public class DataBase {
	
	static DataBase instance = null;
	String url;
	String user;
	String password;
	String dbName;
	Connection conection;
	protected static Statement statement;	// passed for DB queries execution
	public ResultSet result;

	public DataBase(String DBConfigFile){
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
			statement = conection.createStatement();
			
			if(!statement.executeQuery("SHOW DATABASES LIKE '" + dbName + "'").next()){
				statement.execute("CREATE DATABASE " + this.dbName + " CHARACTER SET utf8 COLLATE utf8_general_ci");
			}
			statement.execute("USE " + this.dbName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Singleton
	public static DataBase getInstance(String DBConfigFile){
		if(instance == null){
			IO.display("create DB attempt");
			instance = new DataBase(DBConfigFile);
			return instance;
		}
		return instance;
	}

	// Strategy of tab creation
	TabCreator tabCreator;// strategy
	public void createTab(){
		if (tabCreator == null){
			IO.display("tabCreator strategy not exist!!!");
			return;
		}
		tabCreator.createTab(statement);
	};
	public void setTabCreator(TabCreator currentStrategy){
		this.tabCreator = currentStrategy;
	}

}