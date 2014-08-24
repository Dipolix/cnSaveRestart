package si.craft.cnSaveRestart.components;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

import si.craft.cnSaveRestart.main;
import si.craft.cnSaveRestart.helpers.Helper;

public class AutoSave {

	public static int AUTOSAVE = 0;
	public static boolean running = true;
	public static int interval;
	
	// scheduler
	public static void AutoSaveScheduler() {
		interval = main.getPlugin().getMainConfig().getInt("components.save.time");
		
		AUTOSAVE = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				try {
					AutoSave.SaveAll();
				}
				catch (IOException e) {
					Helper.severe("AutoSave could not save player and worlds data!");
					Bukkit.getScheduler().cancelTask(AUTOSAVE);
					running = false;
					Helper.severe("AutoSave - STOPPED AutoSave!");
				}
			}
		// convert minutes to ticks (20 T/S)
		}, (interval * 60) * 20, (interval * 60) * 20);
	}
	
	// Save All : Player Data + All Worlds Data
	public static void SaveAll() throws IOException {
		// broadcast start of auto save
		Helper.BroadcastAutoSave("START");
		
		main.getPlugin().getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-off");
		Helper.log("&eBukkit auto-save disabled!");
		
		// save player data
		main.getPlugin().getServer().savePlayers();
		Helper.log("Saved ALL players!");
		// save all worlds
		List<World> worlds = main.getPlugin().getServer().getWorlds();
		for (World world : worlds) {
			world.save();
			Helper.log("Saved world: &b" + world.getName());
		}
		
		main.getPlugin().getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-on");
		Helper.log("&eBukkit auto-save enabled!");
		
		// broadcast end of auto save
		Helper.BroadcastAutoSave("END");
		
	}
	
	
	
}
