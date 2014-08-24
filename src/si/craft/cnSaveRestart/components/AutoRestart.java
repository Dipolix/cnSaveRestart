package si.craft.cnSaveRestart.components;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import si.craft.cnSaveRestart.main;
import si.craft.cnSaveRestart.helpers.Helper;

public class AutoRestart {
	
	public static long startTime = System.currentTimeMillis();
	public static boolean initilise = true;
	public static boolean[] reminders;
	// get restart interval from config
	static long timer = main.getPlugin().getMainConfig().getInt("components.restart.time");
	
	// server restart timer
	public static void restartTimer(){
		main.getPlugin().getServer().getScheduler().scheduleSyncRepeatingTask(main.getPlugin(), new BukkitRunnable(){
			@Override
			public void run(){
				long diff = System.currentTimeMillis() - startTime;
				if(initilise){
					reminders = new boolean[4];
					reminders[0] = true;
					reminders[1] = true;
					reminders[2] = true;
					reminders[3] = true;
					initilise = false;
					timer = 60000 * timer; // 3600000 == 1 hour | 60000 == 1 minute
				}
				if(diff >= timer && reminders[3]){
					
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					Bukkit.broadcastMessage(Helper.colors("&eSERVER RESTARTARTING!"));
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					
					
					main.getPlugin().getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
					
					main.getPlugin().getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
					
					reminders[3] = false;
				}
				else if(diff >= timer - 60000 && reminders[2]){
					
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					Bukkit.broadcastMessage(Helper.colors("&eSERVER RESTART in &e&l1 minute!"));
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					
					reminders[2] = false;
				}
				else if(diff >= timer - 300000 && reminders[1]){
					
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					Bukkit.broadcastMessage(Helper.colors("&eSERVER RESTART in &e&l5 minutes!"));
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					
					reminders[1] = false;
				}
				else if(diff >= timer - 600000 && reminders[0]){
					
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					Bukkit.broadcastMessage(Helper.colors("&eSERVER RESTART in &e&l10 minutes!"));
					Bukkit.broadcastMessage(Helper.colors("&b---------------------"));
					
					reminders[0] = false;
				}
			}
		}, 0L, 20L);
	}
	
	
}