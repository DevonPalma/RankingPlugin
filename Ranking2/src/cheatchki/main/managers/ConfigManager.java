package cheatchki.main.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ConfigManager extends FileManager {

	private static ConfigManager instance;
	
	public static void init() {
		instance = new ConfigManager("config.yml");
	}
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	public ConfigManager(String fileName) {
		super(fileName);
	}

	@Override
	public void setDefaults() {
		config.addDefault("SendMessageOnRankup", true);
		config.addDefault("mesasage", "%player% has ranked up to %rank%");
	}
	
	public void runCommand(Player p, String rank) {
		if (config.getBoolean("SendMessageOnRankup")) {
			String message = config.getString("mesasage");
			message = message.replaceAll("%rank%", rank).replaceAll("%player%", p.getDisplayName()).replaceAll("&", "§");
			Bukkit.broadcastMessage(message);
		}
	}

}
