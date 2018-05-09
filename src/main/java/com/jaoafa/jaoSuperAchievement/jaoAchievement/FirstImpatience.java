package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 4
 *
 * はじめてのもどかしさ
 * 「.」と発言する
 * 初めて「.」と発言した場合
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class FirstImpatience implements Listener {
	JavaPlugin plugin;
	public FirstImpatience(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaotan(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(!message.equalsIgnoreCase(".")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(4))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
