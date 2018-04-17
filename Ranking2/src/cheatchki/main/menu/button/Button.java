package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Button {
	public abstract void click(InventoryClickEvent e);
	
	public abstract ItemStack getItem(Player p);
}
