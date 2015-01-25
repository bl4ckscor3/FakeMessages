package bl4ckscor3.plugin.fmsg.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import bl4ckscor3.plugin.fmsg.commands.FakeJoin;
import bl4ckscor3.plugin.fmsg.commands.FakeLeave;

public class FakeMessages extends JavaPlugin
{
	public static List<String> fakeOfflinePlayers = new ArrayList<String>();
	public static List<String> fakeOnlinePlayers = new ArrayList<String>();

	@Override
	public void onEnable()
	{
		System.out.println("[FakeMessages] v " + getDescription().getVersion() + " enabled.");
	}

	@Override
	public void onDisable()
	{
		System.out.println("[FakeMessages] v " + getDescription().getVersion() + " disabled.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = null;

		if(sender instanceof Player)
			p = (Player)sender;

		if(cmd.getName().equals("fjoin"))
		{
			if(args.length > 1)
				return false;

			if(args.length == 0 && p == null)
			{
				System.out.println("[FakeMessages] The console can't fake-join. Use \"/fjoin <name>\" instead.");
				return true;
			}
				
			FakeJoin.exe(p, args);
			return true;
		}
		else if(cmd.getName().equals("fleave"))
		{
			if(args.length > 1)
				return false;

			if(args.length == 0 && p == null)
			{
				System.out.println("[FakeMessages] The console can't fake-leave. Use \"/fleave <name>\" instead.");
				return true;
			}
			
			FakeLeave.exe(p, args);
			return true;
		}

		return false;
	}

	public static boolean isOnline(String name)
	{
		Player[] players = Bukkit.getOnlinePlayers();

		for(Player p : players)
		{
			if(p.getName().equals(name))
				return true;
		}

		return false;
	}
}
