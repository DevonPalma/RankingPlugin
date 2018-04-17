package cheatchki.main.managers;

import java.util.List;

import cheatchki.main.Ranking;
import cheatchki.main.info.RankLadder;

/**
 * this is the file to interact with the eco.yml
 * allowing for each object normally defined in
 * the file to be accessable easily with only
 * a group name and a slot number
 * 
 * @author deedp
 *
 */

public class EconomyFileManager extends FileManager {

	private static EconomyFileManager instance;
	
	public static void init() {
		instance = new EconomyFileManager("eco.yml");
	}
	
	public static EconomyFileManager getInstance() {
		return instance;
	}
	
	
	public EconomyFileManager(String fileName) {
		super(fileName);
	}

	@Override
	public void setDefaults() {

//		config.addDefault("DefaultPath", "");
		
		String defaultPath = config.getString("DefaultPath");
		
		if (!defaultPath.equals("")) {
			int counter = 0;
			RankLadder.pexRankLadder = defaultPath;
			List<String> groups = RankLadder.get();
			if (groups.size() > 0) {
				for (String g : groups) {
					counter++;
					for (int y = 0; y < 7; y++) {
						config.addDefault("groups." + g + ".p" + y + ".permission", "ExamplePerm." + g + ".test" + y);
						
						config.addDefault("groups." + g + ".p" + y + ".price", counter * 10 + y);
						
						config.addDefault("groups." + g + ".p" + y + ".requirements", g);
						
						config.addDefault("groups." + g + ".p" + y + ".description", "Simple Description");
					}
				}
			} else {
				Ranking.getInstance().getLogger().warning("Could not find Rank Ladder \"" + defaultPath + "\" in eco.yml" );
			}
		} else {
			Ranking.getInstance().getLogger().warning("No Rank Ladder has been set in eco.yml");
		}
	}

	public String getPermission(String groupName, int slot) {
		return config.getString("groups." + groupName + ".p" + slot + ".permission");
	}
	public double getPrice(String groupName, int slot) {
		return config.getDouble("groups." + groupName + ".p" + slot + ".price");
	}
	public String getRequirements(String groupName, int slot) {
		return config.getString("groups." + groupName + ".p" + slot + ".requirements");
	}
	public String getDescription(String groupName, int slot) {
		return config.getString("groups." + groupName + ".p" + slot + ".description");
	}
	
}
