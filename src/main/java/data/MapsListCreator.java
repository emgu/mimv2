package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class MapsListCreator extends TabCreator{

	private String filePath;
	
	MapsListCreator(String path){
		this.filePath = path;
	};
	
	@Override
	void createTab(Statement statement) {
		try {
			BufferedReader maps = new BufferedReader(new FileReader(this.filePath));

			String tabName = maps.readLine();
			String primKey = maps.readLine();
			
			if(this.ifTabExists(statement, tabName)){
				maps.close();
				return;
			}
			
			statement.execute("CREATE TABLE " + tabName + " ("
					+ primKey + " BIGINT NOT NULL, "
					+ maps.readLine() + " VARCHAR(255), "
					+ maps.readLine() + " VARCHAR(255), "
					+ maps.readLine() + " VARCHAR(255), "		
					+ "PRIMARY KEY (" + primKey + "))"
					);
			while(maps.ready()){
					statement.execute("INSERT INTO " + tabName + " VALUES ('" 
					+ maps.readLine() + "','"
					+ maps.readLine() + "','"
					+ maps.readLine() + "','"
					+ maps.readLine() + "')");
			}
			maps.close();
			System.out.println("Table " + tabName +" created");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
