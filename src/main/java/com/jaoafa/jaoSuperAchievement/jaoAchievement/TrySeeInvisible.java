package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 30
 *
 * 見えないものを見ようとして
 * スペクテイターモードになろうとする
 * /g 3 もしくは /gamemode 3を実行(実行するだけ、なれなくてもok)
 * ※隠し要素
 *
 * @since 2018/07/16
 * @category jao Achievement
 *
 */
public class TrySeeInvisible implements Listener {
	JavaPlugin plugin;
	public TrySeeInvisible(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void OnTrySeeInvisible(PlayerGameModeChangeEvent event){
		Player player = event.getPlayer();
		if(event.getNewGameMode() != GameMode.SPECTATOR){
			return;
		}
		if(!Achievementjao.getAchievement(player, new AchievementType(30))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
