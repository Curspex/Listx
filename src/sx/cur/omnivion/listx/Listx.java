package sx.cur.omnivion.listx;

import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

import sx.cur.omnivion.listx.command.CommandList;
import sx.cur.omnivion.listx.configuration.LxConfiguration;
import sx.cur.omnivion.listx.listener.player.*;

public class Listx extends JavaPlugin {


	public static Listx i;
	public LxConfiguration config;
	public ListxPlayerCache cache;
	
	@Override
	public void onEnable()
	{
		i = this;
		i.config = new LxConfiguration(i);
		i.cache = new ListxPlayerCache(i);

		final EventRegistrar registrar = new EventRegistrar(i, Bukkit.getPluginManager(), true, EventPriority.HIGH);
		registrar.registerEvent(new LoginListener(i));
		registrar.registerEvent(new LogoutListener(i));

		i.getCommand("listx").setExecutor(new CommandList(i));
		
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


}