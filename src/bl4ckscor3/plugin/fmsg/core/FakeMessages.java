package bl4ckscor3.plugin.fmsg.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import bl4ckscor3.plugin.fmsg.commands.Check;
import bl4ckscor3.plugin.fmsg.commands.FakeJoin;
import bl4ckscor3.plugin.fmsg.commands.FakeLeave;
import bl4ckscor3.plugin.fmsg.listener.ChatListener;
import bl4ckscor3.plugin.fmsg.listener.JoinLeaveListener;

public class FakeMessages extends JavaPlugin
{
	public static List<String> fakeOfflinePlayers = new ArrayList<String>();
	public static List<String> fakeOnlinePlayers = new ArrayList<String>();
	public static Plugin instance;

	@Override
	public void onEnable()
	{
		instance = this;
		getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		Config.createConfig(this);
		System.out.println("[" + getDescription().getName() + "] v " + getDescription().getVersion() + " enabled.");
	}

	@Override
	public void onDisable()
	{
		System.out.println("[" + getDescription().getName() + "] v " + getDescription().getVersion() + " disabled.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = null;

		if(sender instanceof Player)
			p = (Player)sender;

		if(p == null)
		{
			System.out.println("[" + getDescription().getName() + "] The console cannot use this plugin. Please join the server and execute the commands on there.");
			return true;
		}

		if(cmd.getName().equals("fjoin"))
		{
			if(p.hasPermission("fmsg.use"))
			{
				if(args.length > 1)
					return false;

				FakeJoin.exe(p, args);
				return true;
			}
		}
		else if(cmd.getName().equals("fleave"))
		{
			if(p.hasPermission("fmsg.use"))
			{
				if(args.length > 1)
					return false;

				FakeLeave.exe(p, args);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("fcheck"))
		{
			if(p.hasPermission("fmsg.check"))
			{
				if(args.length == 0 || args.length > 1)
					return false;

				Check.exe(p, args[0]);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("freload"))
		{
			if(p.hasPermission("fmsg.reload"))
			{
				reloadConfig();
				p.sendMessage("[" + ChatColor.BLUE + getDescription().getName() + ChatColor.RESET + "] Reloaded configuration successfully.");
				return true;
			}
		}
		return false;
	}

	public static boolean isOnline(String name)
	{
		Player[] players = Bukkit.getOnlinePlayers();

		for(Player p : players)
		{
			if(p.getName().equalsIgnoreCase(name))
				return true;
		}

		return false;
	}

	public static void letPlayerJoin(Player p, String name)
	{
		String joinMessage = instance.getConfig().getString("join-message");

		joinMessage = joinMessage.replace("&", "\u00A7");

		if(isOnline(name))
		{
			joinMessage = joinMessage.replace("{USERNAME}", Bukkit.getPlayer(name).getName());
			joinMessage = joinMessage.replace("{PLAYER}", Bukkit.getPlayer(name).getDisplayName());
		}
		else
		{
			joinMessage = joinMessage.replace("{USERNAME}", Bukkit.getOfflinePlayer(name).getName());
			joinMessage = joinMessage.replace("{PLAYER}", name); //TODO: Add prefix and suffix
		}
		
		p.sendMessage(joinMessage);
	}

	public static void letPlayerLeave(Player p, String name)
	{
		String leaveMessage = instance.getConfig().getString("leave-message");

		leaveMessage = leaveMessage.replace("&", "\u00A7");
		
		if(isOnline(name))
		{
			leaveMessage = leaveMessage.replace("{USERNAME}", Bukkit.getPlayer(name).getName());
			leaveMessage = leaveMessage.replace("{PLAYER}", Bukkit.getPlayer(name).getDisplayName());
		}
		else
		{
			leaveMessage = leaveMessage.replace("{USERNAME}", Bukkit.getOfflinePlayer(name).getName());
			leaveMessage = leaveMessage.replace("{PLAYER}", name); //TODO: Add prefix and suffix
		}
		
		p.sendMessage(leaveMessage);
	}
}
