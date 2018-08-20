package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 34
 *
 * 世界の中心で愛をさけぶ
 * x0y0でなにかチャットを送る
 * x0y0でなにかチャットを送る
 * ※隠し要素
 *
 * @since 2018/08/20
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class CryingOutLoveInTheCenterOfTheWorld implements Listener {
	JavaPlugin plugin;
	public CryingOutLoveInTheCenterOfTheWorld(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakCenter(PlayerChatEvent event){
		Player player = event.getPlayer();

		Location loc = player.getLocation();
		if(loc.getBlockX() != 0 && loc.getBlockZ() != 0){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(35))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
