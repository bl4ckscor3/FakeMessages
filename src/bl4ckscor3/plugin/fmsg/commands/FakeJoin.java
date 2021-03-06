package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import bl4ckscor3.plugin.bl4ckkitCore.core.bl4ckkitCore;
import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class FakeJoin
{
	public static void exe(Player p, Plugin plugin, String args[])
	{
		if(args.length == 1)
		{
			boolean firstCheck = false;

			if(bl4ckkitCore.getPlayerManager().isPlayerOnline(args[0])) //fake-join for other players currently on the server
			{
				if(FakeMessages.fakeOfflinePlayers.contains(args[0]))
				{
					Player[] players = Bukkit.getOnlinePlayers();

					for(Player pl : players)
					{
						FakeMessages.letPlayerJoin(pl, args[0]);
					}

					//hard coded >.<
					Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinEvent(p, ChatColor.BLACK  + "[" + ChatColor.GREEN + "+" + ChatColor.BLACK + "]" + p.getName()));
					FakeMessages.fakeOfflinePlayers.remove(args[0]);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + args[0]);
				}
				else
					bl4ckkitCore.getMessageManager().sendChatMessage(p, plugin, args[0] + " has not fake-left the server. Use /fleave " + args[0] + " to let them do that.");

				firstCheck = true;
			}

			if(!firstCheck && !FakeMessages.fakeOnlinePlayers.contains(args[0])) //fake-join for people who are not on the server (e.g. "/fjoin AntVenom" let's AntVenom fake-join)
			{
				Player[] players = Bukkit.getOnlinePlayers();

				for(Player pl : players)
				{
					FakeMessages.letPlayerJoin(pl, args[0]);
				}

				FakeMessages.fakeOnlinePlayers.add(args[0]);
			}
			else
			{
				if(!firstCheck)
					bl4ckkitCore.getMessageManager().sendChatMessage(p, plugin, args[0] + " already fake-joined the server. Use /fleave " + args[0] + " to let them fake-leave.");
			}
		}
		else //fake-join for yourself
		{
			if(FakeMessages.fakeOfflinePlayers.contains(p.getName()))
			{
				Player[] players = Bukkit.getOnlinePlayers();

				for(Player pl : players)
				{
					FakeMessages.letPlayerJoin(pl, p.getName());
				}

				FakeMessages.fakeOfflinePlayers.remove(p.getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + p.getName());
			}
			else
				bl4ckkitCore.getMessageManager().sendChatMessage(p, plugin, "You have not fake-left the server yet. Use /fleave to do that now.");
		}
	}
}
