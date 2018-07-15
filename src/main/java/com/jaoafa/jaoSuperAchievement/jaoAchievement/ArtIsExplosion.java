package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 33
 *
 * 芸術は爆発
 * TNTトロッコを持つ
 * TNTトロッコを持って少し歩く
 * ※隠し要素
 *
 * @since 2018/07/16
 * @category jao Achievement
 *
 */
public class ArtIsExplosion implements Listener {
	JavaPlugin plugin;
	public ArtIsExplosion(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnArtIsExplosion(PlayerMoveEvent event){ // インベントリ系のイベントだといまいちなのであえて移動イベント
		Player player = (Player) event.getPlayer();

		PlayerInventory inv = player.getInventory();
		ItemStack[] invdata = inv.getContents();
		for(int n=0; n != invdata.length; n++) {
			ItemStack is = inv.getItem(n);
			if(is == null){
				continue;
			}
			if(is.getType() == Material.EXPLOSIVE_MINECART){
				if(!Achievementjao.getAchievement(player, new AchievementType(33))){
					player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
					return;
				}
				return;
			}
		}
	}
}
