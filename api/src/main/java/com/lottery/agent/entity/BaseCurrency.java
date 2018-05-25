package com.lottery.agent.entity;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseCurrency<M extends BaseCurrency<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setCurrencyCode(java.lang.String currencyCode) {
		set("currency_code", currencyCode);
		return (M)this;
	}
	
	public java.lang.String getCurrencyCode() {
		return getStr("currency_code");
	}

	public M setCurrencyName(java.lang.String currencyName) {
		set("currency_name", currencyName);
		return (M)this;
	}
	
	public java.lang.String getCurrencyName() {
		return getStr("currency_name");
	}

	public M setExchangeRate(java.math.BigDecimal exchangeRate) {
		set("exchange_rate", exchangeRate);
		return (M)this;
	}
	
	public java.math.BigDecimal getExchangeRate() {
		return get("exchange_rate");
	}

	public M setIsDefault(java.lang.Integer isDefault) {
		set("is_default", isDefault);
		return (M)this;
	}
	
	public java.lang.Integer getIsDefault() {
		return getInt("is_default");
	}

}
