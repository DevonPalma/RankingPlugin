package cheatchki.main.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cheatchki.main.Options;
import cheatchki.main.menu.button.Button;

public abstract class GUI {
	protected Inventory inventory;
	protected Button[] buttons;
	protected String player;
	
	// inventory construction!
	
	public void create(Player p, int size, String name) {
		inventory = Bukkit.createInventory(p, size, name);
		buttons = new Button[size];
		player = p.getName();
	}
	
	public void create(String p, int size, String name) {
		create(Bukkit.getServer().getPlayer(player), size, name);
	}
	
	public void recreate(int size, String name) {
		create(player, size, name);
	}
	
	public void recreate(int size) {
		create(player, size, inventory.getTitle());
	}
	
	public void recreate(String name) {
		create(player, inventory.getSize(), name);
	}
	
	public void show() {
		Bukkit.getServer().getPlayer(player).openInventory(inventory);
	}
	
	// button construction
	
	public void loadButtonToButtons(Button button, int slot) {
		buttons[slot] = button;
	}
	
	public void loadButtonIntoInventory(int slot) {
		if (buttons[slot] != null)
			inventory.setItem(slot, buttons[slot].getItem(Bukkit.getServer().getPlayer(player)));
	}
	
	public void click(InventoryClickEvent e) {
		if (e.getRawSlot() >= 0 && e.getRawSlot() < Options.SIZE)
			buttons[e.getRawSlot()].click(e);
	}
	
}
