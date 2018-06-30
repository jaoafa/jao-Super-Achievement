package com.jaoafa.jaoSuperAchievement.Event;

import org.bukkit.OfflinePlayer;

class PlayerPageData {
	private OfflinePlayer offplayer;
	private int page;

	PlayerPageData(OfflinePlayer offplayer, int page){
		this.offplayer = offplayer;
		this.page = page;
	}

	OfflinePlayer getViewPlayer(){
		return offplayer;
	}

	int getPage(){
		return page;
	}
}