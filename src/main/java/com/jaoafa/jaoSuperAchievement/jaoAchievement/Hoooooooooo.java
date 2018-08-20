package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 34
 *
 * ほおおおおおおおおおおおｗｗｗｗｗ
 * ほおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおおｗｗｗｗｗｗｗｗｗｗｗｗｗｗｗｗｗｗ
 * (((（ ´◔ ω◔`）)))ほおおおおおおおおおおおおおおおおおおおおおおおｗｗｗｗｗｗｗｗｗｗｗ と発言した場合
 *
 * @since 2018/08/20
 * @category jao Achievement
 *
 */
@SuppressWarnings("deprecation")
public class Hoooooooooo implements Listener {
	JavaPlugin plugin;
	public Hoooooooooo(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnSpeakjaojao(PlayerChatEvent event){
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(!message.equalsIgnoreCase("(((（ ´◔ ω◔`）)))ほおおおおおおおおおおおおおおおおおおおおおおおｗｗｗｗｗｗｗｗｗｗｗ")){
			return;
		}

		if(!Achievementjao.getAchievement(player, new AchievementType(34))){
			player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
			return;
		}
	}
}
