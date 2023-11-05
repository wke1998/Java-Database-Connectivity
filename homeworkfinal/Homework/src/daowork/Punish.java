package daowork;
import java.util.Date;

public class Punish {
		//封裝數據
	    private int punishId;
	    private Date punishDate;
	    private int punishPrice;
	    private String punishName;
	    private String law;	    

		public int getPunishId() {
			return punishId;
		}
		public void setPunishId(int punishId) {
			this.punishId = punishId;
		}
		public Date getPunishDate() {
			return punishDate;
		}
		public void setPunishDate(Date punishDate) {
			this.punishDate = punishDate;
		}
		public int getPunishPrice() {
			return punishPrice;
		}
		public void setPunishPrice(int punishPrice) {
			this.punishPrice = punishPrice;
		}
		public String getPunishName() {
			return punishName;
		}
		public void setPunishName(String punishName) {
			this.punishName = punishName;
		}
		public String getLaw() {
			return law;
		}
		public void setLaw(String law) {
			this.law = law;
		}
}
