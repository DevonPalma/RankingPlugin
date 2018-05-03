package cheatchki.main.managers;

import cheatchki.main.Options;
import cheatchki.main.Utils;
import cheatchki.main.menu.button.Button;
import cheatchki.main.menu.button.EmptyButton;
import cheatchki.main.menu.button.FocusButton;
import cheatchki.main.menu.button.FrameButton;
import cheatchki.main.menu.button.PermissionButton;
import cheatchki.main.menu.button.ScrollButton;

public class GUIFileManager extends FileManager {
	
	private static GUIFileManager instance;
	
	public static void init() {
		instance = new GUIFileManager("GUI.yml");
	}
	
	public static GUIFileManager getInstance() {
		return instance;
	}
	
	public GUIFileManager(String fileName) {
		super(fileName);
	}


	@Override
	public void setDefaults() {
		Options.setSize(config.getInt("guiDefaults.size"));
		for (int x = 1; x <= 5; x += 2) {
			for (int y = 0; y < 9 * x; y++) {
				String index = "guiDefaults.size" + x*9 + ".slot" + y + ".";

				config.addDefault(index + "type", "Empty");
				String type = config.getString(index + "type");
				
				if (type != null && type.equals("Permission"))
					config.addDefault(index + "offset", 0);
				
				else if (type != null && type.equals("Scroll"))
					config.addDefault(index + "direction", "up");
				
			}
		}
	}
	
	public Button[] getDefault(int curPage) {
		return getDefault(Options.SIZE, curPage);
	}

	private Button[] getDefault(int size, int curPage) {
		Button[] returnButtons = new Button[size];
		
		for (int slot = 0; slot < size; slot++) {
			String index = "guiDefaults.size" + size + ".slot" + slot + ".";
			switch(config.getString(index + "type")) {
			case "Permission":
				int offset = config.getInt(index + "offset");
				if (curPage + offset < 0 || curPage + offset >= Utils.getRankLaddersGroups().size())
					returnButtons[slot] = new EmptyButton();
				else
					returnButtons[slot] = new PermissionButton(config.getInt(index + "offset"), curPage, slot);
				break;
			case "Frame":
				returnButtons[slot] = new FrameButton();
				break;
			case "Empty":
				returnButtons[slot] = new EmptyButton();
				break;
			case "Scroll":
//				Ranking.getInstance().getLogger().warning("" + curPage);
				boolean up = config.getString(index + "direction").equals("up");
				if (up && curPage <= 0)
					returnButtons[slot] = new EmptyButton();
				else if (!up && curPage >= Utils.getRankLaddersGroups().size() - 1)
					returnButtons[slot] = new EmptyButton();
				else
					returnButtons[slot] = new ScrollButton(up, curPage);
				break;
			case "Focus":
				returnButtons[slot] = new FocusButton();
			}
		}
		return returnButtons;
	}
}
