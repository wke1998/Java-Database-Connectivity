package daowork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnUtil {
		 String url = "jdbc:sqlserver://localhost:1433;databasename=work;encrypt=false";
		 String user = "typhoon";
		 String pwd = "1234";
		 
		 //取得連線
		 public Connection getConnection() {
		 try {
			Connection conn = DriverManager.getConnection(url,user,pwd);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return null;
	}
		 //歸還連線
		 public void close(Connection conn){
			 try {
					conn.close();
			 } catch (SQLException e) {
					e.printStackTrace();
			}
		 }

}


