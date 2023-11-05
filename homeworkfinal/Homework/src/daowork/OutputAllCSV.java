package daowork;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OutputAllCSV {

	public static void main(String[] args) {
		//輸出csv的全部結果
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		String selectAllSql="select*from dbo.punishlist";
		try (PreparedStatement pstmt = conn.prepareStatement(selectAllSql);
				ResultSet rs = pstmt.executeQuery();){//將結果存入rs
		        // 使用filewriter將資料寫入
		        FileWriter csvWriter = new FileWriter("homework.csv");//創建寫入器存入csv資料

		        // 寫入標題
		        csvWriter.append("序號,裁罰日期,裁罰金額,受處分人,違反法令");//用append修改字符串
		        csvWriter.append("\n");

		       //使用while迴圈逐行寫入
		        while (rs.next()) {
		            String id = rs.getString(1);
		            //轉換日期格式
		            String date = rs.getString(2);
		            String originalDate = rs.getString(2);
		            //找出對應的日期
		            //索引值從0開始，尾端不放入，類似slice
		            String formatDate = originalDate.substring(1, 4) + "/" 
		            + originalDate.substring(5, 7) + "/" + originalDate.substring(8,10);		
		            
		            //將金額轉回原本的顯示模式
		            int price = rs.getInt(3);
		            //把千位數的分隔符換回來
		            String formatPrice = String.format("%,d",price);
		            String name = rs.getString(4);
		            String law = rs.getString(5);

		            csvWriter.append(id).append(",").append(formatDate).append(",")
		            .append(formatPrice).append(",").append(name).append(",").append(law);
		            csvWriter.append("\n");
		        }//while迴圈到此結束
		        System.out.println("CSV導出成功");
		        csvWriter.flush();
		        csvWriter.close();
		    }catch (SQLException | IOException e) {
		    e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}

	}

}
