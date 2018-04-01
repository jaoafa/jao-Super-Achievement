package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * No. 17
 *
 * いちばんのり！
 * 鯖がリスタートしてから一番最初にログインする
 * 3時の再起動後、または鯖を再起動して一番最初にログインする
 * ※隠し要素
 *
 * @since 2018/04/01
 * @category jao Achievement
 *
 */
public class FirstServerLogin implements Listener {
	JavaPlugin plugin;
	public FirstServerLogin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	boolean first = false;
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstServerLogin(PlayerJoinEvent event){
		if(first){
			return;
		}

		Player player = event.getPlayer();
		first = true;
		// どうせgetAchievement側ですでに取得してるかどうかは検査されるのでそのまま。
		if(!Achievementjao.getAchievement(player, new AchievementType(17))){
			player.sendMessage("[" + ChatColor.RED + "j" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "o" + ChatColor.GREEN + "S" + ChatColor.AQUA + "u" + ChatColor.BLUE + "p" + ChatColor.DARK_BLUE + "e" + ChatColor.RED + "r" + ChatColor.GOLD + "A" + ChatColor.YELLOW + "c" + ChatColor.GREEN + "h" + ChatColor.AQUA + "i" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "v" + ChatColor.RED + "e" + ChatColor.GOLD + "m" + ChatColor.YELLOW + "e" + ChatColor.GREEN + "n" + ChatColor.AQUA + "t" + ChatColor.RESET + "] "
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
