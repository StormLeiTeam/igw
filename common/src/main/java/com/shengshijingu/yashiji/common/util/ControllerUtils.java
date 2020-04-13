package com.shengshijingu.yashiji.common.util;

import com.shengshijingu.yashiji.common.controller.HomeController;

/**
 * Created by leilei on 2019/3/14.
 */

public class ControllerUtils {

    private static HomeController homeController;

    public static  HomeController getHomeControllerInstance(){
        if(homeController==null){
            homeController=new HomeController();
        }
        return homeController;
    }


}
