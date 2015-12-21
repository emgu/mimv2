package data;

import java.sql.SQLException;
import java.sql.Statement;

abstract class TabCreator {
	abstract void createTab(Statement statement);
	protected boolean ifTabExists(Statement statement, String tabName) throws SQLException{
		return statement.executeQuery("SHOW TABLES LIKE '" + tabName + "'").next();
	}
}
