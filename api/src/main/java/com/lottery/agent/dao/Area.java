package com.lottery.agent.dao;

import com.lottery.agent.entity.BaseArea;

import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Area extends BaseArea<Area> {
	public static final Area dao = new Area().dao();

	public List<Area> getAllArea() {
		return find("select id, area_code, area_name from t_area order by id");
	}

	public Area getAreaInfo(int id) {
		return findById(id);
	}

	public Area getAreaInfo(String code) {
		return findFirst("select id, area_code, area_name from t_area where area_code = ?", code);
	}
}
