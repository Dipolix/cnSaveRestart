package si.craft.cnSaveRestart.components;

import java.io.IOException;

import org.bukkit.Bukkit;

import si.craft.cnSaveRestart.main;
import si.craft.cnSaveRestart.helpers.Helper;

public class ClearLagg {
		
	public static int LAGGCLEAR = 0;
	public static boolean running = true;
	public static int interval = 15; // minutes

	// scheduler
	public static void ClearLaggScheduler() {
		
		LAGGCLEAR = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				try {
					ClearLagg.doClearLagg();
				}
				catch (IOException e) {
					Bukkit.getScheduler().cancelTask(LAGGCLEAR);
					running = false;
					Helper.severe("ClearLagg Timer - STOPPED countdown timer!");
				}
			}
		// convert minutes to ticks (20 T/S)
		}, 60 * 20, (interval * 60) * 20);
	}
	
	// clear lagg broadcaster and executor
	public static void doClearLagg() throws IOException {
		
		// broadcast 10 minutes warning
		main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable(){
            public void run()
            {
            	Bukkit.broadcastMessage(Helper.colors("&8[&3CN&8]&c Warning! All ground items will be removed in 10 minutes!"));
            }
        }, 60 * 20);
		
		// broadcast 5 minutes warning
		main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable(){
            public void run()
            {
            	Bukkit.broadcastMessage(Helper.colors("&8[&3CN&8]&c Warning! All ground items will be removed in 5 minutes!"));
            }
        }, (300 + 60) * 20);
		
		// broadcast 1 minute warning
		main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable(){
            public void run()
            {
            	Bukkit.broadcastMessage(Helper.colors("&8[&3CN&8]&c Warning! All ground items will be removed in 1 minute!"));
            }
        }, (540 + 60) * 20);
		
		// broadcast 30 seconds warning
		main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable(){
            public void run()
            {
            	Bukkit.broadcastMessage(Helper.colors("&8[&3CN&8]&c Warning! All ground items will be removed in 30 seconds!"));
            }
        }, (570 + 60) * 20);
		
		// broadcast 10 seconds warning
		main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable(){
            public void run()
            {
            	Bukkit.broadcastMessage(Helper.colors("&8[&3CN&8]&c Warning! All ground items will be removed in 10 seconds!"));
            }
        }, (590 + 60) * 20);
		
		// trigger clear lagg command and broadcast "done" message
		main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(main.getPlugin(), new Runnable(){
            public void run()
            {
            	main.getPlugin().getServer().dispatchCommand(Bukkit.getConsoleSender(), "lagg clear");
            	Bukkit.broadcastMessage(Helper.colors("&8[&3CN&8]&c Removed all ground items!"));
            }
        }, (600 + 60) * 20);
		
	}
	
}
