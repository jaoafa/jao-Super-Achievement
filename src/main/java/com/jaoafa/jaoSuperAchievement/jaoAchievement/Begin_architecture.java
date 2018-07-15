package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;
import com.jaoafa.jaoSuperAchievement.jaoAchievement.AchievementType;
import com.jaoafa.jaoSuperAchievement.jaoAchievement.Achievementjao;

/**
 * No. 31
 *
 * 建築の始まり
 * 砂箱ワールドで建築物を作る
 * SandBoxワールドでブロックを設置する
 *
 * @since 2018/07/16
 * @category jao Achievement
 *
 */
public class Begin_architecture implements Listener {
		JavaPlugin plugin;
		public Begin_architecture(JavaPlugin plugin) {
			this.plugin = plugin;
		}
		@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
		public void OnBegin_architecture(BlockPlaceEvent event){
			Player player = event.getPlayer();
			Block block = event.getBlock();
			Location loc = block.getLocation();
			if(!loc.getWorld().getName().equalsIgnoreCase("SandBox")){
				return;
			}
			if(!Achievementjao.getAchievement(player, new AchievementType(31))){
				player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
				return;
			}
		}
	}
