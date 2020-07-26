package com.shengshijingu.yashiji.common.controller;

import com.shengshijingu.yashiji.common.net.NetApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe IM 即时通讯
 */
public class IMController extends Controller {

    public static final String TAG = "IMController";


    /**
     * 获取我的好友
     */
    public void getFriends(Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("", "");

        ApiSubscribe(NetApi.getApiService().getFriends(getRequestBody(params)), observer);

    }


    /**
     * 创建聊天室
     *
     * @param chatroomId
     * @param chatroomName
     * @param observer
     */
    public void createChatRoom(String chatroomId, String chatroomName, Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("chatroomId", chatroomId);
        params.put("chatroomName", chatroomName);

        ApiSubscribe(NetApi.getApiService().createChatRoom(getRequestBody(params)), observer);

    }


    public void destoryChatroom(String chatroomId, Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("chatroomId", chatroomId);

        ApiSubscribe(NetApi.getApiService().destoryChatRoom(getRequestBody(params)), observer);


    }


    /**
     * 聊天室成员
     *
     * @param chatroomId
     * @param observer
     */
    public void chatroomUsers(String chatroomId, String nickName, Observer observer) {


        Map<String, Object> params = new HashMap<>();
        params.put("chatroomId", chatroomId);
        params.put("nickName", nickName);

        ApiSubscribe(NetApi.getApiService().chatRoomUsers(getRequestBody(params)), observer);

    }


    /**
     * 好友备注
     *
     * @param noteName
     * @param userId
     * @param observer
     */
    public void noteUser(String noteName, int userId, Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("noteName", noteName);
        params.put("userId", userId);

        ApiSubscribe(NetApi.getApiService().noteUser(getRequestBody(params)), observer);

    }


    /**
     * 删除好友
     *
     * @param friendUserId
     * @param observer
     */
    public void delFriend(int friendUserId, Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("friendUserId", friendUserId);

        ApiSubscribe(NetApi.getApiService().delFriend(getRequestBody(params)), observer);
    }


    /**
     * 添加好友
     *
     * @param friendUserId
     * @param observer
     */
    public void addFriend(int friendUserId, Observer observer) {

        Map<String, Object> params = new HashMap<>();
        params.put("friendUserId", friendUserId);

        ApiSubscribe(NetApi.getApiService().addFriend(getRequestBody(params)), observer);
    }


    /**
     * 禁言
     * @param chatRoomId
     * @param bannedUserId
     * @param observer
     */
    public void banned(String chatRoomId, String bannedUserId,int blockType, Observer observer) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatroomId", chatRoomId);
        params.put("userId", bannedUserId);
        params.put("blockType", blockType);

        ApiSubscribe(NetApi.getApiService().banned(getRequestBody(params)), observer);


    }
}
