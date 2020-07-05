package com.shengshijingu.yashiji.common.util;

import android.media.MediaDataSource;
import android.service.autofill.TextValueSanitizer;

import com.shengshijingu.yashiji.common.controller.ChatController;
import com.shengshijingu.yashiji.common.controller.CityController;
import com.shengshijingu.yashiji.common.controller.CommonController;
import com.shengshijingu.yashiji.common.controller.HomeController;
import com.shengshijingu.yashiji.common.controller.IMController;
import com.shengshijingu.yashiji.common.controller.LoginController;
import com.shengshijingu.yashiji.common.controller.MessageController;

import java.io.PipedReader;
import java.text.ChoiceFormat;

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

    public static CommonController getCommonController() {

        if (commonController == null) {
            commonController = new CommonController();
        }


        return commonController;
    }


    private static MessageController messageController;

    public static MessageController getMessageController() {

        if (messageController == null) {
            messageController = new MessageController();
        }
        return messageController;
    }


    private static IMController imController;

    public static IMController getImController() {
        if (imController == null) {
            imController = new IMController();
        }
        return imController;
    }


    private static ChatController chatController;

    public static ChatController getChatController() {
        if (chatController == null) {
            chatController = new ChatController();
        }

        return chatController;
    }


    private static  CityController cityController;
    public static CityController getCityController(){
        if(cityController == null) {
            cityController = new CityController();
        }

        return  cityController;
    }

}
