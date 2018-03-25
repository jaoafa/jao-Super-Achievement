package com.jaoafa.jaoSuperAchievement.AchievementAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.jaoafa.jaoSuperAchievement.jaoAchievement.AchievementType;

public class jaoSuperAchievementEvent extends Event {
    private Player player;
    private AchievementType type;
    private static final HandlerList handlers = new HandlerList();

    public jaoSuperAchievementEvent(Player player, AchievementType type){
        this.player = player;
        this.type = type;
    }

    public Player getPlayer(){
    	return player;
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
