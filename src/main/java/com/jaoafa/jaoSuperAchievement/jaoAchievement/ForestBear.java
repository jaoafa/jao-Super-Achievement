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
 * No. 7
 *
 * もりのくまさん
 * くまのアタマをかぶる
 * mine_book000の頭をかぶる
 * ※隠し要素
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
public class ForestBear implements Listener {
	JavaPlugin plugin;
	public ForestBear(JavaPlugin plugin) {
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

		if(!offplayer.getUniqueId().toString().equalsIgnoreCase("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(7))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
