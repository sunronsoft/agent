package com.lottery.agent.controllers;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Clear;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.lottery.agent.BaseController;
import com.lottery.game.GameAction;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author sunron
 */

@Clear
public class IndexController extends BaseController {

    public void index() {
        logger.debug("start index page.");

        try {
            PropKit.appendIfExists("gamePlugins.properties");
            int gameNumber = PropKit.getInt("gameNumber", 0);
            if (gameNumber > 0) {
                String defaultPath = PropKit.get("defaultPath");
                for (int i = 1; i <= gameNumber; i++) {
                    String gamePath = PropKit.get("plugPath" + String.valueOf(i));
                    boolean useDefault = StrKit.notBlank(defaultPath) && StrKit.notBlank(gamePath) && !gamePath.startsWith("/");
                    if (useDefault) {
                        gamePath = defaultPath + gamePath;
                    }
                    URL url = new URL("file://" + gamePath);
                    URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
                    Class<?> myClass = urlClassLoader.loadClass("com.lottery.game.service.TestAction");
                    GameAction action = (GameAction) myClass.newInstance();
                    JSONObject object = action.register("userId", "password");
                    System.out.println(object.toJSONString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        renderText("This is test");
    }
}
