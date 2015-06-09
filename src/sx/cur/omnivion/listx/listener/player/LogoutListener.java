package sx.cur.omnivion.listx.listener.player;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerQuitEvent;

import sx.cur.omnivion.listx.Listx;
import sx.cur.omnivion.listx.listener.LxListener;

public class LogoutListener extends LxListener {


	public LogoutListener(Listx i)
	{
		super(i, PlayerQuitEvent.class);
	}

	@Override
	public void dispatch(Event mevent)
	{
		PlayerQuitEvent event = (PlayerQuitEvent) mevent;

		i.cache.popPlayer(event.getPlayer());
	}


}