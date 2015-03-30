package bl4ckscor3.plugin.fmsg.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import bl4ckscor3.plugin.bl4ckkitCore.core.bl4ckkitCore;
import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class ChatListener implements Listener
{
	private static Plugin plugin;
	
	public ChatListener(Plugin pl)
	{
		plugin = pl;
	}
	
	@EventHandler
	public void onAsnycPlayerChat(AsyncPlayerChatEvent event)
	{
		if(FakeMessages.fakeOfflinePlayers.contains(event.getPlayer().getName()))
		{
			for(Player p: Bukkit.getOnlinePlayers())
			{
				event.getRecipients().remove(p);
			}
			
			bl4ckkitCore.getMessageManager().sendChatMessage(event.getPlayer(), plugin, "Watch out that you don't write into chat when you fake-left! Use /fj to fake-join and send your message again.");
		}
	}
}
