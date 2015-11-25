package model.dao;

public class QueryManager {
	public String find(String dbName, String where){		
		return "SELECT * FROM " + dbName +" WHERE " + where+ ";";
	}
	
	public String findAll(String dbName){
		return "SELECT * FROM " + dbName + ";";
	}
	
}
