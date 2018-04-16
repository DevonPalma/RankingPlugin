package cheatchki.main.information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cheatchki.main.Utils;
import cheatchki.main.managers.files.ItemsFileManager;
import cheatchki.main.menu.SlotType;

/** this is the generator for items which are all defined in the iteminfo.yml
 * 
 * @author deedp
 *
 */

public class Items {
	
	
	public static ItemStack getPermissionItem(Player p, BuyablePermission perm, boolean hovering) {
		
		Map<String, String> conversionMap = new HashMap<String, String>();
		conversionMap.put("%group%", perm.getRequiredGroup());
		conversionMap.put("%permission%", perm.getPermission());
		conversionMap.put("%price%", Double.toString(perm.getPrice()));
		conversionMap.put("%description%", perm.getDescription());
		
		// if the player has the permission offered by the item
		boolean hasPerm = p.hasPermission(perm.getPermission());
		
		// if the player is in the needed group
		boolean hasGroup = Utils.playerInGroup(p, perm.getRequiredGroup());
		
		ItemStack returnItem = new ItemStack(ItemsFileManager.getMaterial(hovering ? SlotType.PERMISSION_HOVERED : SlotType.PERMISSION_UNHOVERED), 1, (byte) getGlassCol(hasPerm, hasGroup));
		
		ItemMeta meta = returnItem.getItemMeta();
		meta.setDisplayName(convert(ItemsFileManager.getPermName(hasGroup, hasPerm), conversionMap));
		
		List<String> lore = ItemsFileManager.getPermLore(hasGroup, hasPerm);
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, convert(lore.get(i), conversionMap));
		}
		
		meta.setLore(lore);
		
		returnItem.setItemMeta(meta);
		
		return returnItem;
		
	}
	
	public static int getGlassCol(boolean hasPerm, boolean hasGroup) {
		if (hasPerm) {
			return 5;
		} else {
			if (hasGroup) {
				return 4;
			} else {
				return 14;
			}
		}
	}
	
	public static String convert(String replace, Map<String, String> conversionMap) {
		for (String s : conversionMap.keySet()) {
			if (replace.contains(s)) {
				replace = replace.replaceAll(s, conversionMap.get(s));
			}
		}
		return replace;
	}
	
	public static ItemStack getFrame() {
		ItemStack returnItem = new ItemStack(ItemsFileManager.getMaterial(SlotType.FRAME), 1, (byte) 7);
		ItemMeta meta = returnItem.getItemMeta();
		meta.setDisplayName(ItemsFileManager.getName(SlotType.FRAME));
		returnItem.setItemMeta(meta);
		return returnItem;
	}
	
	public static ItemStack getScroll(boolean up, int toPage) {
		Map<String, String> conversionMap = new HashMap<String, String>();
		conversionMap.put("%direction%", up ? "up" : "down");
		conversionMap.put("%pageName%", Groups.getGroup(toPage).name());
		conversionMap.put("&", "§");
		
		String name = convert(ItemsFileManager.getName(SlotType.SCROLL), conversionMap);
		
		List<String> lore = ItemsFileManager.getLore(SlotType.SCROLL);
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, convert(lore.get(i), conversionMap));
		}
		
		ItemStack returnItem = new ItemStack(ItemsFileManager.getMaterial(SlotType.SCROLL));
		ItemMeta meta = returnItem.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		returnItem.setItemMeta(meta);
		
		return returnItem;
	}
}
