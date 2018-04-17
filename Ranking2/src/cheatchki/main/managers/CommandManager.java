package cheatchki.main.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cheatchki.main.Ranking;

public class CommandManager implements CommandExecutor {

	//---------------------------------------------------
	
	private static CommandManager instance;
	
	public static void init() {
		Ranking.getInstance().getCommand("rank").setExecutor(new CommandManager());
	}
	
	public static CommandManager getInstance() {
		return instance;
	}
	
	public CommandManager() {
		instance = this;
	}

	//---------------------------------------------------
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1)
			MenuManager.getInstance().addMenu((Player) sender, Integer.parseInt(args[0]));
		else
			MenuManager.getInstance().addMenu((Player) sender);
		return true;
	}

}
