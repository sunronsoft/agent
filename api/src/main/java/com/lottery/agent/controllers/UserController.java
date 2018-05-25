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

import java.util.List;
import java.util.Random;

public class UserController extends BaseController {

    public void login() {
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.notBlank(postData)) {
                JSONObject object = JSON.parseObject(postData);
                String account = object.getString("account");
                String password = object.getString("password");

                Record record = User.dao.userLogin(account, password, Consts.USER_TYPE_MEMBER, IpUtil.getRealIp(getRequest()));
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

    public void logout() {
        JSONObject result = new JSONObject();
        try {
            if (StrKit.notBlank(getToken())) {
                if (User.dao.userLogout(getToken())) {
                    result.put("code", Consts.RESULT_CODE_NOT_LOGIN);
                } else {
                    result.put("code", Consts.RESULT_CODE_FAIL);
                    result.put("msg", "退出账号失败");
                }
            } else {
                result.put("code", Consts.RESULT_CODE_FAIL);
                result.put("msg", "你还没有登录");
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }

    public void register() {
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.notBlank(postData)) {
                JSONObject object = JSON.parseObject(postData);

                User user = new User();
                user.setFirstName(object.getString("first_name"));
                user.setAccount(object.getString("first_name") + object.getString("account"));
                user.setPassword(object.getString("password"));
                user.setNickName(object.getString("account"));
                user.setUserType(Consts.USER_TYPE_MEMBER);
                user.setParentId(object.getInteger("parent_id"));
                user.setUserLevel(Consts.USER_LEVEL_MEMBER);
                user.setIsPause(Consts.PAUSE_NO);
                user.setCanBet(Consts.STATUS_YES);
                user.setComission(0);
                user.setMaxLevel(0);
                user.setIsAnonymous(Consts.STATUS_NO);
                user.setMasterId(0);
                user.setNextTime(1);
                user.setWechat(object.getString("wechat"));
                user.setPhone(object.getString("phone"));
                user.setRemark(object.getString("remark"));
                user.setMaxWin(object.getBigDecimal("max_win"));
                user.setMaxLose(object.getBigDecimal("max_lose"));
                user.setCreateTime(DateUtil.CurrentDate());
                user.setUpdateTime(DateUtil.CurrentTime());

                Record record = User.dao.registerUser(user);

                if (null == record) {
                    result.put("code", Consts.RESULT_CODE_FAIL);
                    result.put("msg", "新增会员失败");
                } else if (record.getColumns().containsKey("msg")) {
                    result.put("code", record.getInt("code"));
                    result.put("msg", record.getStr("msg"));
                } else {
                    result.put("code", Consts.RESULT_CODE_SUCCESS);
                    result.put("data", record);
                }
            } else {
                result.put("code", Consts.RESULT_CODE_REGISTER_EMPTY);
                result.put("msg", "新增会员失败，会员信息不能为空");
                logger.error("registion infomation is null");
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误，新增会员失败");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }

    public void check() {
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.isBlank(postData)) {
                result.put("code", Consts.RESULT_CODE_REGISTER_EMPTY);
                result.put("msg", "获取用户资料失败");
                logger.error("获取用户资料失败");
            } else {
                JSONObject object = JSON.parseObject(postData);
                String firstName = object.getString("first_name");
                String account = object.getString("account");
                if (StrKit.notBlank(firstName, account)) {
                    account = firstName + account;
                    boolean isExist = User.dao.checkAccountExist(0, account);
                    result.put("code", Consts.RESULT_CODE_SUCCESS);
                    result.put("msg", isExist ? "已经存在有该用户，新增用户失败" : "可以新增");
                    result.put("data", isExist);
                } else {
                    result.put("code", Consts.RESULT_CODE_REGISTER_EMPTY);
                    result.put("msg", "获取用户资料失败");
                    logger.error("获取用户资料失败");
                }
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误，检查用户失败");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }

    public void generator() {
        String firstName = null;
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.notBlank(postData)) {
                JSONObject object = JSON.parseObject(postData);
                firstName = object.getString("first_name");
            } else {
                Record record = User.dao.getUserInfo(getToken());
                if (record != null) {
                    firstName = record.getStr("first_name");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (StrKit.isBlank(firstName)) {
            logger.error("获取用户资料失败");
            result.put("code", Consts.RESULT_CODE_FAIL);
            result.put("msg", "生成用户帐号失败");
        } else {
            String source = "abcdefghijklmnopqrstuvwxyz1234567890";
            char[] text = new char[5];
            Random random = new Random();
            String account;
            boolean isExist;

            do {
                for (int i = 0; i < 5; i++) {
                    text[i] = source.charAt(random.nextInt(source.length()));
                }
                account = new String(text);
                isExist = User.dao.checkAccountExist(0, firstName + account);
            } while (isExist);

            result.put("code", Consts.RESULT_CODE_SUCCESS);
            result.put("data", account);
        }

        renderJson(result);
    }

    public void list() {
        JSONObject result = new JSONObject();

        try {
            String postData = HttpKit.readData(getRequest());
            if (StrKit.isBlank(postData)) {
                result.put("code", Consts.RESULT_CODE_REGISTER_EMPTY);
                result.put("msg", "获取用户列表失败");
                logger.error("获取用户列表失败");
            } else {
                JSONObject object = JSON.parseObject(postData);
                Integer userType = object.getInteger("user_type");
                Integer parentId = object.getInteger("user_id");
                Integer masterId = object.getInteger("master_id");

                List<User> userList = User.dao.getUserList(parentId, masterId, null, null, null, null, userType);
                result.put("code", Consts.RESULT_CODE_SUCCESS);
                result.put("data", userList);
            }
        } catch (Exception e) {
            result.put("code", Consts.RESULT_CODE_SYSTEM_ERROR);
            result.put("msg", "系统错误，获取用户列表失败");
            logger.error(e.getMessage(), e);
        }

        renderJson(result);
    }
}
