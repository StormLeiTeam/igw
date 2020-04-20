package com.shengshijingu.yashiji.common.controller;


import com.shengshijingu.yashiji.common.net.NetApi;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 登陆相关
 */
public class LoginController extends Controller {


    public static final String TAG = "LoginController";


//    /**
//     * 城市列表
//     */
//    public void cityList(Observer observer) {
//        Map<String, Object> params = getBaseParams();
//        ApiSubscribe(NetApi.getApiService().cityList(getRequestBody(params)), observer);
//    }

    /**
     * 获取国家信息
     */
    public void getNationality(Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("", "");


        ApiSubscribe(NetApi.getApiService().getNationality(getRequestBody(params)), observer);

    }


    /**
     * 根据 国籍id 获取城市列表
     *
     * @param countryId 国籍id
     * @param observer
     */
    public void getCityListData(int countryId, Observer observer) {

        Map<String, Object> params = new HashMap<>();

        params.put("countryId", countryId);

        ApiSubscribe(NetApi.getApiService().getCityListData(getRequestBody(params)), observer);
    }


    public void registerUser(int countryId, int cityId, String lastName, String firstName,
                             int sex, String birthday, String nickname, String agencyName, String description,
                             String email, String mobilePhone, String password, String inviteCode, String headImage,
                             Observer observer) {


        Map<String,Object> params = new HashMap<>();
        params.put("countryId",countryId);
        params.put("cityId",cityId);
        params.put("lastName",lastName);
        params.put("firstName",firstName);
        params.put("sex",sex);
        params.put("birthday",birthday);
        params.put("nickname",nickname);
        params.put("agencyName",agencyName);
        params.put("description",description);
        params.put("email",email);
        params.put("mobilePhone",mobilePhone);
        params.put("password",password);
        params.put("inviteCode",inviteCode);
        params.put("headImage",headImage);

        ApiSubscribe(NetApi.getApiService().registerUser(getRequestBody(params)),observer);



    }

    /**
     * 帐号登陆
     */
    public void loginByAccent(String mobilePhone, String password,Observer observer){

        Map<String, Object> params = new HashMap<>();
        params.put("mobilePhone",mobilePhone);
        params.put("password",password);

        ApiSubscribe(NetApi.getApiService().loginByAccent(getRequestBody(params)),observer);

    }


    /**
     *
     * 使用邮箱登陆
     * @param email
     * @param verifyCode
     * @param observer
     */
    public void loginByEmail(String email,String verifyCode, Observer observer){

        Map<String, Object> params = new HashMap<>();
        params.put("email",email);
        params.put("verifyCode",verifyCode);

        ApiSubscribe(NetApi.getApiService().loginByEmail(getRequestBody(params)),observer);

    }

    /**
     * 发送验证码
     * @param email
     * @param type
     * @param observer
     */
    public void sendEmailVerifyCode(String email,int type,Observer observer){


        Map<String,Object> params = new HashMap<>();
        params.put("email",email);
        params.put("type",type);

        ApiSubscribe(NetApi.getApiService().sendEmailVerifyCode(getRequestBody(params)),observer);
    }


    /**
     * 重置密码
     * @param email
     * @param verifyCode
     * @param password
     * @param observer
     */
    public void resetPassword(String email,String verifyCode, String password,Observer observer){


        Map<String, Object> params = new HashMap<>();
        params.put("email",email);
        params.put("verifyCode",verifyCode);
        params.put("password",password);

        ApiSubscribe(NetApi.getApiService().resetPassword(getRequestBody(params)), observer);

    }


    /**
     *  修改密码
     * @param originPassword
     * @param password
     * @param observer
     */
    public void updatePassword(String originPassword, String password, Observer observer){

        Map<String,Object> params = new HashMap<>();
        params.put("originPassword",originPassword);
        params.put("password",password);

        ApiSubscribe(NetApi.getApiService().updatePassword(getRequestBody(params)), observer);
    }


}
