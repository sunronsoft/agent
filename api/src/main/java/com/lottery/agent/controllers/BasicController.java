package com.lottery.agent.controllers;

import com.alibaba.fastjson.JSONObject;
import com.lottery.agent.BaseController;
import com.lottery.agent.common.Consts;
import com.lottery.agent.dao.Area;
import com.lottery.agent.dao.Currency;

import java.util.List;

public class BasicController extends BaseController {

    public void getarea() {
        JSONObject result = new JSONObject();

        try {
            List<Area> areaList = Area.dao.getAllArea();
            result.put("code", Consts.RESULT_CODE_SUCCESS);
            result.put("msg", "获取数据成功");
            result.put("data", areaList);
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }

    public void getcurrency() {
        JSONObject result = new JSONObject();

        try {
            List<Currency> currencyList = Currency.dao.getAllCurrency();
            result.put("code", Consts.RESULT_CODE_SUCCESS);
            result.put("msg", "获取数据成功");
            result.put("data", currencyList);
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }
}
