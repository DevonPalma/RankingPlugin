package cheatchki.main;

public class Options {
	public static int SIZE;

	public static void setSize(int size) {
		if (size == 9 || size == 27 || size == 45) {
			SIZE = size;
		} else {
			Ranking.getInstance().getLogger().severe("The size in GUI.yml is invalid");
		}
		
	}
}
