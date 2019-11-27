package me.artcheer.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import com.gmail.nossr50.datatypes.skills.PrimarySkillType;

import me.artcheer.main.Main;

public class ConfigManager {
	private FileConfiguration config = Main.getInstance().getConfig();
	private Plugin plugin = Main.getInstance();
	
	public ConfigManager() {
	
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void reloadConfig() {
		plugin.saveConfig();
		plugin.reloadConfig();
	}
	
	public Optional<String> getTopAtual(PrimarySkillType e) {
		return Optional.ofNullable(config.getString("classes."+e.toString()+".playeratual"));
		
	}
	
	public void setTopAtual(String playername, PrimarySkillType e) {
		config.set("classes."+e.toString()+".playeratual", playername);
		this.reloadConfig();
	}
	
	public String getTag(PrimarySkillType e) {
		return config.getString("classes."+e.toString()+".tag").replace("&", "§");
	}
	
	public String getTagSemSimbol(PrimarySkillType e) {
		return config.getString("classes."+e.toString()+".tag");
	}
	
	// Pega todas as tags do plugin //
	public List<String> getTags() {
		List<String> listTags = new ArrayList<>();
		
		for(PrimarySkillType e : PrimarySkillType.values()) {
			if(config.getString("classes."+e.toString()+".tag") != null){
			listTags.add(config.getString("classes."+e.toString()+".tag").replace("&", "§"));
			}
		}
		
		return listTags;
		
	}
	
	// Seta uma tag de skill ou edita
	public void setTag(PrimarySkillType e, String tag) {
		if((tag != null) && (e != null)) {
			config.set("classes."+e.toString()+".tag", tag.replace("§", "&"));
		}
		this.reloadConfig();
	}
	
	public String getTopAlert(String playername, PrimarySkillType e) {
		return config.getString("message.topalert").replace("{player}", playername).replace("{skill}", e.getName());
	}
	public void setTopAlert(String topalert) {
		config.set("message.topalert", topalert);
		this.reloadConfig();
	}
	
	public int getPriority() {
		return config.getInt("priority");
	}
	public void setPriority(int priority) {
		config.set("priority", priority);
		this.reloadConfig();
	}
}
