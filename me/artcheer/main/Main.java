package me.artcheer.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.artcheer.listeners.Eventos;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		// Mensagem inicio //
		ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
		ccs.sendMessage(ChatColor.AQUA + "===============================");
		ccs.sendMessage(ChatColor.AQUA + "[AT_McmmoTag] Plugin Ativado");
		ccs.sendMessage(ChatColor.AQUA + "[AT_McmmoTag] Criador Artcheer.");
		ccs.sendMessage(ChatColor.AQUA + "===============================");

		try {
			File f = new File(getDataFolder(), "config.yml"); 
			if(!f.exists()) saveResource("config.yml", false);
			ccs.sendMessage("CONFIG: §aOK");
		} catch (Exception e) {
			ccs.sendMessage("§cALGO DEU ERRADO! Procure §6@Artcheer");
			e.printStackTrace();
		}
		ccs.sendMessage("===================================");
		Bukkit.getPluginManager().registerEvents(new Eventos(), this);;
		

	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "================================");
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[AT_McmmoTag] Plugin Desativado.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "================================");
	}


	public static Main getInstance() {
		return (Main) Bukkit.getPluginManager().getPlugin("AT_McmmoTag");
	}
}