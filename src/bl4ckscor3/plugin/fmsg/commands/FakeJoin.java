package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class FakeJoin
{
	public static void exe(Player p, String args[])
	{
		if(args.length == 1)
		{
			if(!FakeMessages.fakeOnlinePlayers.contains(args[0]))
			{
				Player[] players = Bukkit.getOnlinePlayers();

				for(Player pl : players)
				{
					pl.sendMessage(ChatColor.YELLOW + args[0] + " joined the game.");
				}

				FakeMessages.fakeOnlinePlayers.add(args[0]);
			}
			else
				p.sendMessage(ChatColor.RED + "[FakeMessages] This player already fake-joined the server. Use /fleave <name> to let him fake-leave.");
		}
		else
		{
			if(FakeMessages.fakeOfflinePlayers.contains(p.getName()))
			{
				Player[] players = Bukkit.getOnlinePlayers();

				for(Player pl : players)
				{
					pl.sendMessage(ChatColor.YELLOW + p.getName() + " joined the game.");
				}

				FakeMessages.fakeOfflinePlayers.remove(p.getName());
				Bukkit.dispatchCommand(p, "vanish");
			}
			else
				p.sendMessage(ChatColor.RED + "[FakeMessages] You have not fake-left the server yet. Use /fleave to do that now.");
		}
	}
}
