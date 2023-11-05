package daowork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conntest {

	public static void main(String[] args) {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		try (Statement stmt=conn.createStatement();){
			ResultSet rs=stmt.executeQuery("SELECT TOP (1000) [序號]\r\n"
					+ "      ,[裁罰日期]\r\n"
					+ "      ,[裁罰金額]\r\n"
					+ "      ,[受處分人]\r\n"
					+ "      ,[違反法令]\r\n"
					+ "  FROM [work].[dbo].[punishlist]");
				while(rs.next()) {
					System.out.println(rs.getString(1));
				}
				connUtil.close(conn);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
