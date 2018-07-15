package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 32
 *
 * はじめてのものまね
 * 直前に送られたメッセージと同じメッセージを送る
 * 直前に送った別プレイヤーのメッセージと同じものを送信
 *
 * @since 2018/07/16
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class FirstMimicry implements Listener {
	JavaPlugin plugin;
	public FirstMimicry(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	String OLDMessage = null;
	UUID OLDPlayerUUID = null;
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjao_afa(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();

		if(!message.equals(OLDMessage)){
			OLDMessage = message;
			OLDPlayerUUID = player.getUniqueId();
			return; // メッセージが同じじゃなかったらはじく
		}
		if(OLDPlayerUUID.equals(player.getUniqueId())){
			OLDMessage = message;
			OLDPlayerUUID = player.getUniqueId();
			return; // プレイヤーが同じだったらはじく
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(32))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}

		OLDMessage = message;
		OLDPlayerUUID = player.getUniqueId();
	}
}
