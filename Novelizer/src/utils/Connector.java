package utils;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mongodb.Mongo;

public class Connector { 
	// Issue ServletContextListener 쓸때, context-param을 web.xml에 설정하지 않고 annotation 기법으로 구현하는 방법 모르겠음
	// 검색해보니까 그렇게 구현 못하는 것 같은데.. 
	private Connection conn = null;
	
	public Connector(){
		String url = "jdbc:mysql://localhost:3306/novelizer?useUnicode=true&characterEncoding=utf8"; 
		// 서버 ip : 125.209.195.85, DB 이름 : novelizer
		String id = "root";
		String pw = "db1004"; 
		// Default id를 root와 pw로 뒀음, 서버 셋팅할때 수정 필요
		makeConnection(url, id, pw);
	}

	public Connector(String url, String id, String pw){
		makeConnection(url, id, pw);
	}
	
	private void makeConnection(String url, String id, String pw) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}
