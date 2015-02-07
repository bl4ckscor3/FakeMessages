package bl4ckscor3.plugin.fmsg.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class ChatListener implements Listener
{
	@EventHandler
	public void onAsnycPlayerChat(AsyncPlayerChatEvent event)
	{
		if(FakeMessages.fakeOfflinePlayers.contains(event.getPlayer().getName()))
		{
			for(Player p: Bukkit.getOnlinePlayers())
			{
				event.getRecipients().remove(p);
			}
			
			event.getPlayer().sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] Watch out that you don't write into chat when you fake-left! Use /fj to fake-join and send your message again.");
		}
	}
}
