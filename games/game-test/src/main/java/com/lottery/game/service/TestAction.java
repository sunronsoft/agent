package com.lottery.game.service;

import com.alibaba.fastjson.JSONObject;
import com.lottery.game.GameAction;

/**
 * @author sunron
 */

public class TestAction extends GameAction {

    @Override
    public JSONObject register(String... registerInfo) {
        System.out.println(registerInfo.toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        return jsonObject;
    }
}
