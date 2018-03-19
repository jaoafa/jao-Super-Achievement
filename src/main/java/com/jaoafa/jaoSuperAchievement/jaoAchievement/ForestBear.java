package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.ChatColor;
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

/**
 * No. 7
 *
 * もりのくまさん
 * くまのアタマをかぶる
 * mine_book000の頭をかぶる
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
		if(helmet.getType() != Material.SKULL_ITEM){
			return;
		}
		SkullMeta skull = (SkullMeta) helmet.getItemMeta();
		OfflinePlayer offplayer = skull.getOwningPlayer();

		if(!offplayer.getUniqueId().toString().equalsIgnoreCase("32ff7cdc-a1b4-450a-aa7e-6af75fe8c37c")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(7))){
			player.sendMessage("[" + ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S" + ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m" + ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.RESET + "] "
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
