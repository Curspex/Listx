package sx.cur.omnivion.listx;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListxPlayerCache {

	private final Listx i;
	private final Map<String, Set<String>> staff;
	public final Map<String, Set<String>> getStaff() { return this.staff; }
	
	public ListxPlayerCache(Listx i)
	{
		this.i = i;
		staff = new HashMap<String, Set<String>>();
	}
	
	private boolean checkNode(final Player player)
	{
		return player.hasPermission(i.config.getPermissionBase() + "set");
	}
	
	public void popPlayer(final Player player)
	{
		//if (!this.checkNode(player)) return;
		
		for (String group : i.cache.getStaff().keySet())
		{
			Set<String> users = i.cache.getStaff().get(group);
			if (users == null || users.isEmpty()) continue;
			
			if (!users.contains(player.getName())) continue;
			
			i.cache.getStaff().get(group).remove(player.getName());
			
			if (i.cache.getStaff().get(group).isEmpty()) i.cache.getStaff().remove(group);
		}
	}
	
	public void checkPlayer(final Player player)
	{
		if (!this.checkNode(player)) return;
		
		for (final String group : i.config.getGroups())
		{
			if (!player.hasPermission(i.config.getPermissionBase() + group)) continue;
			
			if (i.cache.getStaff().get(group) == null) i.cache.getStaff().put(group, new HashSet<String>());
			
			i.cache.getStaff().get(group).add(player.getName());
			break;
		}
	}
	
	public void refresh()
	{
		i.config.reload();
		this.staff.clear();
		for (final Player player : Bukkit.getOnlinePlayers())
		{
			this.checkPlayer(player);
		}
	}

}