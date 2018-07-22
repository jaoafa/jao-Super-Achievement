package com.jaoafa.jaoSuperAchievement.Event;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoSuperAchievement.MySQL;
import com.jaoafa.jaoSuperAchievement.jaoSuperAchievement;
import com.jaoafa.jaoSuperAchievement.AchievementAPI.AchievementAPI;

public class Event_JSA implements Listener {
	JavaPlugin plugin;
	public Event_JSA(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	static Map<String, PlayerPageData> jSA = new HashMap<>();
	@EventHandler(ignoreCancelled = true)
	public void onPostClick(InventoryClickEvent event) {
		if(event.getWhoClicked().getType() != EntityType.PLAYER) return;
		if(event.getClickedInventory() == null) return;
		if(!event.getClickedInventory().getName().endsWith("jaoSuperAchievement")) return;
		Player player = (Player) event.getWhoClicked();

		event.setCancelled(true);

		if(!jSA.containsKey(player.getName())){
			player.sendMessage(AchievementAPI.getPrefix() + "現在開いているページを取得できませんでした。再度開きなおしてください。");
			return;
		}
		PlayerPageData ppd = jSA.get(player.getName());
		int page = ppd.getPage();
		OfflinePlayer offplayer = ppd.getViewPlayer();
		ItemStack is = event.getClickedInventory().getItem(event.getSlot());
		if(is == null || is.getType() == Material.AIR){
			return;
		}

		if(is.getItemMeta().getDisplayName().equalsIgnoreCase("閉じる")){
			player.closeInventory();
			return;
		}

		if(event.getSlot() == 0){
			// 前へ
			openPage(player, offplayer, page - 1);
		}else if(event.getSlot() == 8){
			// 次へ
			openPage(player, offplayer, page + 1);
		}
	}
	public static void openPage(Player player, OfflinePlayer offplayer, int page){
		try{
			Inventory inv = Bukkit.getServer().createInventory(player, 4 * 9, offplayer.getName() + "のjaoSuperAchievement");

			int Getted = getGettedAchievementCount(offplayer.getUniqueId());
			int notGetted = getNotGettedAchievementCount(offplayer.getUniqueId());

			setItemSkull(inv, 4, offplayer.getName(), AchievementAPI.getjaoSuperAchievement(),
					"" + ChatColor.RESET + ChatColor.AQUA + "--- " + page + " ページ ---",
					"" + ChatColor.RESET + ChatColor.GREEN + "解除済み実績数: " + Getted + "個",
					"" + ChatColor.RESET + ChatColor.RED + "未解除実績数: " + notGetted + "個");

			// インベントリスロット
			// 前へ: 0 / jSA解除済みor未解除数: 4 / 次へ: 8
			// 実績配置は9～ (1ページに27個配置可能)
			int i = 9;
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT * FROM jaoSuperAchievement_Type LIMIT ?, ?");
			statement.setInt(1, (page - 1) * 27);
			statement.setInt(2, 27);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				int id = res.getInt("id");
				String name = res.getString("name");
				String description = res.getString("description");
				boolean hidden = res.getBoolean("hidden");
				String date = res.getString("date");
				int gettedplayercount = getGettedPlayerCount(id);

				boolean getted = isGetted(offplayer, id);
				Material material;
				String msg;
				String hiddenmsg = "";
				String unlockdate = "";
				if(getted){
					material = Material.WOOL; // 取得済みなら羊毛
					msg = ChatColor.GREEN + "実績取得済み";
					unlockdate = "(解除日時: " + date + " | 解除者数: " + gettedplayercount + "人)";
				}else{
					material = Material.STAINED_GLASS; // 未取得なら色付きガラス
					msg = ChatColor.RED + "実績未取得";
					unlockdate = "(解除者数: " + gettedplayercount + "人)";

					if(hidden){ // 隠し要素なら名前と説明の文字列を全部アスタリスクにする
						name = replaceAsterisk(name);
						description = replaceAsterisk(description);
					}
				}

				if(hidden) hiddenmsg = "※隠し要素";

				int damage = id % 16;
				setItem(inv, i, material, damage, ChatColor.RESET + "[" + id + "] " + name,
						"" + ChatColor.RESET + ChatColor.GRAY + description,
						"",
						ChatColor.RESET + msg + " " + ChatColor.GOLD + ChatColor.UNDERLINE + hiddenmsg,
						"" + ChatColor.RESET + ChatColor.BLUE + unlockdate);

				//if(i >= 35) break;
				i++;
			}

			int all = getAchievementCount();
			if(page <= 1){
				setItem(inv, 0, Material.BARRIER, ChatColor.RESET + "閉じる");
			}else if(page >= 2){
				setItemSkull(inv, 0, "MHF_ArrowLeft", ChatColor.RESET + "前へ");
			}
			if(all >= (page * 27) + 1){
				// 次へを追加
				setItemSkull(inv, 8, "MHF_ArrowRight", ChatColor.RESET + "次へ");
			}else{
				setItem(inv, 8, Material.BARRIER, ChatColor.RESET + "閉じる");
			}

			PlayerPageData ppd = new PlayerPageData(offplayer, page);
			jSA.put(player.getName(), ppd);
			player.openInventory(inv);
			return;
		} catch (SQLException | ClassNotFoundException e) {
			jaoSuperAchievement.report(e);
			return;
		}
	}
	static String replaceAsterisk(String value){
		int l = value.length();
		return new StringWriter(){
			{
				for(int i=0; i < l; i++){
					write("*");
				}
			}
		}.toString();
	}
	static void setItem(Inventory inv, int index, Material material, String title, String... lore){
		ItemStack item = new ItemStack(material);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(title);
		if(lore != null) itemmeta.setLore(Arrays.asList(lore));
		item.setItemMeta(itemmeta);
		inv.setItem(index, item);
	}

	static void setItem(Inventory inv, int index, Material material, int damage, String title, String... lore){
		ItemStack item = new ItemStack(material);
		item.setDurability((short) damage);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(title);
		if(lore != null) itemmeta.setLore(Arrays.asList(lore));
		item.setItemMeta(itemmeta);
		inv.setItem(index, item);
	}
	@SuppressWarnings("deprecation")
	static void setItemSkull(Inventory inv, int index, String name, String title){
		ItemStack item = new ItemStack(Material.SKULL_ITEM);
		item.setDurability((short) 3);
		SkullMeta skull_meta = (SkullMeta) item.getItemMeta();
		skull_meta.setOwner(name);
		skull_meta.setDisplayName(title);
		item.setItemMeta(skull_meta);
		inv.setItem(index, item);
	}
	@SuppressWarnings("deprecation")
	static void setItemSkull(Inventory inv, int index, String name, String title, String... lore){
		ItemStack item = new ItemStack(Material.SKULL_ITEM);
		item.setDurability((short) 3);
		SkullMeta skull_meta = (SkullMeta) item.getItemMeta();
		skull_meta.setOwner(name);
		skull_meta.setDisplayName(title);
		if(lore != null) skull_meta.setLore(Arrays.asList(lore));
		item.setItemMeta(skull_meta);
		inv.setItem(index, item);
	}
	static boolean isGetted(OfflinePlayer offplayer, int id){
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT * FROM jaoSuperAchievement WHERE uuid = ? AND achievement_typeid = ?;");
			statement.setString(1, offplayer.getUniqueId().toString());
			statement.setInt(2, id);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			jaoSuperAchievement.report(e);
			return false;
		}
	}
	static int getGettedPlayerCount(int id){
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT COUNT(*) FROM jaoSuperAchievement WHERE achievement_typeid = ?;");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				return res.getInt(1);
			}else{
				return 0;
			}
		} catch (SQLException | ClassNotFoundException e) {
			jaoSuperAchievement.report(e);
			return 0;
		}
	}
	/**
	 * 取得済み実績数を返却
	 * @param uuid UUID
	 * @return 取得済み実績数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static int getGettedAchievementCount(UUID uuid) throws ClassNotFoundException, SQLException{
		PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT COUNT(*) FROM jaoSuperAchievement WHERE uuid = ?;");
		statement.setString(1, uuid.toString());
		ResultSet res = statement.executeQuery();
		if(res.next()){
			return res.getInt(1);
		}else{
			return 0;
		}
	}
	/**
	 * 未取得済み実績数を返却
	 * @param uuid UUID
	 * @return 未取得済み実績数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static int getNotGettedAchievementCount(UUID uuid) throws ClassNotFoundException, SQLException{
		int all = getAchievementCount();
		int getted = getGettedAchievementCount(uuid);
		return all - getted;
	}
	/**
	 * すべての実績数を返却
	 * @param uuid UUID
	 * @return すべての実績数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static int getAchievementCount() throws ClassNotFoundException, SQLException{
		PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT COUNT(*) FROM jaoSuperAchievement_Type");
		ResultSet res = statement.executeQuery();
		if(res.next()){
			return res.getInt(1);
		}else{
			return -1;
		}
	}

}
