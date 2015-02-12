package bl4ckscor3.plugin.fmsg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import bl4ckscor3.plugin.fmsg.core.FakeMessages;

public class FakeLeave
{
	public static void exe(Player p, String args[])
	{
		if(args.length == 1)
		{
			boolean firstCheck = false;

			if(FakeMessages.isOnline(args[0])) //fake-leave for other players currently on the server
			{
				if(!FakeMessages.fakeOfflinePlayers.contains(args[0]))
				{	
					Player[] players = Bukkit.getOnlinePlayers();

					for(Player pl : players)
					{
						FakeMessages.letPlayerLeave(pl, args[0]);
					}

					FakeMessages.fakeOfflinePlayers.add(args[0]);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + args[0]);
				}
				else
					p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] " + args[0] + " has not fake-joined the server. Use /fjoin " + args[0] + " to let him do that.");
				
				firstCheck = true;
			}

			if(!firstCheck && FakeMessages.fakeOnlinePlayers.contains(args[0])) //fake-leave for people who are not on the server (e.g. "/fleave AntVenom" let's AntVenom fake-leave)
			{
				Player[] players = Bukkit.getOnlinePlayers();

				for(Player pl : players)
				{
					FakeMessages.letPlayerLeave(pl, args[0]);
				}

				FakeMessages.fakeOnlinePlayers.remove(args[0]);
			}
			else
			{
				if(!firstCheck)
					p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] " + args[0] + " has not fake-joined the server. Use /fjoin " + args[0] + " to let him do that.");
			}
		}
		else //fake-leave for yourself
		{
			if(!FakeMessages.fakeOfflinePlayers.contains(p.getName()))
			{
				Player[] players = Bukkit.getOnlinePlayers();

				for(Player pl : players)
				{
					FakeMessages.letPlayerLeave(pl, p.getName());
				}

				FakeMessages.fakeOfflinePlayers.add(p.getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + p.getName());
			}
			else
				p.sendMessage("[" + ChatColor.BLUE + FakeMessages.instance.getDescription().getName() + ChatColor.RESET + "] You have not fake-joined the server yet. Use /fjoin to do that now.");
		}
	}
}
