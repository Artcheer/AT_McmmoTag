package me.artcheer.listeners;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.runnables.SaveTimerTask;

import me.artcheer.config.ConfigManager;
import me.artcheer.util.LuckPermsUtil;

public class Eventos implements Listener {
	public ConfigManager cm;
	public LuckPermsUtil lpu;

	@EventHandler
	public void onUpRankSkill(McMMOPlayerLevelUpEvent e) {
		cm = new ConfigManager();
		lpu = new LuckPermsUtil();
		
		Optional<Integer> skillLevelRank = Optional.ofNullable(ExperienceAPI.getPlayerRankSkill(e.getPlayer().getUniqueId(), e.getSkill().getName()));
		
		new SaveTimerTask().run();
		try {
			if (cm.getTopAtual(e.getSkill()).isPresent()) {
				if (!e.getPlayer().getName().equalsIgnoreCase(cm.getTopAtual(e.getSkill()).get())) {
					if(skillLevelRank.get().intValue() == 1) {
						lpu.unSetTagPlayer(e.getSkill(),cm.getTopAtual(e.getSkill()).get());
						lpu.setTagPlayer(e.getSkill(), e.getPlayer());
						Bukkit.getServer().broadcastMessage(cm.getTopAlert(e.getPlayer().getName(), e.getSkill()));
					}
				}
			}
		}catch(Exception ex) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "O jogador "+e.getPlayer().getName()+" ainda não foi salvo no sistema, tente novamente mais tarde.");
		}		
	}
	
	
}
