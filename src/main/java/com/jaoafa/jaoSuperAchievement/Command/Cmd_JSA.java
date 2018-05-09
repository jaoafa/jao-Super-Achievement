package com.jaoafa.jaoSuperAchievement.Command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.jaoSuperAchievement;
import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;
import com.jaoafa.jaoSuperAchievement.jaoAchievement.AchievementType;

public class Cmd_JSA implements CommandExecutor {
	JavaPlugin plugin;
	public Cmd_JSA(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(AchievementAPI.getPrefix() + "このコマンドはプレイヤーからのみ実行できます。");
			return true;
		}
		Player player = (Player) sender;
		Statement statement = jaoSuperAchievement.getNewStatement();
		Statement statement2 = jaoSuperAchievement.getNewStatement();
		try {
			ResultSet res_count = statement2.executeQuery("SELECT COUNT(*) FROM jaoSuperAchievement WHERE uuid = '" + player.getUniqueId().toString() + "';");
			if(res_count.next()){
				int i = res_count.getInt(1);
				player.sendMessage(AchievementAPI.getPrefix() + "解除された実績が" + i + "個あります。");
			}else{
				player.sendMessage(AchievementAPI.getPrefix() + "解除された実績がありません。");
				return true;
			}
			ResultSet res = statement.executeQuery("SELECT * FROM jaoSuperAchievement WHERE uuid = '" + player.getUniqueId().toString() + "';");
			while(res.next()){
				int typeid = res.getInt("achievement_typeid");
				String date = res.getString("date");
				AchievementType type = new AchievementType(typeid);
				player.sendMessage(AchievementAPI.getPrefix()
						+ type.getName() + " - " + type.getDescription() + " - " + date);
			}
			return true;
		} catch (SQLException e) {
			jaoSuperAchievement.report(e);
			return true;
		}
	}
}
