package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * No. 5
 *
 * はじめてのjao afa
 * 「jao afa」と発言する
 * 「jao」と発言したあとに「afa」と発言した場合
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class Speakjao_afa implements Listener {
	JavaPlugin plugin;
	public Speakjao_afa(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	Map<UUID, String> OldMessage = new HashMap<UUID, String>();
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaotan(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(!message.equalsIgnoreCase("afa")) OldMessage.put(player.getUniqueId(), message);
		if(!message.equalsIgnoreCase("afa")){
			return;
		}
		if(!OldMessage.containsKey(player.getUniqueId())){
			return;
		}
		if(!OldMessage.get(player.getUniqueId()).equalsIgnoreCase("jao")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(5))){
			player.sendMessage("[" + ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S" + ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m" + ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.RESET + "] "
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
