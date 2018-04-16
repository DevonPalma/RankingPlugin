package cheatchki.main.managers.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cheatchki.main.Ranking;
import cheatchki.main.menu.SlotType;

public class ItemsFileManager {
	
	private static File file;
	private static FileConfiguration config;
	private static Map<String, String> conversionMap;
	
	public static void init() {
		setupfiles();
		setupDefaults();
		createConversionMap();
		save();
	}
	
	public static void setupfiles() {
		file = new File(Ranking.getInstance().getDataFolder(), "iteminfo.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public static void setupDefaults() {
		//----------------------------------------------------------------
		config.addDefault("PermissionButton.Item.material.outerRow", "STAINED_GLASS");
		config.addDefault("PermissionButton.Item.material.innerRow", "STAINED_GLASS_PANE");
		
		config.addDefault("PermissionButton.Item.name", "%varName%");
		List<String> permLore = new ArrayList<String>();
		permLore.add("&6Requires:");
		permLore.add("&6 - %varGroup%");
		permLore.add("&6Gives:");
		permLore.add("&6 - %varPerm%");
		config.addDefault("PermissionButton.Item.lore", permLore);

		config.addDefault("PermissionButton.Variables.varName.hasBoth", "%varName-hasPerm%");
		config.addDefault("PermissionButton.Variables.varName.hasPerm", "&a&lOwned");
		config.addDefault("PermissionButton.Variables.varName.hasGroup", "&e&lAvailable");
		config.addDefault("PermissionButton.Variables.varName.hasNeither", "&c&lBlocked");

		config.addDefault("PermissionButton.Variables.varGroup.hasBoth", "%varGroup-hasGroup%");
		config.addDefault("PermissionButton.Variables.varGroup.hasPerm", "%varGroup-hasNeither%");
		config.addDefault("PermissionButton.Variables.varGroup.hasGroup", "&a%requiredgroup%");
		config.addDefault("PermissionButton.Variables.varGroup.hasNeither", "&c%requiredgroup%");

		config.addDefault("PermissionButton.Variables.varPerm.hasBoth", "%varPerm-hasPerm%");
		config.addDefault("PermissionButton.Variables.varPerm.hasPerm", "&a%permission%");
		config.addDefault("PermissionButton.Variables.varPerm.hasGroup", "%varPerm-hasNeither%");
		config.addDefault("PermissionButton.Variables.varPerm.hasNeither", "&c%permission%");
		
		//----------------------------------------------------------------
		
		config.addDefault("FrameButton.Item.material", "STAINED_GLASS");
		config.addDefault("FrameButton.Item.name", "&f");

		//----------------------------------------------------------------
		
		config.addDefault("ScrollButton.Item.material", "NETHER_STAR");
		config.addDefault("ScrollButton.Item.name", "&6Scroll %direction%");
		List<String> scrollLore = new ArrayList<String>();
		scrollLore.add("&6To: %pageName%");
		config.addDefault("ScrollButton.Item.lore", scrollLore);
		
		//----------------------------------------------------------------
		
		config.options().copyDefaults(true);
	}
	
	public static void createConversionMap() {
		conversionMap = new HashMap<String, String>();
		conversionMap.put("&", "§");
		for (String s : config.getConfigurationSection("PermissionButton.Variables").getKeys(false)) {
			conversionMap.put("%" + s + "%", "%" + s + "-%varType%%");
			for (String t : config.getConfigurationSection("PermissionButton.Variables." + s).getKeys(false)) {
				conversionMap.put("%" + s + "-" + t + "%", config.getString("PermissionButton.Variables." + s + "." + t));
			}
		}
//		for (String s : conversionMap.keySet()) {
//			Ranking.getInstance().getLogger().warning(s + "   -   " + conversionMap.get(s));
//		}
	}
	
	public static void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	//----------------------------------
	
	
	public static Material getMaterial(int slotType) {
		switch(slotType) {
		case SlotType.PERMISSION_HOVERED:
			return Material.getMaterial(config.getString("PermissionButton.Item.material.innerRow"));
		case SlotType.PERMISSION_UNHOVERED:
			return Material.getMaterial(config.getString("PermissionButton.Item.material.outerRow"));
		case SlotType.FRAME:
			return Material.getMaterial(config.getString("FrameButton.Item.material"));
		case SlotType.SCROLL:
			return Material.getMaterial(config.getString("ScrollButton.Item.material"));
		}
		return null;
	}

	public static String getPermName(boolean hasGroup, boolean hasPerm) {
		return convert(new String(config.getString("PermissionButton.Item.name")), hasGroup, hasPerm);
	}
	public static List<String> getPermLore(boolean hasGroup, boolean hasPerm) {
		List<String> lore = (List<String>) config.getList("PermissionButton.Item.lore");
		List<String> returnLore = new ArrayList<String>();
		for (int i = 0; i < lore.size(); i++) {
			returnLore.add(i, convert(new String(lore.get(i)), hasGroup, hasPerm));
		}
		return returnLore;
	}

	
	public static String getName(int slotType) {
		String returnName = "";
		switch(slotType) {
		case SlotType.FRAME:
			returnName = config.getString("FrameButton.Item.name");
			break;
		case SlotType.SCROLL:
			returnName = config.getString("ScrollButton.Item.name");
			break;
		}
		return new String(returnName).replaceAll("&", "§");
	}
	
	public static List<String> getLore(int slotType) {
		List<String> lore = null;
		switch(slotType) {
		case SlotType.SCROLL:
			lore = (List<String>) config.getList("ScrollButton.Item.lore");
			break;
		}
		
		List<String> returnLore = new ArrayList<String>();
		for (int i = 0; i < lore.size(); i++) {
			returnLore.add(i, new String(lore.get(i).replaceAll("&", "§")));
		}
		return returnLore;
	}
	
	//----------------------------------
	
 	public static String convert(String toBeConverted, boolean hasgroup, boolean hasperm) {
		return convert(toBeConverted, convert(hasgroup, hasperm));
	}
	
	public static String convert(String toBeConverted, String varType) {
		conversionMap.put("%varType%", varType);
		boolean finished = false;
		int counter = 0;
		while (!finished && counter < 30) {
			counter++;
			finished = true;
			for (String s : conversionMap.keySet()) {
				if (toBeConverted.contains(s)) {
					finished = false;
					toBeConverted = toBeConverted.replaceAll(s, conversionMap.get(s));
				}
			}
		}
		conversionMap.remove("%varType%");
		return toBeConverted;
	}
	
	public static String convert(boolean hasgroup, boolean hasperm) {
		if (hasgroup) {
			if (hasperm) {
				return "hasBoth";
			} else {
				return "hasGroup";
			}
		} else {
			if (hasperm) {
				return "hasPerm";
			} else {
				return "hasNeither";
			}
		}
	}
}
