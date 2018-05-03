package cheatchki.main;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cheatchki.main.managers.BuyablePermissionManager;
import cheatchki.main.managers.CommandManager;
import cheatchki.main.managers.ConfigManager;
import cheatchki.main.managers.EconomyFileManager;
import cheatchki.main.managers.EventManager;
import cheatchki.main.managers.GUIFileManager;
import cheatchki.main.managers.ItemFileManager;
import cheatchki.main.managers.MenuManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Ranking extends JavaPlugin {
	
	private static Ranking instance;
	private static Permission perms;
	private static Economy eco;
	
	
	@Override
	public void onEnable() {
		instance = this;

		perms = Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();
		eco = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
		
		EconomyFileManager.init();
		BuyablePermissionManager.init();
		ItemFileManager.init();
		GUIFileManager.init();
		ConfigManager.init();
		
		EventManager.init();
		CommandManager.init();
		MenuManager.init();
		
		ItemGenerator.init();
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Ranking getInstance() {
		return instance;
	}
	
	public static Permission getPermission() {
		return perms;
	}
	
	public static Economy getEconomy() {
		return eco;
	}
}
