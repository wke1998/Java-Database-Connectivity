package daowork;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import daowork.impl.PunishDAO;

public class CreateTest {

	public static void main(String[] args) {
		IPunishlistDAO p=new PunishDAO();
		Punish punish=new Punish();
		punish.setPunishId(
				(Integer.parseInt(JOptionPane.showInputDialog("請輸入新序號"))));
		SimpleDateFormat ndate=new SimpleDateFormat("yyyy/MM/dd");//自定義日期格式
		Date d = null;
	        while (true) {
	            String inputDate = JOptionPane.showInputDialog("請輸入裁罰日期(yyyy/MM/dd)");        
	            try {
	                d = ndate.parse(inputDate);
	                break; // 輸入格式正確，跳出迴圈
	            } catch (ParseException e) {
	                JOptionPane.showMessageDialog(null, "日期格式錯誤，請輸入有效的日期格式(yyyy/MM/dd)");
	            }
	        }  
	    punish.setPunishDate(d);
		punish.setPunishPrice(Integer.parseInt(JOptionPane.showInputDialog("請輸入裁罰金額")));
		punish.setPunishName(JOptionPane.showInputDialog("請輸入受處分人姓名"));
		punish.setLaw(JOptionPane.showInputDialog("請輸入違反法令"));
		if(p.createPunish(punish)) {
			System.out.println("已成功新增序號為"+punish.getPunishId()+"的資料");
		}else {
			System.out.println("新增失敗");
		}
	}

}
