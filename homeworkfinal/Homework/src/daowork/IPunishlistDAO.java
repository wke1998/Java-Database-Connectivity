package daowork;
import java.util.List;

public interface IPunishlistDAO {
		//C建立
		boolean createPunish(Punish punish);
		//R讀取
		Punish findPunishById(int punishId);//讀取單一資料
		List<Punish> findAllPunish();//讀取全部資料
		//U更新
		boolean updatePunish(Punish punish);
		//D刪除
		boolean deletePunishById(int punishId);

}
