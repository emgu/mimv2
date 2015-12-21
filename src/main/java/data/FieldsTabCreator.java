package data;

import java.io.*;
import java.sql.*;

class FieldsTabCreator extends TabCreator {

	private String filePath;
	
	FieldsTabCreator(String path){
		this.filePath = path;
		
	};
	
	@Override
	public void createTab(Statement statement) {
		try {
			BufferedReader fields = new BufferedReader(new FileReader(this.filePath));
			String tabName = fields.readLine();
			String primKey = fields.readLine();
			
			if(this.ifTabExists(statement, tabName)){
				fields.close();
				return;
			}
			
			statement.execute("CREATE TABLE " + tabName + " ("
					+ primKey + " BIGINT NOT NULL, "
					+ fields.readLine() + " VARCHAR(255), "
					+ fields.readLine() + " VARCHAR(1023), "
					+ fields.readLine() + " BIGINT NOT NULL, "
					+ "PRIMARY KEY (" + primKey + "))"
					);
			
			
			while(fields.ready()){
					statement.execute("INSERT INTO " + tabName + " VALUES ('" 
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
							+ fields.readLine() + "','"
							+ fields.readLine() + "')");
			}
			fields.close();
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
