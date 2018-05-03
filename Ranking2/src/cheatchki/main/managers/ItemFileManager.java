package cheatchki.main.managers;

import java.util.HashMap;
import java.util.Map;

import cheatchki.main.info.BuyablePermission;

/**
 * 
 * 
 * @author deedp
 *
 */

public class ItemFileManager extends FileManager {

	//------------------ Static ------------------

	private static ItemFileManager instance;
	
	public static void init() {
		instance = new ItemFileManager("items.yml");
	}

	public static ItemFileManager getInstance() {
		return instance;
	}
	
	//---------------- inheritance ---------------

	public ItemFileManager(String fileName) {
		super(fileName);
		conversionMap = new HashMap<String, Map<String, String>>();
		createMap("PermissionButton");
		createMap("FrameButton");
		createMap("EmptyButton");
		createMap("ScrollButton");
		createMap("FocusButton");
	}

	@Override
	public void setDefaults() {
		for (String s : config.getKeys(false)) {
			config.addDefault(s + ".Item.material", "%material%");
			config.addDefault(s + ".Item.damage", "%damage%");
			config.addDefault(s + ".Item.name", "%name%");
			config.addDefault(s + ".Item.lore", "%lore%");
		}
	}
	
	//---------------- conversions ---------------

	private Map<String, Map<String, String>> conversionMap;
	
	
	/* createMap(String type)
	 * this creates the variable map, which is defined in the file
	 * the variable map can only be 2d and POSSIBLY 1d but can not be
	 * any higher than that (99% sure)
	 * 
	 * first this creates the base variables and their replacements
	 * ex. %material% -> %material-base%
	 * then it creates all the sub variables
	 * ex. %material-base% -> DIAMOND
	 */
	public void createMap(String type) {
		try {
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("&", "§");
			for (String s : config.getConfigurationSection(type + ".Variables").getKeys(false)) {
				tempMap.put("%" + s + "%", "%" + s + "-base%");
				for (String s1 : config.getConfigurationSection(type + ".Variables." + s).getKeys(false)) {
					tempMap.put("%" + s + "-" + s1 + "%", config.getString(type + ".Variables." + s + "." + s1));
				}	
			}
			conversionMap.put(type, tempMap);
		} catch (NullPointerException e) {
			throw new NullPointerException("Could not create Item map for " + type);
		}
	}
	
	
	/* convert(toBeConverted, type)
	 * this loops through the entire conversion map
	 * to find a matching values then replace them with the correct
	 * replace variable.
	 * the loop ends if 1. the safety counter is reached
	 * or 2. if it can complete one full loop without finding
	 * a single match
	 */
	public String convert(String toBeConverted, String type) {
		boolean finished = false;
		int counter = 0;
		String returnString = toBeConverted;
		
		while (!finished && counter < 10) {
			finished = true;
			counter++;
			for (String s : conversionMap.get(type).keySet()) {
				if (returnString.contains(s)) {
					finished = false;
//					Ranking.getInstance().getLogger().warning(s + " -> " + conversionMap.get(type).get(s));
					returnString = returnString.replaceAll(s, conversionMap.get(type).get(s));
				}
			}
		}
		return returnString;
	}
	
	
	public String permissionConvert(String toBeConverted, boolean hasGroup, boolean hasPerm, int offset, BuyablePermission perm) {
		conversionMap.get("PermissionButton").put("%GP%", (hasPerm ? (hasGroup ? "hasBoth" : "hasPerm") : (hasGroup ? "hasGroup" : "hasNeither")));
		conversionMap.get("PermissionButton").put("%offset%", "offset" + Math.abs(offset));

		conversionMap.get("PermissionButton").put("%permission%", perm.getPermission());
		conversionMap.get("PermissionButton").put("%price%", "" + perm.getPrice());
		conversionMap.get("PermissionButton").put("%requirement%", perm.getRequirements());
		conversionMap.get("PermissionButton").put("%description%", perm.getDescription());
		
		
		String converted = convert(toBeConverted, "PermissionButton");
		
		conversionMap.get("PermissionButton").remove("%permission%");
		conversionMap.get("PermissionButton").remove("%price%");
		conversionMap.get("PermissionButton").remove("%requirement%");
		conversionMap.get("PermissionButton").remove("%description%");
		
		conversionMap.get("PermissionButton").remove("%GP");
		conversionMap.get("PermissionButton").remove("%offset%");
		return converted;
	}
	
	public String frameConvert(String toBeConverted) {
		return convert(toBeConverted, "FrameButton");
	}
	
	public String emptyConvert(String toBeConverted) {
		return convert(toBeConverted, "EmptyButton");
	}
	
	public String scrollConvert(String toBeConverted, boolean up, String groupName) {
		conversionMap.get("ScrollButton").put("%direction%", (up ? "up" : "down"));
		conversionMap.get("ScrollButton").put("%toGroup%", groupName);
		String converted = convert(toBeConverted, "ScrollButton");
		conversionMap.get("ScrollButton").remove("%direction%");
		conversionMap.get("ScrollButton").remove("%toGroup%");
		return converted;
	}
	
	public String focusButton(String toBeConverted, String groupName) {
		conversionMap.get("FocusButton").put("%toGroup%", groupName);
		String converted = convert(toBeConverted, "FocusButton");
		conversionMap.get("FocusButton").remove("%toGroup%");
		return converted;
	}

	//--------------- retrival info --------------

	public String getName(String type) {
		String name = new String(config.getString(type + ".Item.name"));
		return name;
	}
	
	public String getDamage(String type) {
		String damage = new String(config.getString(type + ".Item.damage"));
		return damage;
	}
	
	public String getMaterial(String type) {
		String material = new String(config.getString(type + ".Item.material"));
		return material;
	}
	
	
	public String getLore(String type) {
		String lore = new String(config.getString(type + ".Item.lore"));
		return lore;
	}
	
}
