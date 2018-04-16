package cheatchki.main;

import org.bukkit.entity.Player;

import cheatchki.main.information.Groups;

public class Utils {
	public static boolean playerInGroup(Player p, String group) {
		for (int i = Groups.values().length - 1; i >= 0; i--) {
			if (Ranking.getPermission().playerInGroup(p, Groups.values()[i].name())) {
				return true;
			}
			if (Groups.values()[i].name().equals(group))
				return false;
		}
		return false;
	}
	
	public static int getHighestGroup(Player p) {
		for (int i = Groups.values().length - 1; i >= 0; i--) {
			String g = Groups.values()[i].name();
			if (Ranking.getPermission().playerInGroup(p, g)) 
				return i;
		}
		return -1;
	}
}
