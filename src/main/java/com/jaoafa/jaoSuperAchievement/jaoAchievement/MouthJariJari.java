package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 18
 *
 * 砂利が口の中に入ってじゃりじゃりする～www
 * 砂利を頭にかぶる
 * X4Zの頭をかぶる
 *
 * @since 2018/06/28
 * @category jao Achievement
 *
 */
public class MouthJariJari implements Listener {
	JavaPlugin plugin;
	public MouthJariJari(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnForestBear(PlayerMoveEvent event){ // インベントリ系のイベントだといまいちなのであえて移動イベント
		Player player = (Player) event.getPlayer();

		ItemStack helmet = player.getInventory().getHelmet();
		if(helmet == null) return;
		if(helmet.getType() != Material.SKULL_ITEM){
			return;
		}
		SkullMeta skull = (SkullMeta) helmet.getItemMeta();
		OfflinePlayer offplayer = skull.getOwningPlayer();

		if(offplayer == null){
			return;
		}

		if(!offplayer.getUniqueId().toString().equalsIgnoreCase("5799296a-d1ec-4252-93bd-440bb9caa65c")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(18))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
