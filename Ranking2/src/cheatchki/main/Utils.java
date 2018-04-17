package cheatchki.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import cheatchki.main.info.BuyablePermission;
import cheatchki.main.info.RankLadder;
import cheatchki.main.managers.BuyablePermissionManager;
import cheatchki.main.managers.ConfigManager;
import cheatchki.main.managers.EconomyFileManager;
import cheatchki.main.managers.MenuManager;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utils {
	
	private static List<String> groups;
	
	public static List<String> getRankLaddersGroups() {
		if (groups == null) 
			return getRankLaddersGroups(EconomyFileManager.getInstance().getConfig().getString("DefaultPath"));
		return groups;
	}
	
	public static List<String> getRankLaddersGroups(String rankLadder) {
		if (groups == null) {
			Ranking.getInstance().getLogger().info("Generating " + rankLadder);
			Map<Integer, PermissionGroup> ranks = PermissionsEx.getPermissionManager().getRankLadder(rankLadder);
			groups = new ArrayList<String>();
			for (int i = 1000; i > 0; i--)
				try {
					groups.add(ranks.get(i).getName());
				} catch (NullPointerException e) {}
		}
		return groups;
	}
	
	public static boolean playerInGroup(Player p, String group) {
		for (int i = getRankLaddersGroups().size() - 1; i >= 0; i--) {
			String s = getRankLaddersGroups().get(i);
//			Ranking.getInstance().getLogger().warning(group + " " + s);
			if (Ranking.getPermission().playerInGroup(p, s)) {
				return true;
			}
			if (s.equals(group)) {
				return false;
			}
		}
		return false;
	}
	
	public static int getHighestGroup(Player p) {
		for (int i = getRankLaddersGroups().size() - 1; i >= 0; i--) {
			if (Ranking.getPermission().playerInGroup(p, getRankLaddersGroups().get(i)))
				return i;
		}
		return -1;
	}
	
	public static void advancePlayer(Player p) {
		int highestGroup = getHighestGroup(p);
		if (highestGroup < getRankLaddersGroups().size()-1) {
			boolean rankup = true;
			for (int i = 0; i < 7; i++) {
				BuyablePermission b = BuyablePermissionManager.get(RankLadder.get().get(highestGroup), i);
				if (!Ranking.getPermission().playerHas(p, b.getPermission())) {
					rankup = false;
				}
			}
			if (rankup) {
				for (int i = 0; i < 7; i++) {
					BuyablePermission b = BuyablePermissionManager.get(RankLadder.get().get(highestGroup), i);
					Ranking.getPermission().playerRemove(null, p, b.getPermission());
				}
				Ranking.getPermission().playerRemoveGroup(null, p, RankLadder.get().get(highestGroup));
				Ranking.getPermission().playerAddGroup(null, p, RankLadder.get().get(highestGroup + 1));
				MenuManager.getInstance().addMenu(p);
				ConfigManager.getInstance().runCommand(p, RankLadder.get().get(highestGroup + 1));
			}
		}
	}
}
