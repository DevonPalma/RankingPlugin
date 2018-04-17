package cheatchki.main.managers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import cheatchki.main.Ranking;

public class EventManager implements Listener {
	
	//---------------------------------------------------
	
	private static EventManager instance;
	
	public static void init() {
		Ranking.getInstance().getServer().getPluginManager().registerEvents(new EventManager(), Ranking.getInstance());
	}
	
	public static EventManager getInstance() {
		return instance;
	}
	
	public EventManager() {
		instance = this;
	}

	//---------------------------------------------------
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		MenuManager.getInstance().closeEvent(e);
	}
	
	@EventHandler
	public void onInvClicked(InventoryClickEvent e) {
		MenuManager.getInstance().clickEvent(e);
	}
}
