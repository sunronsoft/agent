package com.lottery.agent.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.lottery.agent.BaseController;
import com.lottery.agent.common.Consts;
import com.lottery.agent.dao.User;
import com.lottery.agent.util.DateUtil;
import com.lottery.agent.util.IpUtil;

public class AgentController extends BaseController {

    public void register() {
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.notBlank(postData)) {
                JSONObject object = JSON.parseObject(postData);

                User user = new User();
                if (object.getInteger("account_type").equals(1)) {

                } else {

                }
                if (StrKit.notBlank(object.getString("currency_code"))) {
                    user.setCurrencyCode(object.getString("currency_code"));
                    user.setUserLevel(object.getInteger("user_level"));
                    user.setParentId(object.getInteger("parent_id"));
                    user.setWechat(object.getString("wechat"));
                } else {
                    user.setMasterId(object.getInteger("parent_id"));
                }
                user.setFirstName(object.getString("first_name"));
                user.setAccount(object.getString("first_name") + object.getString("account"));
                user.setPassword(object.getString("password"));
                user.setNickName(object.getString("account"));
                user.setUserType(object.getInteger("user_type"));
                user.setIsPause(Consts.PAUSE_NO);
                user.setCanBet(Consts.STATUS_YES);
                //user.setComission(object.getInteger("comission"));
                user.setMaxLevel(object.getInteger("maxlevel"));
                user.setIsAnonymous(Consts.STATUS_NO);
                user.setNextTime(1);
                user.setRemark(object.getString("remark"));
                user.setMaxWin(object.getBigDecimal("max_win"));
                user.setMaxLose(object.getBigDecimal("max_lose"));
                user.setCreateTime(DateUtil.CurrentDate());
                user.setUpdateTime(DateUtil.CurrentTime());

                Record record = User.dao.registerUser(user);

                if (null == record) {
                    result.put("code", Consts.RESULT_CODE_FAIL);
                    result.put("msg", "注册失败");
                } else if (record.getColumns().containsKey("msg")) {
                    result.put("code", record.getInt("code"));
                    result.put("msg", record.getStr("msg"));
                } else {
                    result.put("code", Consts.RESULT_CODE_SUCCESS);
                    result.put("data", record);
                }
            } else {
                result.put("code", Consts.RESULT_CODE_REGISTER_EMPTY);
                result.put("msg", "注册失败，注册信息不能为空");
                logger.error("registion infomation is null");
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误，注册失败");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }

    public void login() {
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.notBlank(postData)) {
                JSONObject object = JSON.parseObject(postData);
                String account = object.getString("account");
                String password = object.getString("password");

                Record record = User.dao.userLogin(account, password, Consts.USER_TYPE_AGENT, IpUtil.getRealIp(getRequest()));
                if (null == record) {
                    result.put("code", Consts.RESULT_CODE_LOGIN_ERROR);
                    result.put("msg", "登录失败，登录账号或密码错误");
                } else if (record.getInt("is_pause").equals(Consts.PAUSE_YES)) {
                    result.put("code", Consts.RESULT_CODE_ACCOUNT_DISABLED);
                    result.put("msg", "登录失败，登录账号已被禁用");
                } else {
                    result.put("code", Consts.RESULT_CODE_SUCCESS);
                    result.put("data", record);
                }
            } else {
                result.put("code", Consts.RESULT_CODE_ACCOUNT_ERROR);
                result.put("msg", "登录失败，登录账号和密码不能为空");
                logger.error("account or password is null");
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误，登录失败");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }
}
