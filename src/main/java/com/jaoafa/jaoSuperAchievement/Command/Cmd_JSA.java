package com.jaoafa.jaoSuperAchievement.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;
import com.jaoafa.jaoSuperAchievement.Event.Event_JSA;

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
		Event_JSA.openPage(player, 1);
		return true;
	}
}
