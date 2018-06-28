package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 19
 *
 * アルアル地獄
 * .tと発言する
 * チャットより.tと発言した場合
 * ※隠し要素
 *
 * @since 2018/06/28
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class AruAruHell implements Listener {
	JavaPlugin plugin;
	public AruAruHell(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaotan(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(!message.equalsIgnoreCase(".t")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(18))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
