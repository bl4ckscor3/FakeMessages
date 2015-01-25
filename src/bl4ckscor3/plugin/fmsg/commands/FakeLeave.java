package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class FakeLeave
{
	public static void exe(Player p)
	{
		if(!FakeMessages.fakeOfflinePlayers.contains(p.getName()))
		{
			Player[] players = Bukkit.getOnlinePlayers();

			for(Player pl : players)
			{
				pl.sendMessage(ChatColor.YELLOW + p.getName() + " left the game.");
			}

			FakeMessages.fakeOfflinePlayers.add(p.getName());
			Bukkit.dispatchCommand(p, "vanish");
		}
		else
			p.sendMessage(ChatColor.RED + "[FakeMessages] You have not fake-joined the server yet. Use /fjoin to do that now.");
	}
}
