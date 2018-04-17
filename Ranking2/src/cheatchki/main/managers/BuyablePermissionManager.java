package cheatchki.main.managers;

import java.util.HashMap;
import java.util.Map;

import cheatchki.main.info.BuyablePermission;
import cheatchki.main.info.RankLadder;

/** 
 * this is basically an extenssion of the EcconomyFileManager
 * as it loads all the data from there into a 2d map of
 * buyablePermissions that way each time a buyable perm is 
 * required it doesn't need to be re-constructed
 * 
 * @author deedp
 *
 */

public class BuyablePermissionManager {
	private static Map<String, Map<Integer, BuyablePermission>> rankInformation;
	public static void init() {
		rankInformation = new HashMap<String, Map<Integer, BuyablePermission>>();
		
		for (String groupName : RankLadder.get()) {
			rankInformation.put(groupName, create(groupName));
		}
	}
	
	public static BuyablePermission get(String groupName, int slot) {
		return rankInformation.get(groupName).get(slot);
	}
	
	public static Map<Integer, BuyablePermission> create(String groupName) {
		Map<Integer, BuyablePermission> returnGroupMap = new HashMap<Integer, BuyablePermission>();
		for (int slot = 0; slot < 7; slot++) {
			returnGroupMap.put(slot, create(groupName, slot));
		}
		return returnGroupMap;
	}
	
	public static BuyablePermission create(String groupName, int slot) {
		BuyablePermission returnPerm = new BuyablePermission();
		returnPerm.setDescription(	EconomyFileManager.getInstance().getDescription(groupName, slot));
		returnPerm.setPermission(	EconomyFileManager.getInstance().getPermission(groupName, slot));
		returnPerm.setPrice(		EconomyFileManager.getInstance().getPrice(groupName, slot));
		returnPerm.setRequirements(	EconomyFileManager.getInstance().getRequirements(groupName, slot));
		return returnPerm;
	}
}
