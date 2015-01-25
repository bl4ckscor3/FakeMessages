package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class FakeJoin
{
	public static void exe(Player p)
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
