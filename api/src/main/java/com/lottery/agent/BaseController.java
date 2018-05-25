package com.lottery.agent;

import com.jfinal.core.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseController extends Controller {

    protected static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    protected String getToken() {
        return getHeader("token");
    }
}
