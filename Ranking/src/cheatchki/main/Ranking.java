package cheatchki.main;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cheatchki.main.managers.CommandManager;
import cheatchki.main.managers.EventManager;
import cheatchki.main.managers.MenuManager;
import cheatchki.main.managers.files.ItemsFileManager;
import cheatchki.main.managers.files.RanksFileManager;
import net.milkbowl.vault.permission.Permission;

public class Ranking extends JavaPlugin {
	
	private static Ranking instance;
	private static Permission perms;
	
	
	public void onEnable() {
		instance = this;
		CommandManager.init();
		EventManager.init();
		MenuManager.init();
		RanksFileManager.init();
		ItemsFileManager.init();
		perms = Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();
	}
	
	public void onDisable() {
		
	}
	
	public static Ranking getInstance() {
		return instance;
	}
	
	public static Permission getPermission() {
		return perms;
	}
}
