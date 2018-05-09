package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 16
 *
 * BOTCH
 * 1人になる
 * 誰もいない時にログインするor誰かがログアウトして1人になる
 * ※隠し要素
 *
 * @since 2018/04/01
 * @category jao Achievement
 *
 */
public class Botch implements Listener {
	JavaPlugin plugin;
	public Botch(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		int i = Bukkit.getServer().getOnlinePlayers().size();
		Player player = event.getPlayer();
		if(i != 1){
			return;
		}
		if(!Achievementjao.getAchievement(player, new AchievementType(16))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		int i = Bukkit.getServer().getOnlinePlayers().size() - 1;
		Player botch_player = null;
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			Player left_player = event.getPlayer();
			if(!p.getUniqueId().equals(left_player.getUniqueId())){
				botch_player = p;
			}
		}

		if(botch_player == null){
			return;
		}

		if(i != 1){
			return;
		}
		if(!Achievementjao.getAchievement(botch_player, new AchievementType(16))){
			botch_player.sendMessage(AchievementAPI.getPrefix()
					+ "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
