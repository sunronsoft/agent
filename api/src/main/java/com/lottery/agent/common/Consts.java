package com.lottery.agent.common;

public class Consts {

    /**
     * 状态：0 不可用，1 正常
     */
    public static final int STATUS_YES = 1;
    public static final int STATUS_NO = 0;

    /**
     * 暂停状态：0 暂停 1 正常
     */
    public static final int PAUSE_YES = 0;
    public static final int PAUSE_NO = 1;

    /**
     * 用户类型：0 会员 1 代理（包括子账号） 2 公司
     */
    public static final int USER_TYPE_MEMBER = 0;
    public static final int USER_TYPE_AGENT = 1;
    public static final int USER_TYPE_COMPANY = 2;

    /**
     * 子账号类别：1管理子帐 2查看子帐 3占成子帐 4全权子帐
     */
    public static final int SUB_ACCOUNT_MANAGE = 1;
    public static final int SUB_ACCOUNT_VIEW = 2;
    public static final int SUB_ACCOUNT_COMISSION = 3;
    public static final int SUB_ACCOUNT_ALL = 4;

    /**
     * 用户级别：0 会员，从 1 开始为代理级别，暂时只设10级代理
     */
    public static final int USER_LEVEL_MEMBER = 0;
    public static final int USER_LEVEL_AGENT_01 = 1;
    public static final int USER_LEVEL_AGENT_02 = 2;
    public static final int USER_LEVEL_AGENT_03 = 3;
    public static final int USER_LEVEL_AGENT_04 = 4;
    public static final int USER_LEVEL_AGENT_05 = 5;
    public static final int USER_LEVEL_AGENT_06 = 6;
    public static final int USER_LEVEL_AGENT_07 = 7;
    public static final int USER_LEVEL_AGENT_08 = 8;
    public static final int USER_LEVEL_AGENT_09 = 9;
    public static final int USER_LEVEL_AGENT_10 = 10;

    /**
     * 最大代理级别
     */
    public static final int USER_LEVEL_AGENT_MAX = 10;

    /**
     * 返回状态：
     *          -1000 失败，
     *          10000 成功，
     *          10001 没登录，
     *          10002 账号密码错误，
     *          10003 验证码错误，
     *          10004 账号不存在，
     *          10099 系统错误
     */
    public static final int RESULT_CODE_FAIL = 99999;
    public static final int RESULT_CODE_SUCCESS = 10000;
    public static final int RESULT_CODE_NOT_LOGIN = 10001;
    public static final int RESULT_CODE_LOGIN_ERROR = 10002;
    public static final int RESULT_CODE_SMSCODE_ERROR = 10003;
    public static final int RESULT_CODE_ACCOUNT_ERROR = 10004;
    public static final int RESULT_CODE_ACCOUNT_DISABLED = 10005;
    public static final int RESULT_CODE_REGISTER_EMPTY = 10006;
    public static final int RESULT_CODE_REGISTER_SAME_ACCOUNT = 10007;
    public static final int RESULT_CODE_SYSTEM_ERROR = 10099;

    /**
     * 登录状态：-1 异常退出，0 正常退出，1 登录态
     */
    public static final int ACCOUNT_ABORT = -1;
    public static final int ACCOUNT_LOGOUT = 0;
    public static final int ACCOUNT_LOGIN = 1;

    /**
     * 最长修改密码时间（单位：秒）
     */
    public static final int MONDIFY_PASSWORD_NEXT_TIME = 30 * 24 * 60 * 60;
}
