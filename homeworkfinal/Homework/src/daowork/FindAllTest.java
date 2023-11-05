package daowork;

import java.util.List;
import daowork.Punish;
import daowork.IPunishlistDAO;
import daowork.impl.PunishDAO;

public class FindAllTest {

	public static void main(String[] args) {
		IPunishlistDAO p=new PunishDAO();
		List<Punish> findAllPunish=p.findAllPunish();
		for(Punish punish:findAllPunish) {//foreach迴圈讀出來
			System.out.print(punish.getPunishId()+" ");
			System.out.print(punish.getPunishDate()+" ");
			System.out.print(punish.getPunishPrice()+" ");
			System.out.print(punish.getPunishName()+" ");
			System.out.println(punish.getLaw());
			
		}
	}

}
