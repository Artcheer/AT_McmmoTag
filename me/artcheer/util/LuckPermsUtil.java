package me.artcheer.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.nossr50.datatypes.skills.PrimarySkillType;

import me.artcheer.config.ConfigManager;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;

public class LuckPermsUtil {
	
	public String perm = "prefix.100.";
	private ConfigManager cm;
	
	public LuckPermsUtil() {
		
	}
	
	public void setTagPlayer(PrimarySkillType e, Player p) {
		cm = new ConfigManager();
		List<String> tags = cm.getTags();
		
		// Verificando se o jogador tem outras tags do plugin e retira caso tenha //
		User user = LuckPerms.getApi().getUser(p.getUniqueId());
		for(Node n : user.getAllNodes()){
			if(n.isPrefix()) {
				for(String s : tags) {
					if(n.getPermission().contains(s)) {
						user.unsetPermission(n);
					}
				}
			}
		}
		// =======================================================================
		// Seta a tag no jogador // 
		user.setPermission(LuckPerms.getApi().buildNode(perm+cm.getTag(e)).build());
		LuckPerms.getApi().getUserManager().saveUser(user);
		cm.setTopAtual(p.getName(), e);
		cm.reloadConfig();
	}
	
	
	public void unSetTagPlayer(PrimarySkillType e, String playername) {
		// Tira a tag do jogador
		cm = new ConfigManager();
		if(cm.getTopAtual(e) != null) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user "+playername+" permission unset \"prefix.100."+cm.getTagSemSimbol(e)+"\"");
		}
		
	}
	
	
}
