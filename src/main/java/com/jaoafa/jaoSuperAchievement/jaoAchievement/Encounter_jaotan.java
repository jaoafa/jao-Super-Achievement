package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 14
 *
 * 神との遭遇
 * jaotanに遭遇する
 * tpコマンドでjaotanに飛ぼうとした場合
 * ※隠し要素
 *
 * @since 2018/03/29
 * @category jao Achievement
 *
 */
public class Encounter_jaotan implements Listener {
	JavaPlugin plugin;
	public Encounter_jaotan(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
		Player player = event.getPlayer();
		String command = event.getMessage();
		String[] args = command.split(" ", 0);
		if(args.length >= 2){
			if(args[0].equalsIgnoreCase("/tp") && args[1].equalsIgnoreCase("jaotan")){
				if(!Achievementjao.getAchievement(player, new AchievementType(14))){
					player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
					return;
				}
			}
		}
	}
}
