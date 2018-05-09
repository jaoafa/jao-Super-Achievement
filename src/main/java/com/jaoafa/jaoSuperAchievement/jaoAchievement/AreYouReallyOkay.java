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
 * No. 15
 *
 * きみほんとにだいじょうぶ？
 * リピートコマンドブロックを持つ
 * リピートコマンドブロックをアイテムスロットに入れる
 * ※隠し要素
 *
 * @since 2018/03/31
 * @category jao Achievement
 *
 */
public class AreYouReallyOkay implements Listener {
	JavaPlugin plugin;
	public AreYouReallyOkay(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnAreYouReallyOkay(PlayerMoveEvent event){ // インベントリ系のイベントだといまいちなのであえて移動イベント
		Player player = (Player) event.getPlayer();

		PlayerInventory inv = player.getInventory();
		ItemStack is = inv.getItemInMainHand();
		if(is.getType() == Material.COMMAND_REPEATING){
			if(!Achievementjao.getAchievement(player, new AchievementType(15))){
				player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
				return;
			}
		}
	}
}
