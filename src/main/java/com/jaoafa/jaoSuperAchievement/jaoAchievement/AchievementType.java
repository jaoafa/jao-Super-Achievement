package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jaoafa.jaoSuperAchievement.MySQL;
import com.jaoafa.jaoSuperAchievement.jaoSuperAchievement;

public class AchievementType {
	private int id;
	private String name;
	private String description;
	public AchievementType(int id){
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT * FROM jaoSuperAchievement_Type WHERE id = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				this.id = res.getInt("id");
				name = res.getString("name");
				description = res.getString("description");
			}else{
				throw new IllegalArgumentException("指定されたIDのjaoSuperAchievementは見つかりません。");
			}
		} catch (SQLException | ClassNotFoundException e) {
			jaoSuperAchievement.report(e);
		}
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
}
