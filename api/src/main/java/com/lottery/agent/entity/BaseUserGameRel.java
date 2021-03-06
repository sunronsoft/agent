package com.lottery.agent.entity;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserGameRel<M extends BaseUserGameRel<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public M setGameId(java.lang.Integer gameId) {
		set("game_id", gameId);
		return (M)this;
	}
	
	public java.lang.Integer getGameId() {
		return getInt("game_id");
	}

	public M setAllComission(java.lang.Integer allComission) {
		set("all_comission", allComission);
		return (M)this;
	}
	
	public java.lang.Integer getAllComission() {
		return getInt("all_comission");
	}

	public M setComission(java.lang.Integer comission) {
		set("comission", comission);
		return (M)this;
	}
	
	public java.lang.Integer getComission() {
		return getInt("comission");
	}

}
