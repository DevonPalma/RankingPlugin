package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cheatchki.main.Ranking;
import cheatchki.main.Utils;
import cheatchki.main.information.Groups;
import cheatchki.main.information.Items;
import cheatchki.main.managers.MenuManager;

public class PermissionButton extends Button {

	private boolean hovered;
	protected transient Groups relativeGroup;
	private int spot;
	
	private int currentPage;
	
	public PermissionButton(Groups relativeGroup, int slot, boolean hovered) {
		super(slot);
		this.setHovered(hovered);
		this.relativeGroup = relativeGroup;
		spot = slot%9;
	}
	
	public PermissionButton(boolean hovered) {
		super();
		this.setHovered(hovered);
	}
	
	@Override
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(true);
		if (!Utils.playerInGroup((Player)e.getWhoClicked(), relativeGroup.name()))
			return;
		
		String permission = relativeGroup.getBuyablePermission(spot).getPermission();
		if (!Ranking.getPermission().playerHas((Player) e.getWhoClicked(), permission)) {
//			e.getWhoClicked().sendMessage("Why buy " + relativeGroup.getBuyablePermission(spot).getPermission());
			double price = relativeGroup.getBuyablePermission(spot).getPrice();
			if (Ranking.getEconomy().getBalance((Player) e.getWhoClicked()) >= price) {
				Ranking.getEconomy().withdrawPlayer((Player) e.getWhoClicked(), price);
				Ranking.getPermission().playerAdd((Player) e.getWhoClicked(), permission);
				e.getWhoClicked().sendMessage("You have bought \"" + permission + "\" for " + price);
				MenuManager.getInstance().addMenu((Player) e.getWhoClicked(), currentPage);
			}
		}
		
		
	}

	@Override
	public void draw(Inventory i, Player p) {
		if (relativeGroup != null)
			i.setItem(slot, Items.getPermissionItem(p, relativeGroup.getBuyablePermission(spot), hovered));
		else
			i.clear(slot);
	}

	public Groups getRelativeGroup() {
		return relativeGroup;
	}

	public void setRelativeGroup(Groups relativeGroup) {
		this.relativeGroup = relativeGroup;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	
	@Override
	public void setSlot(int slot) {
		super.setSlot(slot);
		spot = slot % 9;
	}
	
	public void setCurrentPage(int page) {
		this.currentPage = page;
	}
	
}
