package com.lottery.agent.entity;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseArea<M extends BaseArea<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setAreaCode(java.lang.String areaCode) {
		set("area_code", areaCode);
		return (M)this;
	}
	
	public java.lang.String getAreaCode() {
		return getStr("area_code");
	}

	public M setAreaName(java.lang.String areaName) {
		set("area_name", areaName);
		return (M)this;
	}
	
	public java.lang.String getAreaName() {
		return getStr("area_name");
	}

}
