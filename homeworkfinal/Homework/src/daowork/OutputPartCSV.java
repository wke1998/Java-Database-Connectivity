package daowork;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import daowork.impl.PunishDAO;

public class OutputPartCSV {

    public static void main(String[] args) {
        ConnUtil connUtil = new ConnUtil();
        int selectId = Integer.parseInt(JOptionPane.showInputDialog("請輸入查詢序號"));
        IPunishlistDAO p = new PunishDAO();
        Punish punish = p.findPunishById(selectId);

        if (punish!= null) {
            try {
                FileWriter csvWriter = new FileWriter("query" + selectId + ".csv");//生成查詢ID的CSV

                csvWriter.append("序號,裁罰日期,裁罰金額,受處分人,違反法令");//創造標題
                csvWriter.append("\n");

                String id = String.valueOf(punish.getPunishId());
                String formatDate = punish.getPunishDate().toString();
                String formatPrice = String.format("%,d", punish.getPunishPrice());//把千分位還原回來
                String name = punish.getPunishName();
                String law = punish.getLaw();

                csvWriter.append(id).append(",").append(formatDate).append(",")
                        .append(formatPrice).append(",").append(name).append(",").append(law);
                csvWriter.append("\n");

                System.out.println("CSV導出成功");
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("查無資料");
        }
    }
}
