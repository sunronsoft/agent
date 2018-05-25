package com.lottery.agent.entity;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUserPermission<M extends BaseUserPermission<M>> extends Model<M> implements IBean {

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("user_id");
	}

	public M setPermissionId(java.lang.Integer permissionId) {
		set("permission_id", permissionId);
		return (M)this;
	}
	
	public java.lang.Integer getPermissionId() {
		return getInt("permission_id");
	}

}