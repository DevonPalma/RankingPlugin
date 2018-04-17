package cheatchki.main.menu;

import org.bukkit.entity.Player;

import cheatchki.main.Options;
import cheatchki.main.Ranking;
import cheatchki.main.info.RankLadder;
import cheatchki.main.managers.GUIFileManager;

/**
 * this is the ranking menu for all players
 * it needs to have a scroll down and scroll up function
 * 
 * @author deedp
 *
 */

public class Menu extends GUI {
	public static Menu createMenu(Player player) {
		return createMenu(player, RankLadder.getHighestGroup(player));
	}
	public static Menu createMenu(Player player, int page) {
		Menu m = new Menu();
		m.create(player, Options.SIZE, page);
		m.createButtons();
		m.show();
		return m;
	}
	
	private int page;
	
	public void create(Player p, int size, int page) {
		if (page >= 0 && page < RankLadder.get().size()) { 
			String title = RankLadder.get().get(page);
			super.create(p, size, title);
			this.page = page;
		} else {
			Ranking.getInstance().getLogger().warning("Could not create GUI as the page " + page + " is out of bounds");
		}
	}
	
	public void createButtons() {
		buttons = GUIFileManager.getInstance().getDefault(page);
		for (int i = 0; i < buttons.length; i++)  {
			loadButtonIntoInventory(i);
		}
	}
	@Override
	public void loadButtonIntoInventory(int slot) {
		
		super.loadButtonIntoInventory(slot);
	}
	
}
