package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * はじめてのjao
 * jao鯖に初めて入る
 * 鯖に初めてログインする
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
public class Firstjao {
	JavaPlugin plugin;
	public Firstjao(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstjao(PlayerJoinEvent event){
		Player player = event.getPlayer();
		// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
		if(!Achievementjao.getAchievement(player, new AchievementType(3))){
			player.sendMessage("[" + ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S" + ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m" + ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.RESET + "] "
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
