package daowork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class InputCSV {

	public static void main(String[] args) {
		ConnUtil connUtil=new ConnUtil();
		Connection conn=connUtil.getConnection();
		String csvUrl="https://data.tainan.gov.tw/dataset/52b8164c-97cd-43a0-b1bb-d29597fb1b31/resource/d200d0eb-942e-445b-b4f3-fd324dab0d30/download/3c4d5ab6-a20e-45a1-9e78-1a891c700907.csv";
		String insertSql="insert into punishlist(序號,裁罰日期,裁罰金額,受處分人,違反法令) "
				+ "values(?,?,?,?,?)";
		try {
            URL csvurl = new URL(csvUrl);
            InputStream openStream = csvurl.openStream();
            InputStreamReader isr = new InputStreamReader(openStream);
            BufferedReader br = new BufferedReader(isr);

            String line;//儲存CSV文件的資料
            boolean title = true; // 用來標記是否為第一行
            while ((line = br.readLine()) != null) {//逐行讀取是不是null
                if (title) {//檢查是否為第一行
                    title = false;
                    continue; // 跳過標題行
                }
                //以逗號作為分隔，但跳過在雙引號內的逗號         
                Pattern pattern = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String[] fields = pattern.split(line);
                //使用正則表示式處理等會的金額問題
                
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                	 pstmt.setString(1, fields[0].trim());//去除空格
                    
                    // 解析日期並轉換格式
                    String dateString = fields[1];
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yy/MM/dd");//輸入進來的日期格式
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd"); // 新日期格式
                    java.util.Date dateParsed = inputFormat.parse(dateString);
                    String formattedDate = outputFormat.format(dateParsed);//將 java.util.Date 格式化為新的日期格式
                    pstmt.setString(2, formattedDate);

                    // 處理金額問題，移除引號和逗號，然後轉換為數字
                    String moneyString = fields[2].replace("\"", "").replace(",", "").trim();
                    int moneyAmount = Integer.parseInt(moneyString);
                    pstmt.setInt(3, moneyAmount);
                    //金額問題處理完畢
                    pstmt.setString(4, fields[3].trim());
                    pstmt.setString(5, fields[4].trim());
                    pstmt.executeUpdate();
                } catch (ParseException | NumberFormatException | SQLException e) {
                    e.printStackTrace();
                }
            }
            br.close();
            isr.close();
            openStream.close();
            System.out.println("資料導入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        	connUtil.close(conn);
        }

	}

}
