package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 24
 *
 * えいえんのjaoest
 * ログイン時間が1ヶ月を超える
 * OnlineTimeが1ヶ月(30日)を超える
 * ※隠し要素
 *
 * @since 2018/07/12
 * @category jao Achievement
 *
 */
public class Forever_jaoest implements Listener {
	JavaPlugin plugin;
	public Forever_jaoest(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event){
		Player player = (Player) event.getPlayer();
		int onlineTime = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
		int borderSec = 2592000; // 1か月の秒数

		if(onlineTime < borderSec){
			return; // オンライン時間が、ボーダー時間内だったらリターン
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(24))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
