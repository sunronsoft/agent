package com.lottery.agent.common;

import com.jfinal.config.*;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.lottery.agent.controllers.*;
import com.lottery.agent.dao._MappingKit;

public class AppConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        PropKit.use("config.properties");
        me.setEncoding("utf-8");
        me.setDevMode(PropKit.getBoolean("devMode", false));
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/user", UserController.class);
        me.add("/agent", AgentController.class);
        me.add("/basic", BasicController.class);
        me.add("/game", GameController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        String jdbcUrl = PropKit.get("jdbcUrl").trim();
        String jdbcUser = PropKit.get("user").trim();
        String jdbcPassword = PropKit.get("password").trim();

        // 配置Druid数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPassword);
        me.add(druidPlugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new POST());
    }

    @Override
    public void configHandler(Handlers me) {
    }
}
