package bl4ckscor3.plugin.fmsg.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import bl4ckscor3.plugin.fmsg.commands.FakeJoin;
import bl4ckscor3.plugin.fmsg.commands.FakeLeave;

public class FakeMessages extends JavaPlugin
{
	public static List<String> fakeOfflinePlayers = new ArrayList<String>();
	
	@Override
	public void onEnable()
	{
		System.out.println("[FakeMessages] v " + getDescription().getVersion() + " enabled.");
	}
	
	@Override
	public void onDisable()
	{
		System.out.println("[FakeMessages] v " + getDescription().getVersion() + " disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = null;
		
		if(sender instanceof Player)
			p = (Player)sender;
		
		if(cmd.getName().equals("fjoin"))
		{
			if(args.length > 0)
				return false;

			FakeJoin.exe(p);
			return true;
		}
		else if(cmd.getName().equals("fleave"))
		{
			if(args.length > 0)
				return false;
			
			FakeLeave.exe(p);
			return true;
		}
		
		return false;
	}
}
