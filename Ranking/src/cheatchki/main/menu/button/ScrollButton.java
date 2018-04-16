package cheatchki.main.menu.button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import cheatchki.main.information.Groups;
import cheatchki.main.information.Items;
import cheatchki.main.managers.MenuManager;

public class ScrollButton extends Button {

	private boolean up;
	private int toPage;
	
	public ScrollButton() {
	}
	
	public void setup(boolean up, int curPage) {
		this.up = up;
		if (up && curPage <= 0)
			toPage = -1;
		else if (!up && curPage >= Groups.values().length - 1)
			toPage = -1;
		else
			this.toPage = curPage + (up ? -1 : 1);
	}
	
	@Override
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(true);
		if (toPage != -1) 
			MenuManager.getInstance().addMenu((Player) e.getWhoClicked(), toPage);
	}

	@Override
	public void draw(Inventory i, Player p) {
		if (toPage != -1) {
			i.setItem(slot, Items.getScroll(up, toPage));
		}
	}

}
