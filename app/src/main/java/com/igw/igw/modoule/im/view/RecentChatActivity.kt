package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.BaseBundle
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.igw.igw.R
import com.igw.igw.bean.FriendBean
import com.igw.igw.bean.login.UserInfoBean
import com.igw.igw.modoule.im.RecentChatContract
import com.igw.igw.modoule.im.model.RecentChatModel
import com.igw.igw.modoule.im.presenter.RecentChatPresenter
import com.igw.igw.modoule.login.loginstate.LoginManager
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.UserInfo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.city_fragment.*
import kotlinx.android.synthetic.main.common_status_bar.*


/**
 * 最近聊天
 */
class RecentChatActivity<P : RecentChatPresenter> : FragmentActivity() , RecentChatContract.View{

    companion object{

        val TAG  = "RecentChatActivity"


        public fun startSelf(activity: Activity){

            var intent  = Intent(activity,RecentChatActivity::class.java)

            activity.startActivity(intent)
        }
    }

    protected  var mPresenter : RecentChatPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_chat)


        StatusBarUtils.transparentBarOrFitSystemWindow(this)
        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle(resources.getString(R.string.title_recent_chat))
        status_bar_main.setTitleTextColor(R.color.black_000000)
        status_bar_main.setTitleTextSize(16f)

        initPresenter()
        initChat()

        setUpFragment()


        setUpListener()
    }

    private fun initPresenter() {

        mPresenter = RecentChatPresenter(RecentChatModel())
        mPresenter!!.attachView(this)
    }

    private fun setUpListener() {


        status_bar_main.setOnBackClickListener(object : StatusBarView.OnBackClickListener{
            override fun onClick() {


                finish()
            }


        })
    }

    private fun initChat() {

        mPresenter!!.getFriendsList()
        mPresenter!!.userInfo()
//
//        Map<String, Boolean> supportedConversation = new HashMap<>();
//       supportedConversation.put(RongPushClient.ConversationType.PRIVATE.getValue(), false)
//        RongIM.getInstance().startConversationList(Context , supportedConversation)
//
//
//        var supportedConversation : HashMap<String,Boolean> = HashMap<String , Boolean>()
//        supportedConversation[Conversation.ConversationType.PRIVATE.name] = false
//
//        RongIM.getInstance().startConversationList(this, supportedConversation)


//
    }

    private fun setUpFragment() {
        val uri: Uri = Uri.parse("rong://" +
                applicationInfo.packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false") //群组
//                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false") //公共服务号
//                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false") //订阅号
//                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true") //系统
                .build()

        val chatFragment  =  ConversationListFragment()
//
        chatFragment.uri = uri;

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.replace(R.id.container,chatFragment)
        transaction.commit()

    }

    override fun onSuccessFriends(data: FriendBean.DataBean) {


//        for (friend in data.friends) {
//
//            var userInfo =  UserInfo("${friend.friendUserId}",friend.friendNickName, Uri.parse("${friend.friendHeadImage}"))
//            RongIM.getInstance().refreshUserInfoCache(userInfo);
//        }

    }

    override fun onFailFriends(code: Int, msg: String) {

    }

    override fun userInfoSuccessful(data: UserInfoBean.DataBean) {
//        LoginManager.instance.updateRongUserInfo("${data.id}", data.nickName, data.headImage)

//        LogUtils.d(TAG, "获取个人位置信息 00 ")
//        var userInfo =  UserInfo("${data.id}",data.nickName, Uri.parse("${Constants.BASE_URL + data.headImage}"))
//        RongIM.getInstance().refreshUserInfoCache(userInfo);
    }

    override fun userInfoFail(code: Int, msg: String) {

        ToastUtil.showCenterToast(this, "msg")

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }
}
