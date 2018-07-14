package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

public class Jaoest extends BukkitRunnable {
	JavaPlugin plugin;
	public Jaoest(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		for(Player player: Bukkit.getOnlinePlayers()){
			Forever(player);
			Charisma(player);
			Super(player);
			Sincerity(player);
			Normal(player);
		}
	}

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
	void Forever(Player player){
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
	/**
	 * No. 25
	 *
	 * カリスマjaoest
	 * ログイン時間が1週間を超える
	 * OnlineTimeが1週間(7日)を超える
	 * ※隠し要素
	 *
	 * @since 2018/07/12
	 * @category jao Achievement
	 *
	 */
	void Charisma(Player player){
		int onlineTime = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
		int borderSec = 604800; // 1週間の秒数

		if(onlineTime < borderSec){
			return; // オンライン時間が、ボーダー時間内だったらリターン
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(25))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
	/**
	 * No. 26
	 *
	 * スーパーjaoest
	 * ログイン時間が1日を超える
	 * OnlineTimeが1日を超える
	 * ※隠し要素
	 *
	 * @since 2018/07/12
	 * @category jao Achievement
	 *
	 */
	void Super(Player player){
		int onlineTime = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
		int borderSec = 86400; // 1日の秒数

		if(onlineTime < borderSec){
			return; // オンライン時間が、ボーダー時間内だったらリターン
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(26))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
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
	void Sincerity(Player player){
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
	/**
	 * No. 28
	 *
	 * ふつうのjaoest
	 * ログイン時間が4096GameTickを超える
	 * OnlineTimeが4096GameTickを超える
	 * ※隠し要素
	 *
	 * @since 2018/07/12
	 * @category jao Achievement
	 *
	 */
	void Normal(Player player){
		int onlineGameTick = player.getStatistic(Statistic.PLAY_ONE_TICK);
		int borderGameTick = 4096;

		if(onlineGameTick < borderGameTick){
			return; // オンライン時間が、ボーダー時間内だったらリターン
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(28))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
