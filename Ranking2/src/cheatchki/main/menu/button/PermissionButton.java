package cheatchki.main.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cheatchki.main.ItemGenerator;
import cheatchki.main.Ranking;
import cheatchki.main.Utils;
import cheatchki.main.info.BuyablePermission;
import cheatchki.main.info.RankLadder;
import cheatchki.main.managers.BuyablePermissionManager;
import cheatchki.main.managers.MenuManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class PermissionButton extends Button {

	private int pageOffset;
	private int page;
	private int slot;
	
	public PermissionButton(int offset, int page, int slot) {
		this.pageOffset = offset;
		this.page = page;
		this.slot = slot;
	}
	
	public BuyablePermission get() {
		return BuyablePermissionManager.get(RankLadder.get().get(page + pageOffset), slot%9);
	}

	/* Click event
	 * 
	 * check if player doesn't have it unlocked already
	 * check if player has money
	 * if so take it, and add the permission, then run a perm upgrade check
	 * then update the button itself
	 *
	 * needs to be able to:
	 * get the player 
	 * check if player has a permission 
	 * 		hook into permission manager 
	 * check if the player has the money 
	 * 		hook into economy manager 
	 * take the money from the player
	 * add a permission to a player
	 * run a permissions check - 
	 * access to the gui 
	 * 
	 * needed variables
	 * 		player	- get from event
	 * 		permission needed - from buyable permission
	 * 		players balance - uses player and main class function
	 * 		money needed - from buyable permission
	 * 		gui reference - 
	 *
	 */ 
	
	@Override
	public void click(InventoryClickEvent e) {
		e.setCancelled(true);
		Economy eco = Ranking.getEconomy();
		Permission perm = Ranking.getPermission();
		
		Player player = (Player) e.getWhoClicked();
		String required = get().getRequirements();
		String permGiven = get().getPermission();
		Double priceRequired = get().getPrice();
		
		if (!perm.playerHas(player, permGiven)) {
			if (perm.playerInGroup(player, required)) {
				if (eco.getBalance(player) >= priceRequired) {
					eco.withdrawPlayer(player, priceRequired);
					perm.playerAdd(null, player, permGiven);
				}
			}
			MenuManager.getInstance().addMenu(player, page);
		}
		Utils.advancePlayer(player);
	}
	
	
	/* to set the slot of inventory to item
	 * 		get the item (see below)
	 * 		set a slot
	 * 			access to inventory
	 * 
	 * base replace variables
	 * permission
	 * requirements
	 * price
	 * decription
	 * 
	 * available modifeirs
	 * offset(offset)
	 * availability
	 * 
	 */
	@Override
	public ItemStack getItem(Player p) {
		ItemGenerator IG = ItemGenerator.getInstance();
		
		boolean hasPerm = Ranking.getPermission().playerHas(p, get().getPermission());
		boolean hasGroup = Utils.playerInGroup(p, get().getRequirements());
		return IG.getPermissionItem(hasGroup, hasPerm, pageOffset, get());
	}

}
