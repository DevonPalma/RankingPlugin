package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cheatchki.main.ItemGenerator;

public class FrameButton extends Button {

	@Override
	public void click(InventoryClickEvent e) {
		e.setCancelled(true);
	}

	@Override
	public ItemStack getItem(Player p) {
		return ItemGenerator.getInstance().getFrameItem();
	}

}
