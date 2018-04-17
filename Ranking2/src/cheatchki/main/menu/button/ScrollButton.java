package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cheatchki.main.ItemGenerator;
import cheatchki.main.info.RankLadder;
import cheatchki.main.managers.MenuManager;

public class ScrollButton extends Button {

	private boolean up;
	private int toPage;
	
	public ScrollButton(boolean up, int curPage) {
		this.up = up;
		this.toPage = curPage + (up ? -1 : 1);
	}
	
	@Override
	public void click(InventoryClickEvent e) {
		e.setCancelled(true);
		MenuManager.getInstance().addMenu((Player) e.getWhoClicked(), toPage);
	}

	@Override
	public ItemStack getItem(Player p) {
		return ItemGenerator.getInstance().getScrollButton(up, RankLadder.get().get(toPage));
	}

}
