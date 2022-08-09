package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

public class DBConnection {
	
	
	private static Connection conn;
	
	public static Connection getConnection() {
		
		
		if(conn == null) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		// JAVA쪽으로 설계도 클래스를 사용하고자 불러오는것
			System.out.println("드라이버 로딩 성공!");
			
			// 다리를 짓고자 하는 목적지
			String url = "jdbc:mysql://localhost:3306/melon";
			String user = "root";
			String password = "1234";
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException cfne) {
			System.out.println("드라이버 로딩 실패 : " + cfne);
			
		} catch (SQLException sqle) {
			System.out.println("DB 연결 실패 : " + sqle);
		}
		
		}
		return conn;
	}
	
	
	
	
	
}
