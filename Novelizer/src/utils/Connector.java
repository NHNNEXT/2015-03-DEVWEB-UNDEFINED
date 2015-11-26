package utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connector { 

	private Logger log = LoggerFactory.getLogger("Connector.class");
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
			log.error("cannot make connections \n" + e);
		}
	}
	
	public Connection getConnection() {
		return this.conn;
	}
}
