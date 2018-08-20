package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 37
 *
 * お前はおはようございますをございますというのか
 * jaoを言わずafaと言ったら怒られる
 * jaoを言わずafaと言った場合
 * ※隠し要素
 *
 * @since 2018/08/20
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class SpeakOnlyafa implements Listener {
	JavaPlugin plugin;
	public SpeakOnlyafa(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	Set<UUID> Alreadyjao = new HashSet<>(); // 既にjaoと発言しているか(ログイン時にリセット)
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjao_afa(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(message.equalsIgnoreCase("jao")){
			Alreadyjao.add(player.getUniqueId());
			return;
		}
		if(!message.equalsIgnoreCase("afa")){
			return;
		}
		if(Alreadyjao.contains(player.getUniqueId())){
			return;
		}


		if(!Achievementjao.getAchievement(player, new AchievementType(37))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnFirstjao(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(!Alreadyjao.contains(player.getUniqueId())){
			return;
		}
		Alreadyjao.remove(player.getUniqueId());
	}
}
