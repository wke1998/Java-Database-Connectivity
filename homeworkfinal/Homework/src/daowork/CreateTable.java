package daowork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {

	public static void main(String[] args) {
		//建立資料table
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		String createSql="create table punishlist(序號 int Primary Key,裁罰日期 date,裁罰金額 money,"
				+ "受處分人 nvarchar(100),違反法令 nvarchar(200))";
		try(PreparedStatement pstmt = conn.prepareStatement(createSql)) {
		    pstmt.executeUpdate(); 
		    System.out.println("資料表已建立成功");
		} catch (SQLException e) {
		    e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}		
	}
}
