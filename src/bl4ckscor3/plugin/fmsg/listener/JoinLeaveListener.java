package bl4ckscor3.plugin.fmsg.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class JoinLeaveListener implements Listener
{
	@EventHandler
	public void onAsnycPlayerPreLogin(AsyncPlayerPreLoginEvent event)
	{
		if(FakeMessages.fakeOfflinePlayers.contains(event.getName()))
			FakeMessages.fakeOfflinePlayers.remove(event.getName());

		if(FakeMessages.fakeOnlinePlayers.contains(event.getName()))
			FakeMessages.fakeOnlinePlayers.remove(event.getName());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if(FakeMessages.fakeOfflinePlayers.contains(event.getPlayer().getName()))
			FakeMessages.fakeOfflinePlayers.remove(event.getPlayer().getName());

		if(FakeMessages.fakeOnlinePlayers.contains(event.getPlayer().getName()))
			FakeMessages.fakeOnlinePlayers.remove(event.getPlayer().getName());
	}
}
