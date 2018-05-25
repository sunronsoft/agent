package com.lottery.agent.controllers;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.lottery.agent.BaseController;
import com.lottery.agent.common.Consts;
import com.lottery.agent.dao.Game;
import com.lottery.agent.dao.User;
import com.lottery.agent.dao.UserGameRel;

import java.util.List;

public class GameController extends BaseController {

    public void list() {
        JSONObject result = new JSONObject();
        try {
            if (StrKit.notBlank(getToken())) {
                Record user = User.dao.getUserInfo(getToken());
                if (null != user) {
                    if ((user.getInt("user_type") == Consts.USER_TYPE_AGENT && user.getInt("user_level") == Consts.USER_LEVEL_MEMBER) ||
                            user.getInt("user_type") == Consts.USER_TYPE_COMPANY) {
                        List<Game> gameList = Game.dao.getGameList();

                        result.put("code", Consts.RESULT_CODE_SUCCESS);
                        result.put("data", gameList);
                    } else {
                        List<Record> gameList = UserGameRel.dao.getUserGameList(user.getInt("id"));

                        result.put("code", Consts.RESULT_CODE_SUCCESS);
                        result.put("data", gameList);
                    }
                } else {
                    result.put("code", Consts.RESULT_CODE_ACCOUNT_ERROR);
                    result.put("msg", "你还没有登录");
                }
            } else {
                result.put("code", Consts.RESULT_CODE_NOT_LOGIN);
                result.put("msg", "你还没有登录");
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }
}
