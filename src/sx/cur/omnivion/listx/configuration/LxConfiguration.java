package sx.cur.omnivion.listx.configuration;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import sx.cur.omnivion.listx.Listx;

public class LxConfiguration {

	private final Listx i;
	private FileConfiguration config;
	public LxConfiguration(Listx i)
	{
		this.i = i;
		this.config = i.getConfig();
		this.cns();
	}
	
	private void cns()
	{
		config.options().copyDefaults(true);
		i.saveConfig();
		
		this.setPermissionBase(config.getString("permissionBase"));
		this.setGroups(config.getStringList("groups"));
		this.setFormat(config.getStringList("format"), true);
	}
	
	public void reload()
	{
		i.reloadConfig();
		config = i.getConfig();
		this.cns();
	}
	
	private String LISTX_PERMISSION_BASE = "listx.staff.";
	public String getPermissionBase() { return this.LISTX_PERMISSION_BASE; }
	public void setPermissionBase(final String newBase) { this.LISTX_PERMISSION_BASE = newBase; }
	
	private List<String> groups = new ArrayList<String>();
	public List<String> getGroups() { return this.groups; }
	public void setGroups(final List<String> newFormat) { this.groups = newFormat; }
	
	private List<String> format = new ArrayList<String>();
	public List<String> getFormat() { return this.format; }
	public void setFormat(final List<String> newFormat, final boolean convertColors)
	{
		if (convertColors)
		{
			for (int lcv = 0; lcv < newFormat.size(); lcv++)
			{
				newFormat.set(lcv, ChatColor.translateAlternateColorCodes('&', newFormat.get(lcv)));
			}
		}
		this.format = newFormat;
	}
	
}