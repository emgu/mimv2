package data;

import java.io.*;
import java.sql.*;

public class AdventureTabCreator extends TabCreator {

	private String filePath;
	
	public AdventureTabCreator(String path){
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
					+ primKey + " INT NOT NULL, "				//prim key
					+ fields.readLine() + " VARCHAR(255), "		//name
					+ fields.readLine() + " VARCHAR(1023), "	//description
					+ fields.readLine() + " VARCHAR(255), "		//type
					+ fields.readLine() + " VARCHAR(255), "		//special1
					+ fields.readLine() + " VARCHAR(255), "		//special2
					+ fields.readLine() + " VARCHAR(255), "		//special3
					+ fields.readLine() + " VARCHAR(255), "		//special4
					+ "PRIMARY KEY (" + primKey + "))"
					+ "AUTO_INCREMENT=0"
					);
			
			while(fields.ready()){
					statement.execute("INSERT INTO " + tabName + " VALUES('"
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
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
