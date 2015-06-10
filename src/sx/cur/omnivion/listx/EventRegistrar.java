package sx.cur.omnivion.listx;

import org.bukkit.event.EventPriority;
import org.bukkit.plugin.PluginManager;

import sx.cur.omnivion.listx.listener.LxListener;

public class EventRegistrar {

	private final Listx i;
	private final EventPriority defaultPriority;
	private final boolean defaultIgnoreCancelled;
	private final PluginManager manager;
	public EventRegistrar(final Listx i, final PluginManager manager, final boolean defaultIgnoreCancelled, final EventPriority defaultPriority)
	{
		this.i = i;
		this.manager = manager;
		this.defaultIgnoreCancelled = defaultIgnoreCancelled;
		this.defaultPriority = defaultPriority;
	}

	protected void registerEvent(LxListener obj)
	{
		this.registerEvent(obj, this.defaultPriority, this.defaultIgnoreCancelled);
	}

	protected void registerEvent(LxListener obj, EventPriority priority, boolean ignoreCancelled)
	{
		this.manager.registerEvent(obj.getClazz(), obj, priority, obj, this.i, ignoreCancelled);
	}

}