package cheatchki.main.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import cheatchki.main.Ranking;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankLadder {
	
	public static String pexRankLadder;
	
	public static List<String> rankLadder;
	
	public static List<String> get() {
		if (rankLadder == null) {
			rankLadder = new ArrayList<String>();
			Map<Integer, PermissionGroup> groupMap = PermissionsEx.getPermissionManager().getRankLadder(pexRankLadder);
			for (int i = 1000; i > 0; i--) {
				PermissionGroup temp = groupMap.get(i);
				if (temp != null)
					rankLadder.add(temp.getName());
			}
		}
		return rankLadder;
	}
	
	public static int getHighestGroup(Player player) {
		for(int i = get().size() - 1; i >= 0; i++) {
			if (Ranking.getPermission().playerInGroup(player, get().get(i)))
				return i;
		}
		return -1;
	}
}
