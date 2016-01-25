package data;

public class DBHandler {
	static DataBase DB;

	static public void createDB(String DBConfigPath){
		DB = DataBase.createDB(DBConfigPath);
		
	}
	static public DataBase getDB(){
		return DB;
	}
	
	// creating table with all available maps list - Builder
	static public void createMapsList (String MapsListPath){
		DB.setTabCreator(new MapsListTabCreator(MapsListPath));
		DB.createTab();
	};
	static public void createFields (String fieldsConfigPath){
		DB.setTabCreator(new FieldsTabCreator(fieldsConfigPath));
		DB.createTab();
	};
	static public void createMap (String mapConfigPath){
		DB.setTabCreator(new MainMapTabCreator(mapConfigPath));
		DB.createTab();
	};	
	static public void createAdventureCard (String advCardConfigPath){
		DB.setTabCreator(new AdventureCardCreator(advCardConfigPath));
		DB.createTab();
	};
	
}
