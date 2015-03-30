package bl4ckscor3.plugin.fmsg.core;

import org.bukkit.plugin.Plugin;

import bl4ckscor3.plugin.bl4ckkitCore.core.bl4ckkitCore;

public class Config
{
	public static void createConfig(Plugin plugin)
	{
		plugin.reloadConfig();
		plugin.getConfig().options().header("You can customise the join and leave messages here.\n"
				+ "This will allow for better hiding of fake-joining and fake-leaving if you use custom join and leave messages.\n"
				+ "Use {USERNAME} to display the actual name of the user, and {PLAYER} to use their displayname (prefix, suffix, nick, etc.)\n"
				+ "You can use color codes and such.");
		plugin.getConfig().addDefault("join-message", "&e{USERNAME} joined the game.");
		plugin.getConfig().addDefault("leave-message", "&e{USERNAME} left the game.");
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		bl4ckkitCore.getMessageManager().sendConsoleMessage(plugin, "Configuration created/enabled!");
	}
}
