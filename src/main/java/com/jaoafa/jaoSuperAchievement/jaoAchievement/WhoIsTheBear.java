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
 * No. 8
 *
 * くまさんはだれ
 * くまになりきる
 * mine_book000の頭をかぶって帽子以外の革の防具を身に付ける
 *
 * @since 2018/03/20
 * @category jao Achievement
 *
 */
public class WhoIsTheBear implements Listener {
	JavaPlugin plugin;
	public WhoIsTheBear(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnWhoIsTheBear(PlayerMoveEvent event){ // インベントリ系のイベントだといまいちなのであえて移動イベント
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

		ItemStack chestplate = player.getInventory().getChestplate();
		if(chestplate == null) return;
		if(chestplate.getType() != Material.LEATHER_CHESTPLATE){
			return;
		}

		ItemStack leggings = player.getInventory().getLeggings();
		if(leggings == null) return;
		if(leggings.getType() != Material.LEATHER_LEGGINGS){
			return;
		}

		ItemStack boots = player.getInventory().getBoots();
		if(boots == null) return;
		if(boots.getType() != Material.LEATHER_BOOTS){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(8))){
			player.sendMessage("[" + ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S" + ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m" + ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.RESET + "] "
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
