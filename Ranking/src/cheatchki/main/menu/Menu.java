package cheatchki.main.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cheatchki.main.Options;
import cheatchki.main.Ranking;
import cheatchki.main.information.Default;
import cheatchki.main.information.Groups;
import cheatchki.main.information.Items;
import cheatchki.main.menu.button.Button;
import cheatchki.main.menu.button.PermissionButton;
import cheatchki.main.menu.button.ScrollButton;

public class Menu {
	private int page;
	private Player player;
	private Inventory inventory;
	
	private Button[] buttons;
	
	public Menu(Player player, int page) {
		this.page = page;
		this.player = player;
		Groups g;
		if ((g = Groups.getGroup(page)) == null) return;
		this.inventory = Ranking.getInstance().getServer().createInventory(player, Options.inventorySize, "Rank: " + g.name());
		loadButtons();
		loadInventory();
		player.openInventory(inventory);
	}
	
	private void loadButtons() {
		buttons = new Button[Options.inventorySize];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = Default.getButton(i);
			buttons[i].setSlot(i);
			if (buttons[i] instanceof PermissionButton) {
				int relPage = Default.getRelativePage(page, i);
				((PermissionButton) buttons[i]).setRelativeGroup(Groups.getGroup(relPage));
			} else if (buttons[i] instanceof ScrollButton) {
				boolean up = Default.getRelativePage(1, i) == 0;
				((ScrollButton) buttons[i]).setup(up, page);
			}
		}
		
		
		
//		buttons = new Button[27];
//		for (int x = -1; x < 2; x++) {
//			Groups b = Groups.getGroup(page+x);
//			if (b == null) continue;
//			for (int y = 0; y < b.getPermSize(); y++) {
//				int index = (x + 1) * 9 + y;
//				buttons[index] = new PermissionButton(b, index);
//			}
//		}
	}
	
	private void loadInventory() {
		for (Button b : buttons) {
			if (b != null)
				b.draw(inventory, player);
		}
	}
	
	public void clickEvent(InventoryClickEvent e) {
		int slot = e.getRawSlot();
//		Ranking.getInstance().getLogger().warning("Raw: " + e.getRawSlot() + " base: " + e.getSlot());
		if (slot >= 0 && slot < Options.inventorySize)
			if (buttons[slot] != null)
				buttons[slot].onClick(e);
	}
}
