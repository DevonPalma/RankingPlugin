package cheatchki.main.managers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cheatchki.main.Ranking;

/** 
 * this is a base class for creating and loading in files 
 * this allows the player to create a file, get a config,
 * and save the file
 * 
 * along side that it provides an abstract method called
 * setDefaults which is called when a file is constructed,
 * and where a player would normally add any defaults he would
 * like
 * 
 * @author deedp
 *
 */

public abstract class FileManager {
	
	protected File file;
	protected FileConfiguration config;
	
	public FileManager(String fileName) {
		loadFiles(fileName);
		setDefaults();
		config.options().copyDefaults(true);
		save();
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void loadFiles(String fileName) {
//		file = new File(Ranking.getInstance().getDataFolder(), fileName);
//		Ranking.getInstance().getLogger().warning(file.getAbsolutePath());
//		if (!file.exists()) {
////			try {
////				file.createNewFile();
////			} catch (IOException e1) {
////				e1.printStackTrace();
////			}
//			Ranking.getInstance().getLogger().warning("File \"" + fileName + "\" hasn't been generated, generating now!");
//			InputStream input = null;
//			OutputStream output = null;
//			try {
//				input = Ranking.getInstance().getResource(fileName);
//				output = new FileOutputStream(file);
//				int read = 0;
//				byte[] bytes = new byte[1024];
//				while ((read = input.read(bytes)) != -1)
//					output.write(bytes, 0, read);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				if (input != null) {
//					try {
//						input.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				if (output != null) {
//					try {
//						output.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		Ranking.getInstance().saveResource(fileName, false);
		file = new File(Ranking.getInstance().getDataFolder(), fileName);
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract void setDefaults();
}
