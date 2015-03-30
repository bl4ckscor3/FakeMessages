package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import bl4ckscor3.plugin.bl4ckkitCore.core.bl4ckkitCore;
import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class Check
{
	public static void exe(Player p, Plugin pl, String name)
	{
		for(String s : FakeMessages.fakeOfflinePlayers)
		{
			if(!s.equals(name))
				continue;
			
			if(bl4ckkitCore.getPlayerManager().isPlayerOnline(name))
			{
				bl4ckkitCore.getMessageManager().sendChatMessage(p, pl, name + " is fake-offline.");
				return;
			}
		}
		bl4ckkitCore.getMessageManager().sendChatMessage(p, pl, name + " is not fake-offline.");
	}
}
