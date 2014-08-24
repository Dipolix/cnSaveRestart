package si.craft.cnSaveRestart.components;

import java.util.Calendar;

import net.minecraft.server.v1_5_R3.MinecraftServer;

import org.bukkit.entity.Player;

import si.craft.cnSaveRestart.main;

public class ServerMonitor {
	
	private static ServerMonitor instance;
	
	public static void ServerInfo(Player player) {
		
		long maxRam = Runtime.getRuntime().maxMemory() / 1024 / 1024;
		long freeRam = Runtime.getRuntime().freeMemory() / 1024 / 1024;
		
	}
	
}
