package com.jaoafa.jaoSuperAchievement;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.Command.Cmd_JSA;
import com.jaoafa.jaoSuperAchievement.jaoAchievement.It_was_hoe;
import com.jaoafa.jaoSuperAchievement.jaoAchievement.Speakjaotan;

public class jaoSuperAchievement extends JavaPlugin {
	/**
	 * プラグインが起動したときに呼び出し
	 * @author mine_book000
	 * @since 2018/02/15
	 */
	@Override
	public void onEnable() {
		getCommand("jsa").setExecutor(new Cmd_JSA(this));
		//
		JavaPlugin = this;

		LoadjaoAchievements();
		Load_Config(); // Config Load
	}

	private void LoadjaoAchievements(){
		getServer().getPluginManager().registerEvents(new It_was_hoe(this), this);
		getServer().getPluginManager().registerEvents(new Speakjaotan(this), this);
	}

	public static String sqlserver = "jaoafa.com";
	public static String sqluser;
	public static String sqlpassword;
	public static Connection c = null;
	public static FileConfiguration conf;
	public static JavaPlugin JavaPlugin;
	/**
	 * コンフィグ読み込み
	 * @author mine_book000
	 */
	private void Load_Config(){
		conf = getConfig();

		if(conf.contains("discordtoken")){
			Discord.start(this, conf.getString("discordtoken"));
		}else{
			getLogger().info("Discordへの接続に失敗しました。 [conf NotFound]");
			getLogger().info("Disable jaoSuperAchievement...");
			getServer().getPluginManager().disablePlugin(this);
		}
		if(conf.contains("sqluser") && conf.contains("sqlpassword")){
			jaoSuperAchievement.sqluser = conf.getString("sqluser");
			jaoSuperAchievement.sqlpassword = conf.getString("sqlpassword");
		}else{
			getLogger().info("MySQL Connect err. [conf NotFound]");
			getLogger().info("Disable jaoSuperAchievement...");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		if(conf.contains("sqlserver")){
			sqlserver = (String) conf.get("sqlserver");
		}

		MySQL MySQL = new MySQL(sqlserver, "3306", "jaoafa", sqluser, sqlpassword);

		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			getLogger().info("MySQL Connect err. [ClassNotFoundException]");
			getLogger().info("Disable jaoSuperAchievement...");
			getServer().getPluginManager().disablePlugin(this);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			getLogger().info("MySQL Connect err. [SQLException: " + e.getSQLState() + "]");
			getLogger().info("Disable jaoSuperAchievement...");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		getLogger().info("MySQL Connect successful.");
	}

	/**
	 * プラグインが停止したときに呼び出し
	 * @author mine_book000
	 * @since 2018/02/15
	 */
	@Override
	public void onDisable() {

	}

	public static Statement getNewStatement(){
		Statement statement;
		try {
			statement = jaoSuperAchievement.c.createStatement();
		} catch (NullPointerException e) {
			MySQL MySQL = new MySQL(jaoSuperAchievement.sqlserver, "3306", "jaoafa", jaoSuperAchievement.sqluser, jaoSuperAchievement.sqlpassword);
			try {
				jaoSuperAchievement.c = MySQL.openConnection();
				statement = jaoSuperAchievement.c.createStatement();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO 自動生成された catch ブロック
				report(e);
				return null;
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			report(e);
			return null;
		}

		statement = MySQL.check(statement);
		return statement;
	}
	public static void SendMessage(CommandSender sender, Command cmd, String text) {
		sender.sendMessage("[AntiAlts2] " + ChatColor.YELLOW + text);
	}

	public static void report(Throwable exception){
		exception.printStackTrace();
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
		Discord.send("293856671799967744", "AntiAlts2でエラーが発生しました。" + "\n"
					+ "```" + sw.toString() + "```\n"
					+ "Cause: `" + exception.getCause() + "`");
	}

	public static JavaPlugin JavaPlugin(){
		return JavaPlugin;
	}
}
