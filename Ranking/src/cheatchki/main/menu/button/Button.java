package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class Button {
	
	public Button(int slot) {
		this.slot = slot;
	}
	
	public Button() {}
	
	protected int slot;
	

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public abstract void onClick(InventoryClickEvent e);
	
	public abstract void draw(Inventory i, Player p);
}
