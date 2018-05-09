package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

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
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
