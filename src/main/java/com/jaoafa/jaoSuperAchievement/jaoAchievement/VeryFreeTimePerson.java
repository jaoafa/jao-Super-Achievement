package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

/**
 * No. 36
 *
 * とてもお暇な方
 * もっと他にやることないの?
 * 石炭ブロックを石炭にしたあと石炭で石炭ブロックを作る
 * ※隠し要素
 *
 * @since 2018/08/20
 * @category jao Achievement
 *
 */
public class VeryFreeTimePerson implements Listener {
	JavaPlugin plugin;
	public VeryFreeTimePerson(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	Set<UUID> Craft = new HashSet<>(); // 既に石炭ブロック→石炭してるか
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void OnCraft(CraftItemEvent event){
		if(event.getWhoClicked().getType() != EntityType.PLAYER){
			return;
		}
		Player player = (Player) event.getWhoClicked();
		ItemStack is = event.getRecipe().getResult();

		if(is.getType() == Material.COAL){
			// 石炭ブロック→石炭
			Craft.add(player.getUniqueId());
		}else if(is.getType() == Material.COAL_BLOCK){
			// 石炭→石炭ブロック
			if(!Craft.contains(player.getUniqueId())){
				return;
			}
			if(!Achievementjao.getAchievement(player, new AchievementType(36))){
				player.sendMessage(AchievementAPI.getPrefix() + "実績の解除中に問題が発生しました。もう一度お試しください。");
				return;
			}
		}
	}
}
