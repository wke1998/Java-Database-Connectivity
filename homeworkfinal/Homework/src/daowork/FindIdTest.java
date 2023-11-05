package daowork;

import java.util.List;

import javax.swing.JOptionPane;

import daowork.impl.PunishDAO;

public class FindIdTest {

	public static void main(String[] args) {
		IPunishlistDAO p=new PunishDAO();
		Punish punish=p.findPunishById
				(Integer.parseInt(JOptionPane.showInputDialog("請輸入查詢序號")));
			System.out.println("序號："+punish.getPunishId());
			System.out.println("裁罰日期："+punish.getPunishDate());
			System.out.println("裁罰金額："+punish.getPunishPrice());
			System.out.println("受處分人姓名："+punish.getPunishName());
			System.out.println("違反法令："+punish.getLaw());
	}

}
