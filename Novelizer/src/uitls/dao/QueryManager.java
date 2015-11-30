package uitls.dao;

public class QueryManager {
	public String find(String tableName, String where) {
		return "SELECT * FROM " + tableName + " WHERE " + where + ";";
	}

	public String find(String tableName, String... params) {
		return find(tableName, toWhereSql(params));
	}
	
	private String toWhereSql(String[] params) {
		StringBuffer whereSql = new StringBuffer();
		
		for (String param : params) {
			whereSql.append(param);
		}
		
		return whereSql.toString();
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
