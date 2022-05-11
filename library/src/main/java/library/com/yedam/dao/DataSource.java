package library.com.yedam.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataSource {
	private static DataSource dataSource = new DataSource();	//싱글톤, 자기 자신 인스턴스
	private DataSource() {};	// 외부에서 사용하지 못하게 하고, 싱클톤은 외부에서 사용하지 못하게 해야함
	
	private Connection conn; // db 연결통로
	private String driver; // 
	private String url;
	private String user;
	private String password;
	
	public static DataSource getInstance() {	// 불러주고
		return dataSource;
	}
	
	public Connection getConnection() {
		dbConfig();
		try {
			Class.forName(driver); // driver 로딩
			conn = DriverManager.getConnection(url, user, password); // DriverManager 통해 db 연결
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void dbConfig() {
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		try {
			properties.load(new FileInputStream(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
