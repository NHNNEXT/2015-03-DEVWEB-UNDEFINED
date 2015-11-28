package model.dao;

public class QueryManager {
	public String find(String tableName, String where) {
		return "SELECT * FROM " + tableName + " WHERE " + where + ";";
	}

	public String findAll(String tableName) {
		return "SELECT * FROM " + tableName + ";";
	}

	public String Insert(String tableName, String cloumnName, String data) {
		return "INSERT INTO " + tableName + "(" + cloumnName + ") VALUES(" + data + ");";
	}
	
	public String toQueryStirng(String text){
		return "'" + text + "'";
	}
	
	
}
