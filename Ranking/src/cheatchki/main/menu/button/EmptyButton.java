package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class EmptyButton extends Button {
	
	public EmptyButton() {
		super();
	}

	@Override
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(true);
	}

	@Override
	public void draw(Inventory i, Player p) {
		i.clear(slot);
	}

}
