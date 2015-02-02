package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class Check
{
	public static void exe(Player p, String name)
	{
		for(String s : FakeMessages.fakeOfflinePlayers)
		{
			if(FakeMessages.isOnline(s))
			{
				p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] This player is fake-offline.");
				return;
			}
		}
		p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] This player is not fake-offline.");
	}
}
