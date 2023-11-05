package daowork;

import javax.swing.JOptionPane;

import daowork.impl.PunishDAO;

public class DeleteTest {

	public static void main(String[] args) {
		IPunishlistDAO p=new PunishDAO();
        int id = Integer.parseInt(JOptionPane.showInputDialog("請輸入欲刪除的序號"));
        boolean isDeleted = p.deletePunishById(id);      
        if (isDeleted) {
            System.out.println("已刪除序號為"+id+"的資料");
        } else {
            System.out.println("無法刪除序號"+id+"的資料，請重新嘗試");
        }
	}

}
