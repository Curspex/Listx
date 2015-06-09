package sx.cur.omnivion.listx.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import sx.cur.omnivion.listx.Listx;

public class CommandList implements CommandExecutor {

	private final Listx i;
	public CommandList(Listx i)
	{
		this.i = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		if (args.length > 0)
		{
			if (args[0].equalsIgnoreCase("reload") && sender.hasPermission(i.config.getPermissionBase() + "reload"))
			{
				i.config.reload();
				sender.sendMessage(ChatColor.GREEN + "Listx has been reloaded!");
				return true;
			}
			else if (args[0].equalsIgnoreCase("refresh") && sender.hasPermission(i.config.getPermissionBase() + "refresh"))
			{
				i.cache.refresh();
				sender.sendMessage(ChatColor.GREEN + "Listx cache refreshed!");
				return true;
			}
		}

		final StringBuilder builder = new StringBuilder();

		for (String format : i.config.getFormat())
		{
			if (format.contains("<online>"))
			{
				format = format.replace("<online>", String.valueOf(Bukkit.getOnlinePlayers().size()));
			}

			for (final String group : i.config.getGroups())
			{
				final String groupWithBrackets = "<" + group + ">";
				if (!format.contains(groupWithBrackets))
				{
					continue;
				}

				if (i.cache.getStaff().get(group) == null || i.cache.getStaff().get(group).isEmpty())
				{
					format = format.replace(groupWithBrackets, "None");
					continue;
				}

				format = format.replace(groupWithBrackets, i.cache.getStaff().get(group).toString().replace("[", "").replace("]", ""));
			}

			builder.append(format + '\n');
		}


		sender.sendMessage(builder.toString());


		return true;
	}

}