package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cheatchki.main.ItemGenerator;
import cheatchki.main.Utils;
import cheatchki.main.managers.MenuManager;

public class FocusButton extends Button {

	@Override
	public void click(InventoryClickEvent e) {
		e.setCancelled(true);
		MenuManager.getInstance().addMenu((Player) e.getWhoClicked());
	}

	@Override
	public ItemStack getItem(Player p) {
		return ItemGenerator.getInstance().getFocusItem(Utils.getRankLaddersGroups().get(Utils.getHighestGroup(p)));
	}

}
