package com.jaoafa.jaoSuperAchievement.jaoAchievement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jaoafa.jaoSuperAchievement.jaoSuperAchievement;

public class AchievementType {
	private int id;
	private String name;
	private String description;
	public AchievementType(int id){
		Statement statement = jaoSuperAchievement.getNewStatement();
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM jaoSuperAchievement_Type WHERE id = " + id);
			if(res.next()){
				this.id = res.getInt("id");
				name = res.getString("name");
				description = res.getString("description");
			}else{
				throw new IllegalArgumentException("指定されたIDのjaoSuperAchievementは見つかりません。");
			}
		} catch (SQLException e) {
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
