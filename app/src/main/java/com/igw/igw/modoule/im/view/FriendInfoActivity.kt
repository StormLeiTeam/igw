package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.data.LocalUriFetcher
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.FriendBean
import com.igw.igw.bean.chat.DelFriendBean
import com.igw.igw.modoule.im.FriendInfoContract
import com.igw.igw.modoule.im.model.FriendInfoModel
import com.igw.igw.modoule.im.presenter.FriendInfoPresenter
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_friend_info.*
import kotlinx.android.synthetic.main.common_status_bar.*


class FriendInfoActivity : BaseActivity<FriendInfoPresenter>(), FriendInfoContract.View {


    companion object {
        val TAG = "FriendInfoActivity"


        val INTENT_TAG = "friend_info"
        fun startSelf(activity: Activity, intentValue: String) {


            var intent = Intent(activity, FriendInfoActivity::class.java)
//            intent.putExtra()
            intent.putExtra(INTENT_TAG, intentValue)

            activity.startActivity(intent)
        }

    }



    var friendInfo: String? = null


    var friendBean: FriendBean.DataBean.FriendsBean? = null
    override fun initView() {

        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle("")
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)

        friendInfo = intent.getStringExtra(INTENT_TAG)
        friendBean = GsonUtils.instance.fromJson<FriendBean.DataBean.FriendsBean>(friendInfo, FriendBean.DataBean.FriendsBean::class.java)

        updateView(friendBean!!)



        setUpListener()


    }

    private fun setUpListener() {


        btn_del_friend.setOnClickListener {


            friendBean?.let {

                mPresenter.delFriend(it.friendUserId)

            }
        }

        btn_send_msg.setOnClickListener {

            friendBean?.let {

                SingleChatActivity.startSelfOfIntent(
                        this, it.friendUserId.toString(), it.friendNickName, Conversation.ConversationType.PRIVATE)
            }
        }


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {


                friendInfo?.let {
                    LocaleUtils.changeLocale(this@FriendInfoActivity,
                            INTENT_TAG, it)

                }
            }
        })


    }

    private fun updateView(friendBean: FriendBean.DataBean.FriendsBean) {


        GlideUtils.loadImage(this,
                friendBean.friendHeadImage, iv_head_image)
        tv_person_name.text = friendBean.friendNickName


        if (friendBean.friendSex == 1) {
            iv_sex.setBackgroundResource(R.drawable.man)

        } else {
            iv_sex.setBackgroundResource(R.drawable.man)

        }


//        iv_sex.setBackgroundResource(R.drawable.man)

        tv_individual_resume.text = friendBean.friendUserDesc

        if (LocaleUtils.isLocaleEn(this)) {

            tv_nationality.text = friendBean.friendCountryEnName

            tv_city.text = friendBean.friendCityEnName

        } else {

            tv_nationality.text = friendBean.friendCountryCnName

            tv_city.text = friendBean.friendCityCnName
        }



        btn_del_friend.visibility = View.VISIBLE
        btn_send_msg.visibility = View.VISIBLE


    }

    override fun initPresenter() {

        mPresenter = FriendInfoPresenter(FriendInfoModel())
        mPresenter.attachView(this)


    }

    override fun getLayoutId(): Int = R.layout.activity_friend_info

    override fun setTitle(): String {

        return ""

    }

    override fun setRightButton(): String {
        return ""
    }

    override fun setStatusBarColor(): Boolean {

        return false
//        TODO("Not yet implemented")
    }

    override fun delFriendSuccess(data: DelFriendBean.DataBean) {

        ToastUtil.showCenterToast(this, "删除成功")
        finish()
    }

    override fun delFriendFail(code: Int, msg: String) {

        ToastUtil.showCenterToast(this, msg)

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }
}