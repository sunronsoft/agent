package com.lottery.agent.entity;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePermission<M extends BasePermission<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setPermissionName(java.lang.String permissionName) {
		set("permission_name", permissionName);
		return (M)this;
	}
	
	public java.lang.String getPermissionName() {
		return getStr("permission_name");
	}

	public M setPermissionRemark(java.lang.String permissionRemark) {
		set("permission_remark", permissionRemark);
		return (M)this;
	}
	
	public java.lang.String getPermissionRemark() {
		return getStr("permission_remark");
	}

}
