package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 3
 *
 * はじめてのjao
 * jao鯖に初めて入る
 * 鯖に初めてログインする
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
public class Firstjao implements Listener {
	JavaPlugin plugin;
	public Firstjao(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstjao(PlayerJoinEvent event){
		Player player = event.getPlayer();
		// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
		if(!Achievementjao.getAchievement(player, new AchievementType(3))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
