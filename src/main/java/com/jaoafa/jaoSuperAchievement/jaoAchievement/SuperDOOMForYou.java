package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 10
 *
 * 最高のdoomをあなたに
 * 累計4096のTNTブロックを設置する
 * 累計4096のTNTブロックを設置する
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
public class SuperDOOMForYou implements Listener {
	JavaPlugin plugin;
	public SuperDOOMForYou(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSuperDOOMForYou(BlockPlaceEvent event){ // インベントリ系のイベントだといまいちなのであえて移動イベント
		Player player = (Player) event.getPlayer();

		int tntcount = player.getStatistic(Statistic.USE_ITEM, Material.TNT);
		tntcount++; // イベント発生時には今回の分が入っていないので

		if(tntcount < 4096){
			return; // 4096以下
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(10))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
