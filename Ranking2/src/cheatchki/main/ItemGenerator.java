package cheatchki.main;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cheatchki.main.info.BuyablePermission;
import cheatchki.main.managers.ItemFileManager;

public class ItemGenerator {
	
	private static ItemGenerator instance;
	
	public static void init() {
		instance = new ItemGenerator();
	}
	
	public static ItemGenerator getInstance() {
		return instance;
	}
	
	
	private BaseItem PERMISSION_ITEM;
	private BaseItem FRAME_ITEM;
	private BaseItem EMPTY_ITEM;
	private BaseItem SCROLL_ITEM;
	private BaseItem FOCUS_ITEM;
	
	
	public ItemGenerator() {
		PERMISSION_ITEM = new BaseItem("PermissionButton");
		FRAME_ITEM = new BaseItem("FrameButton");
		EMPTY_ITEM = new BaseItem("EmptyButton");
		SCROLL_ITEM = new BaseItem("ScrollButton");
		FOCUS_ITEM = new BaseItem("FocusButton");
	}
	
	public ItemStack createItem(String material, String damage, String name, String lore) {
//		Ranking.getInstance().getLogger().warning("M: " + material + " D: " + damage + " N: " + name + " L: " + lore);
		
		
		Material m = Material.getMaterial(material);
		byte d = (byte) Integer.parseInt(damage);
		
		ItemStack returnItem = new ItemStack(m, 1, d);
		ItemMeta meta = returnItem.getItemMeta();
		
		meta.setDisplayName(name);
		if (!lore.equals("")) {
			List<String> newLore = Arrays.asList(lore.split("%n%"));
			meta.setLore(newLore);
		}
		returnItem.setItemMeta(meta);
		
		return returnItem;
	}
	
	public ItemStack getPermissionItem(boolean hasGroup, boolean hasPerm, int offset, BuyablePermission perm) {
		ItemFileManager IFM = ItemFileManager.getInstance();
		
		String material = IFM.permissionConvert(PERMISSION_ITEM.material, hasGroup, hasPerm, offset, perm);
		String damage = IFM.permissionConvert(PERMISSION_ITEM.damage, hasGroup, hasPerm, offset, perm);
		String name = IFM.permissionConvert(PERMISSION_ITEM.name, hasGroup, hasPerm, offset, perm);
		String lore = IFM.permissionConvert(PERMISSION_ITEM.lore, hasGroup, hasPerm, offset, perm);
		
		return createItem(material, damage, name, lore);
	}
	
	public ItemStack getFrameItem() {
		ItemFileManager IFM = ItemFileManager.getInstance();
		
		String material = IFM.frameConvert(FRAME_ITEM.material);
		String damage = IFM.frameConvert(FRAME_ITEM.damage);
		String name = IFM.frameConvert(FRAME_ITEM.name);
		String lore = IFM.frameConvert(FRAME_ITEM.lore);
		
		return createItem(material, damage, name, lore);
	}
	
	public ItemStack getEmptyItem() {
		ItemFileManager IFM = ItemFileManager.getInstance();
		
		String material = IFM.emptyConvert(EMPTY_ITEM.material);
		String damage = IFM.emptyConvert(EMPTY_ITEM.damage);
		String name = IFM.emptyConvert(EMPTY_ITEM.name);
		String lore = IFM.emptyConvert(EMPTY_ITEM.lore);
		
		return createItem(material, damage, name, lore);
	}
	
	public ItemStack getScrollButton(boolean up, String groupName) {
		ItemFileManager IFM = ItemFileManager.getInstance();

		String material = IFM.scrollConvert(SCROLL_ITEM.material, up, groupName);
		String damage = IFM.scrollConvert(SCROLL_ITEM.damage, up, groupName);
		String name = IFM.scrollConvert(SCROLL_ITEM.name, up, groupName);
		String lore = IFM.scrollConvert(SCROLL_ITEM.lore, up, groupName);
		
		return createItem(material, damage, name, lore);
	}
	
	public ItemStack getFocusItem(String groupName) {
		ItemFileManager IFM = ItemFileManager.getInstance();
		
		String material = IFM.focusButton(FOCUS_ITEM.material, groupName);
		String damage = IFM.focusButton(FOCUS_ITEM.damage, groupName);
		String name = IFM.focusButton(FOCUS_ITEM.name, groupName);
		String lore = IFM.focusButton(FOCUS_ITEM.lore, groupName);
		
		return createItem(material, damage, name, lore);
	}
	
}

class BaseItem {
	public String name;
	public String lore;
	public String damage;
	public String material;
	
	public BaseItem(String type) {
		ItemFileManager IFM = ItemFileManager.getInstance();
		name = IFM.convert(IFM.getName(type), type);
		lore = IFM.convert(IFM.getLore(type), type);
		damage = IFM.convert(IFM.getDamage(type), type);
		material = IFM.convert(IFM.getMaterial(type), type);
	}
	
	
}