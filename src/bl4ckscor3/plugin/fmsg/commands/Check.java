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
			if(!s.equals(name))
				continue;
			
			if(FakeMessages.isOnline(name))
			{
				p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] " + name + " player is fake-offline.");
				return;
			}
		}
		p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] " + name + " player is not fake-offline.");
	}
}
