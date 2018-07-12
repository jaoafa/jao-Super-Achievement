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
 * No. 27
 *
 * まことのjaoest
 * ログイン時間が1時間を超える
 * OnlineTimeが1時間を超える
 * ※隠し要素
 *
 * @since 2018/07/12
 * @category jao Achievement
 *
 */
public class Sincerity_jaoest implements Listener {
	JavaPlugin plugin;
	public Sincerity_jaoest(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event){
		Player player = (Player) event.getPlayer();
		int onlineTime = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
		int borderSec = 3600; // 1時間の秒数

		if(onlineTime < borderSec){
			return; // オンライン時間が、ボーダー時間内だったらリターン
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(27))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
