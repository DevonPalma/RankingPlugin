package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import cheatchki.main.information.Groups;
import cheatchki.main.information.Items;

public class PermissionButton extends Button {

	private boolean hovered;
	protected transient Groups relativeGroup;
	
	public PermissionButton(Groups relativeGroup, int slot, boolean hovered) {
		super(slot);
		this.setHovered(hovered);
		this.relativeGroup = relativeGroup;
	}
	
	public PermissionButton(boolean hovered) {
		super();
		this.setHovered(hovered);
	}
	
	@Override
	public void onClick(InventoryClickEvent e) {
		e.setCancelled(true);
		
		
	}

	@Override
	public void draw(Inventory i, Player p) {
		if (relativeGroup != null)
			i.setItem(slot, Items.getPermissionItem(p, relativeGroup.getBuyablePermission(slot%9), hovered));
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
	
}
