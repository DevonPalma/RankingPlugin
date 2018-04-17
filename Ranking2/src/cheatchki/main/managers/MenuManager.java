package cheatchki.main.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import cheatchki.main.Utils;
import cheatchki.main.menu.Menu;

public class MenuManager {
	
	private static MenuManager instance;
	
	public static void init() {
		instance = new MenuManager();
	}
	
	public static MenuManager getInstance() {
		return instance;
	}
	
	private Map<Player, Menu> menus;
	
	public MenuManager() {
		menus = new HashMap<Player, Menu>();
	}
	
	public void addMenu(Player p) {
		addMenu(p, Utils.getHighestGroup(p));
	}
	
	public void addMenu(Player p, int page) {
		menus.put(p, Menu.createMenu(p, page));
	}
	
	public void removeMenu(Player p) {
		menus.remove(p);
	}
	
	public void clickEvent(InventoryClickEvent e) {
		Menu m = menus.get((Player) e.getWhoClicked());
		if (m != null)
			m.click(e);
	}
	
	public void closeEvent(InventoryCloseEvent e) {
		menus.remove((Player) e.getPlayer());
	}
}
