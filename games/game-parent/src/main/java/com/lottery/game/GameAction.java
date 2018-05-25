package com.lottery.game;

import com.alibaba.fastjson.JSONObject;

public abstract class GameAction {

    public abstract JSONObject register(String... registerInfo);
}
