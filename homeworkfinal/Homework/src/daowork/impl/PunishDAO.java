package daowork.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import daowork.ConnUtil;
import daowork.IPunishlistDAO;
import daowork.Punish;

public class PunishDAO implements IPunishlistDAO {

	@Override
	public boolean createPunish(Punish punish) {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		String sql="INSERT INTO [dbo].[punishlist]\r\n"
				+ "           ([序號]\r\n"
				+ "           ,[裁罰日期]\r\n"
				+ "           ,[裁罰金額]\r\n"
				+ "           ,[受處分人]\r\n"
				+ "           ,[違反法令])\r\n"
				+ "           VALUES(?,?,?,?,?)";
		try (PreparedStatement pstmt=conn.prepareStatement(sql);){
			pstmt.setInt(1,punish.getPunishId());
			Date d=new Date(punish.getPunishDate().getTime());//轉型
			pstmt.setDate(2,d);
			pstmt.setInt(3,punish.getPunishPrice());
			pstmt.setString(4,punish.getPunishName());
			pstmt.setString(5,punish.getLaw());
			
			int c=pstmt.executeUpdate();
			return c>0; //確認有沒有新增一筆(含)以上的資料
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}
		return false;
	}

	@Override
	public Punish findPunishById(int punishid) {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		Punish p=new Punish();//創建一個Punish物件p，用於存儲查詢結果。
		String sql="SELECT [序號]\r\n"
				+ "      ,[裁罰日期]\r\n"
				+ "      ,[裁罰金額]\r\n"
				+ "      ,[受處分人]\r\n"
				+ "      ,[違反法令]\r\n"
				+ "  FROM [work].[dbo].[punishlist]"
				+ "  where [序號]=?";//透過序號查詢單一資料
		try (PreparedStatement pstmt=conn.prepareStatement(sql);){
			pstmt.setInt(1,punishid);//設置整數型參數，避免SQL injection
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			p.setPunishId(rs.getInt(1));
			p.setPunishDate(rs.getDate(2));
			p.setPunishPrice(rs.getInt(3));
			p.setPunishName(rs.getString(4));
			p.setLaw(rs.getString(5));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}
		return p;//回傳查詢結果
	}

	@Override
	public List<Punish> findAllPunish() {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		List<Punish> punishs=new ArrayList<>();
		String sql="SELECT [序號]\r\n"
				+ "      ,[裁罰日期]\r\n"
				+ "      ,[裁罰金額]\r\n"
				+ "      ,[受處分人]\r\n"
				+ "      ,[違反法令]\r\n"
				+ "  FROM [work].[dbo].[punishlist]";
		try (Statement stmt = conn.createStatement();){
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {		
			Punish p=new Punish();
			p.setPunishId(rs.getInt(1));
			p.setPunishDate(rs.getDate(2));
			p.setPunishPrice(rs.getInt(3));
			p.setPunishName(rs.getString(4));
			p.setLaw(rs.getString(5));
			punishs.add(p);//將所有資料加入punishs動態陣列
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}
		return punishs;//回傳所有資料的查詢結果
	}

	@Override
	public boolean updatePunish(Punish punish) {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		String sql="UPDATE [dbo].[punishlist]\r\n"
				+ "   SET [序號] = ?\r\n"
				+ "      ,[裁罰日期] = ?\r\n"
				+ "      ,[裁罰金額] = ?\r\n"
				+ "      ,[受處分人] = ?\r\n"
				+ "      ,[違反法令] = ?\r\n"
				+ " WHERE [序號]=?";
		try (PreparedStatement pstmt=conn.prepareStatement(sql);){
			pstmt.setInt(1,punish.getPunishId());
			Date d=new Date(punish.getPunishDate().getTime());//轉型
			pstmt.setDate(2,d);
			pstmt.setInt(3,punish.getPunishPrice());
			pstmt.setString(4,punish.getPunishName());
			pstmt.setString(5,punish.getLaw());
			pstmt.setInt(6,punish.getPunishId());
						
			int u=pstmt.executeUpdate();
			return u>0; //確認是否修改成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}
		
		return false;
	}

	@Override
	public boolean deletePunishById(int id) {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		String sql="DELETE FROM [dbo].[punishlist]\r\n"
				+ "      WHERE [序號]=?";
		try (PreparedStatement pstmt=conn.prepareStatement(sql);){
			pstmt.setInt(1, id);
			int d=pstmt.executeUpdate();
			return d > 0;//確認是否成功刪除
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connUtil.close(conn);
		}
		
		return false;
	}

}
