package si.craft.cnSaveRestart;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import si.craft.cnSaveRestart.components.AutoRestart;
import si.craft.cnSaveRestart.components.AutoSave;
import si.craft.cnSaveRestart.components.ClearLagg;
import si.craft.cnSaveRestart.helpers.DefaultOptions;
import si.craft.cnSaveRestart.helpers.Helper;
import si.craft.cnSaveRestart.helpers.YAMLConfig;
import si.craft.cnSaveRestart.helpers.YAMLConfigManager;

public class main extends JavaPlugin {
	// this plugin
		public static main plugin;
		public static String pluginPrefix = "CN SaveRestart";
		
		// helper class
		public final Helper functions = new Helper();
		
		// config manager
		private YAMLConfigManager configManager;
		private YAMLConfig mainConfig;
		public DefaultOptions defaultoptions;
		
		// components
		public AutoSave autosave;
		public AutoRestart autorestart;
		public ClearLagg clearlagg;
		//public ServerMonitor servermonitor;
		
		
		/************************************************************************************************
		 * ON PLUGIN :: ENABLE / RELOAD
		 ***********************************************************************************************/
		@Override
		public void onEnable() {
			
			// set plugin
			plugin = this;
			
			// Plugin manager
			//PluginManager pluginmanager = this.getServer().getPluginManager();
			
			// get plugin.yml file contents
			PluginDescriptionFile description = this.getDescription();
			
			/************************************************************************************************
			 * PLUGIN CONFIG
			 ***********************************************************************************************/
			configManager = new YAMLConfigManager(this);
			String[] header = { pluginPrefix, "----- GC HUB Tools by GigaCraft -----"};
			
			// default config file (config.yml)
			defaultoptions = new DefaultOptions(this);
			try {
				mainConfig = configManager.getNewConfig("cnSaveRestart.yml", header);
				defaultoptions.setDefaultValues(mainConfig);
			} 
			catch (Exception e) {}
			mainConfig.reloadConfig();
			
			/************************************************************************************************
			 * START COMPONENTS
			 ***********************************************************************************************/
			autorestart = new AutoRestart();
			AutoRestart.restartTimer();
			
			autosave = new AutoSave();
			AutoSave.AutoSaveScheduler();
			
			clearlagg = new ClearLagg();
			ClearLagg.ClearLaggScheduler();
			
			//servermonitor = new ServerMonitor();

			/************************************************************************************************
			 * LOG PLUGIN INIT
			 ***********************************************************************************************/
			Helper.log("&e Version: " + description.getVersion() + "&a Has Been Enabled!");
			
		}
		
		/************************************************************************************************
		 * COMMANDS
		 ***********************************************************************************************/
		/*
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			// lowercase incomming command
			String command = cmd.getName().toLowerCase();
			
			// test for command origin
			if (sender instanceof Player) {
				// sender is a player
				
				// get player
				Player player = (Player) sender;
				
				// loop thru all valid commands that can be used by players
				switch (command) {
				case "monitor":
					ServerMonitor.ServerInfo(player);
				}
				
			}
			else {
				// sender is console or another plugin
				
			}
			return false;
		}
		 */
		
		/************************************************************************************************
		 * ON PLUGIN :: DISABLE
		 ***********************************************************************************************/
		@Override
		public void onDisable() {
			// console message on disable
			Helper.log("&aHas Been Disabled!");
		}
		
		/************************************************************************************************
		 * GET SERVER NAME FROM server.properties
		 ***********************************************************************************************/
		public static String getServerName() {
			return Bukkit.getServer().getServerId().toString().toLowerCase();
		}
		
		/************************************************************************************************
		 * GET PLUGIN
		 ***********************************************************************************************/
		public static main getPlugin() {
			return plugin;
		}

		/************************************************************************************************
		 * PLUGIN CONFIG FILES
		 ***********************************************************************************************/
		public YAMLConfig getMainConfig() {
			return mainConfig;
		}
}
