package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cheatchki.main.information.Items;

public class FrameButton extends Button {

	@Override
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(true);
	}

	@Override
	public void draw(Inventory i, Player p) {
//		ItemStack frame = new ItemStack
		i.setItem(slot, Items.getFrame());
	}

}
