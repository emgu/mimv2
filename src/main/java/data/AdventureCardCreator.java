package data;

import java.io.*;
import java.sql.*;

public class AdventureCardCreator extends TabCreator {

	private String filePath;
	
	AdventureCardCreator(String path){
		this.filePath = path;
		
	};
	@Override
	void createTab(Statement statement) {
		try {
			BufferedReader fields = new BufferedReader(new FileReader(this.filePath));
			String tabName = fields.readLine();
			String primKey = fields.readLine();
			
			if(this.ifTabExists(statement, tabName)){
				fields.close();
				return;
			}
			
			statement.execute("CREATE TABLE " + tabName + " ("
					+ primKey + " INT NOT NULL, "
					+ fields.readLine() + " VARCHAR(255), "
					+ fields.readLine() + " VARCHAR(1023), "
					+ fields.readLine() + " VARCHAR(255), "
					+ "PRIMARY KEY (" + primKey + "))"
					+ "AUTO_INCREMENT=0"
					);
			
			while(fields.ready()){
					statement.execute("INSERT INTO " + tabName + " VALUES('"
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
							+ fields.readLine() + "')");
			}
			fields.close();
			System.out.println("Table " + tabName +" created");
			
		} catch (Exception e) {
			System.out.println(this);
			e.printStackTrace();
		}

	}

}
