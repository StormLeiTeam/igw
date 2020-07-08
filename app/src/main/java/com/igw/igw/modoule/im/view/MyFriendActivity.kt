package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.FriendBean
import com.igw.igw.modoule.im.MyFriendContract
import com.igw.igw.modoule.im.adapter.MyFriendAdapter
import com.igw.igw.modoule.im.adapter.MyFriendSearchAdapter
import com.igw.igw.modoule.im.adapter.OnItemClickListener
import com.igw.igw.modoule.im.model.MyFriendModel
import com.igw.igw.modoule.im.presenter.MyFriendPresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.igw.igw.widget.storm.searchpop.SearchFriendPopWindow
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_friend.*
import kotlinx.android.synthetic.main.common_status_bar.*

class MyFriendActivity : BaseActivity<MyFriendPresenter>(), MyFriendContract.View {

    companion object {

        val TAG = "MyFriendActivity"

        public fun startSelf(activity: Activity) {

            var intent = Intent(activity, MyFriendActivity::class.java)
            activity.startActivity(intent)
        }

    }


    private lateinit var mAdapter: MyFriendAdapter
    private lateinit var mManager: LinearLayoutManager

    private lateinit var mSearchAdapter: MyFriendSearchAdapter

    private lateinit var searchFriendPopWindow: SearchFriendPopWindow

    override fun initView() {

        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(resources.getString(R.string.title_my_friends_list))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        searchFriendPopWindow = SearchFriendPopWindow(this)


        initAdapter()
        setUpData()

        setUpListener()
    }

    private fun initAdapter() {

        mAdapter = MyFriendAdapter(this, false)

        mManager = LinearLayoutManager(this)
        mManager.orientation = LinearLayoutManager.VERTICAL
        mManager.isAutoMeasureEnabled = true
        rv_friends.layoutManager = mManager
        rv_friends.adapter = mAdapter


    }

    private fun setUpData() {


        mPresenter.getFriendsList()
        showLoadingText()

    }

    private fun setUpListener() {


        ll_outside.setOnClickListener{
            et_search.text?.clear()
            et_search.clearFocus()

        }

        status_bar_main.setOnBackClickListener(object : StatusBarView.OnBackClickListener {
            override fun onClick() {

                if (ll_search_list.visibility == View.VISIBLE) {
                    ll_search_list.visibility = View.GONE

                    return
                }

                finish()
            }


        })

        search_outside.setOnClickListener {

            if (ll_search_list.visibility == View.VISIBLE) {
                ll_search_list.visibility = View.GONE
                et_search.text?.clear()
                et_search.clearFocus()
                friend_line.requestFocus()

            }
        }


        mAdapter.onItemClickListener(object : MyFriendAdapter.OnItemClickListener {
            override fun onItemClick(item: FriendBean.DataBean.FriendsBean, position: Int) {


                singleChat(item)


            }


        })





        ll_near_chat.setOnClickListener {

            // 最近聊天
            LogUtils.d(TAG, "跳转  -- ")



            ChatTypeActivity.startSelf(this)

        }





        et_search.setOnFocusChangeListener { v, hasFocus ->


            LogUtils.d(TAG, "获取焦点 , $hasFocus")

            ll_search_list.visibility = if (hasFocus) View.VISIBLE else View.GONE


        }


        //滑动字母表跳转位置
        sb_letter.setOnTouchingLetterChangedListener {

            val position = mAdapter.getPositonForSection(it[0])

            if (position != -1) {


                mManager.scrollToPositionWithOffset(position, 0)
            }

        }


        // 切换状态
        status_bar_main.setOnConfirmClickListener(object :
                StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                LocaleUtils.changeLocale(this@MyFriendActivity)
            }


        })


    }

    private fun singleChat(item: FriendBean.DataBean.FriendsBean) {


        // 进入到单聊模式

        LogUtils.d(TAG, "选择单聊  --${item.id} ")

        SingleChatActivity.startSelfOfIntent(this, item.friendUserId.toString(), item.friendNickName, Conversation.ConversationType.PRIVATE)

    }

    override fun initPresenter() {

        mPresenter = MyFriendPresenter(MyFriendModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_my_friend


    override fun setTitle(): String {

        return ""
    }


    override fun setRightButton(): String {
        return ""
    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun onSuccessFriends(data: FriendBean.DataBean) {


        val friends = data.friends

        mAdapter.refreshData(friends)

        hideLoadingText()

        initSearchAdapter(data)


    }

    private fun initSearchAdapter(data: FriendBean.DataBean) {


        mSearchAdapter = MyFriendSearchAdapter(data.friends)
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL


        rv_search.layoutManager = manager
        rv_search.adapter = mSearchAdapter


//        searchFriendPopWindow.setAdapter(data)


        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                ll_search_list.visibility = View.VISIBLE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                LogUtils.d(TAG, "获取值 ----------------")

                s?.let {
//                    searchFriendPopWindow.filter(s)
                    mSearchAdapter.filter.filter(s)
//                    searchFriendPopWindow.showPopupWindow(et_search)


                }


            }

        })

        mSearchAdapter.onItemClickListener(object : OnItemClickListener<FriendBean.DataBean.FriendsBean> {
            override fun onItemClick(item: FriendBean.DataBean.FriendsBean, position: Int) {


                ll_search_list.visibility = View.GONE
                singleChat(item)
            }


        })


    }

    override fun onFailFriends(code: Int, msg: String) {

        ToastUtil.showCenterToast(this, msg)

    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_my_friend)
//    }
}
