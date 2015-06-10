package sx.cur.omnivion.listx.listener;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import sx.cur.omnivion.listx.Listx;

public abstract class LxListener implements EventExecutor, Listener {

	protected final Listx i;
	protected final Class<? extends Event> clazz;
	public LxListener(Listx i, Class<? extends Event> clazz)
	{
		this.i = i;
		this.clazz = clazz;
	}
	
	public Class<? extends Event> getClazz()
	{
		return this.clazz;
	}
	
	@Override
	public final void execute(Listener listener, Event mevent)// throws EventException
	{
		if (!(mevent.getClass() == this.getClazz())) return;
		this.dispatch(mevent);
	}
	
	public abstract void dispatch(Event mevent);

}