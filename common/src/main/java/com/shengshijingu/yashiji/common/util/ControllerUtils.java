package com.shengshijingu.yashiji.common.util;

import android.service.autofill.TextValueSanitizer;

import com.shengshijingu.yashiji.common.controller.CommonController;
import com.shengshijingu.yashiji.common.controller.HomeController;
import com.shengshijingu.yashiji.common.controller.LoginController;

import java.io.PipedReader;

/**
 * Created by leilei on 2019/3/14.
 */

public class ControllerUtils {

    private static HomeController homeController;

    public static HomeController getHomeControllerInstance() {
        if (homeController == null) {
            homeController = new HomeController();
        }
        return homeController;
    }


    private static LoginController loginController;

    public static LoginController getLoginControllerInstance() {

        if (loginController == null) {
            loginController = new LoginController();
        }
        return loginController;
    }


    private static CommonController commonController;

    public static CommonController getCommonController(){

        if(commonController == null) {
            commonController = new CommonController();
        }


        return commonController;
    }

}
