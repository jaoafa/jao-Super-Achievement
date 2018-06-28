package com.jaoafa.jaoSuperAchievement.AchievementAPI;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.jaoafa.jaoSuperAchievement.jaoAchievement.AchievementType;

public class jaoSuperAchievementEvent extends Event {
    private OfflinePlayer offplayer;
    private AchievementType type;
    private static final HandlerList handlers = new HandlerList();

    public jaoSuperAchievementEvent(OfflinePlayer player, AchievementType type){
        this.offplayer = player;
        this.type = type;
    }

    public OfflinePlayer getPlayer(){
    	return offplayer;
    }

    public AchievementType getType(){
    	return type;
    }

	@Override
	public HandlerList getHandlers() {
		// TODO 自動生成されたメソッド・スタブ
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
