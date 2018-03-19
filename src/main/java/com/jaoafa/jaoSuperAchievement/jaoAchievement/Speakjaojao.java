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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * No. 9
 *
 * はじめてのjaojao
 * 「jaojao」と発言してから退出する
 * 「jaojao」と発言して10秒以内にサーバから退出した場合
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class Speakjaojao implements Listener {
	JavaPlugin plugin;
	public Speakjaojao(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	Map<UUID, Long> JaoJaoTime = new HashMap<UUID, Long>();
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaojao(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(!message.equalsIgnoreCase("jaojao")){
			return;
		}

		JaoJaoTime.put(player.getUniqueId(), System.currentTimeMillis() / 1000L);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaojaoLeft(PlayerQuitEvent event){
		Player player = event.getPlayer();

		if(!JaoJaoTime.containsKey(player.getUniqueId())){
			return;
		}

		if(((System.currentTimeMillis() / 1000L) - JaoJaoTime.get(player.getUniqueId())) > 10){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(9))){
			player.sendMessage("[" + ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S" + ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m" + ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.RESET + "] "
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
