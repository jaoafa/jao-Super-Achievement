package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * It Was Hoe
 * 鍬を掲げて鍬への愛を叫ぶ。
 * 鍬を手に持った状態で「IWH！！IWH！！」と発言した場合
 *
 * @since 2018/02/17
 * @category jao Achievement
 *
 */
public class It_was_hoe implements Listener {
	JavaPlugin plugin;
	public It_was_hoe(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnIWH(AsyncPlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(!message.equalsIgnoreCase("IWH！！IWH！！")){
			return;
		}
		ItemStack is = player.getInventory().getItemInHand();
		if(is == null){
			return;
		}
		Material type = is.getType();
		if(type != Material.WOOD_HOE
			&& type != Material.STONE_HOE
			&& type != Material.IRON_HOE
			&& type != Material.GOLD_HOE
			&& type != Material.DIAMOND_HOE){
			return;
		}
	}
}
