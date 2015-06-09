package sx.cur.omnivion.listx.listener.player;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;

import sx.cur.omnivion.listx.Listx;
import sx.cur.omnivion.listx.listener.LxListener;

public class LoginListener extends LxListener {


	public LoginListener(Listx i)
	{
		super(i, PlayerJoinEvent.class);
	}

	@Override
	public void dispatch(Event mevent)
	{
		PlayerJoinEvent event = (PlayerJoinEvent) mevent;

		i.cache.checkPlayer(event.getPlayer());
	}


}