package cheatchki.main.managers.files;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cheatchki.main.Ranking;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RanksFileManager {
	private static File file;
	private static FileConfiguration config;
	
	
	public static void init() {
		setupfiles();
		setupDefaults();
		save();
	}
	
	public static void setupfiles() {
		file = new File(Ranking.getInstance().getDataFolder(), "ranks.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public static void setupDefaults() {
		Map<Integer, PermissionGroup> groups = PermissionsEx.getPermissionManager().getRankLadder("basic");
		
		for (int i = 1000; i > 0; i--) {
			PermissionGroup group = groups.get(i);
			if (group != null) {
				for (int x = 0; x < 7; x++) {
					config.addDefault(toString(group.getName(), x, "enabled"), true);
					config.addDefault(toString(group.getName(), x, "permission"), group.getName() + ".test" + x);
					config.addDefault(toString(group.getName(), x, "description"), "/EmptyPerm");
					config.addDefault(toString(group.getName(), x, "price"), (1000.0 - i) * 10 + x);
				}
			}
		}
		config.options().copyDefaults(true);
	}
	
	public static void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static String toString(String groupName, int slot, String option) {
		return groupName + ".s" + slot + "." + option;
	}
	
	public static boolean getEnabled(String groupName, int slot) {
		return config.getBoolean(toString(groupName, slot, "enabled"));
	}
	
	public static String getPermission(String groupName, int slot) {
		return config.getString(toString(groupName, slot, "permission"));
	}
	
	public static String getDescription(String groupName, int slot) {
		return config.getString(toString(groupName, slot, "description"));
	}
	
	public static double getPrice(String groupName, int slot) {
		return config.getDouble(toString(groupName, slot, "price"));
	}
}
