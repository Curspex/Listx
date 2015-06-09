package sx.cur.omnivion.listx;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import sx.cur.omnivion.listx.command.CommandList;
import sx.cur.omnivion.listx.configuration.LxConfiguration;
import sx.cur.omnivion.listx.listener.LxListener;
import sx.cur.omnivion.listx.listener.player.*;

public class Listx extends JavaPlugin {


	public static Listx i;
	public LxConfiguration config;
	public ListxPlayerCache cache;
	private final EventPriority defaultPriority = EventPriority.HIGH;
	private final boolean defaultIgnoreCancelled = false;
	
	@Override
	public void onEnable()
	{
		i = this;
		i.config = new LxConfiguration(i);
		i.cache = new ListxPlayerCache(i);
		final PluginManager manager = i.getServer().getPluginManager();

		i.getCommand("listx").setExecutor(new CommandList(i));
		i.registerEvent(new LoginListener(i), manager);
		i.registerEvent(new LogoutListener(i), manager);
		
		if (!Bukkit.getOnlinePlayers().isEmpty())
		{
			i.cache.refresh();
		}
		
	}

	@Override
	public void onDisable()
	{
		i.cache = null;
		i.config = null;
		i = null;
	}

	private void registerEvent(LxListener obj, PluginManager manager)
	{
		i.registerEvent(obj.getClazz(), obj, manager, i.defaultPriority, i.defaultIgnoreCancelled);
	}

	private void registerEvent(Class<? extends Event> clazz, LxListener obj, PluginManager manager, EventPriority priority, boolean ignoreCancelled)
	{
		manager.registerEvent(clazz, obj, priority, obj, i, ignoreCancelled);
	}


}