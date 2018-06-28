package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.jaoafa.jaoSuperAchievement.Discord;
import com.jaoafa.jaoSuperAchievement.jaoSuperAchievement;
import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;
import com.jaoafa.jaoSuperAchievement.AchievementAPI.jaoSuperAchievementEvent;

public class Achievementjao {
	public void AlreadyAchievementCheck(int id){
		AchievementType type = new AchievementType(id);
		type.getName();
	}

	static private FireworkEffect.Type[] types = {
			Type.BALL,
			Type.BALL_LARGE,
			Type.BURST,
			Type.CREEPER,
			Type.STAR,
	};
	static private Random rand = new Random();

	public static boolean getAchievement(Player player, AchievementType type){
		if(isAlreadyGettedAchievement(player, type)){
			return true; // すでに取得済みだけどエラーじゃないので
		}
		Statement statement = jaoSuperAchievement.getNewStatement();

		try {
			statement.executeUpdate("INSERT INTO jaoSuperAchievement (player, uuid, achievement_typeid, date) VALUES ('" + player.getName() + "', '" + player.getUniqueId() + "', " + type.getID() + ", CURRENT_TIMESTAMP);");
		} catch (SQLException e) {
			jaoSuperAchievement.report(e);
			return false;
		}

		Bukkit.broadcastMessage(AchievementAPI.getPrefix() + player.getName() + "が「" + type.getName() + "」を取得しました！");
		Discord.send("**[jaoSuperAchievement]** " + player.getName() + "が「" + type.getName() + "」を取得しました！");

		jaoSuperAchievementEvent jaoSuperAchievementEvent = new jaoSuperAchievementEvent(player, type);
		Bukkit.getServer().getPluginManager().callEvent(jaoSuperAchievementEvent);

		Firework firework = player.getLocation().getWorld().spawn(player.getLocation().add(0, 3, 0), Firework.class);
		FireworkMeta meta = firework.getFireworkMeta();
		Builder effect = FireworkEffect.builder();
		effect.with(types[rand.nextInt(types.length)]);
		effect.withColor(Color.RED, Color.AQUA, Color.YELLOW, Color.GREEN, Color.LIME, Color.ORANGE, Color.PURPLE);
		effect.withFade(Color.RED, Color.AQUA, Color.YELLOW, Color.GREEN, Color.LIME, Color.ORANGE, Color.PURPLE);
		effect.flicker(true);
		meta.setPower(1);
		meta.addEffect(effect.build());
		firework.setFireworkMeta(meta);
		new BukkitRunnable() {
			public void run() {
				firework.detonate();
			}
		}.runTaskLater(jaoSuperAchievement.JavaPlugin(), 1);
		return true;
	}
	public static boolean getAchievement(OfflinePlayer offplayer, AchievementType type){
		if(isAlreadyGettedAchievement(offplayer, type)){
			return true; // すでに取得済みだけどエラーじゃないので
		}
		Statement statement = jaoSuperAchievement.getNewStatement();

		try {
			statement.executeUpdate("INSERT INTO jaoSuperAchievement (player, uuid, achievement_typeid, date) VALUES ('" + offplayer.getName() + "', '" + offplayer.getUniqueId() + "', " + type.getID() + ", CURRENT_TIMESTAMP);");
		} catch (SQLException e) {
			jaoSuperAchievement.report(e);
			return false;
		}

		Bukkit.broadcastMessage(AchievementAPI.getPrefix() + offplayer.getName() + "が「" + type.getName() + "」を取得しました！");
		Discord.send("**[jaoSuperAchievement]** " + offplayer.getName() + "が「" + type.getName() + "」を取得しました！");

		jaoSuperAchievementEvent jaoSuperAchievementEvent = new jaoSuperAchievementEvent(offplayer, type);
		Bukkit.getServer().getPluginManager().callEvent(jaoSuperAchievementEvent);
		return true;
	}
	public static boolean isAlreadyGettedAchievement(OfflinePlayer player, AchievementType type){
		Statement statement = jaoSuperAchievement.getNewStatement();
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM jaoSuperAchievement WHERE uuid = '" + player.getUniqueId().toString() + "' AND achievement_typeid = " + type.getID() + ";");
			if(res.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			jaoSuperAchievement.report(e);
			return false;
		}
	}
}
