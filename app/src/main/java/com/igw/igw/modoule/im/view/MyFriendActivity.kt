package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.FriendBean
import com.igw.igw.modoule.im.MyFriendContract
import com.igw.igw.modoule.im.adapter.MyFriendAdapter
import com.igw.igw.modoule.im.model.MyFriendModel
import com.igw.igw.modoule.im.presenter.MyFriendPresenter
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import kotlinx.android.synthetic.main.activity_my_friend.*
import kotlinx.android.synthetic.main.common_status_bar.*

class MyFriendActivity : BaseActivity<MyFriendPresenter>(),MyFriendContract.View {

    companion object{

        val TAG  = "MyFriendActivity"

        public fun startSelf(activity: Activity){

            var intent = Intent(activity, MyFriendActivity::class.java)
            activity.startActivity(intent)
        }

    }


    private  lateinit var  mAdapter : MyFriendAdapter
    private lateinit var mManager: LinearLayoutManager


    override fun initView() {

        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(resources.getString(R.string.title_my_friends_list))
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        initAdapter()
        setUpData()

        setUpListener()
    }

    private fun initAdapter() {

            mAdapter = MyFriendAdapter(this, false)

         mManager= LinearLayoutManager(this)
        mManager.orientation = LinearLayoutManager.VERTICAL
        mManager.isAutoMeasureEnabled = true
        rv_friends.layoutManager = mManager
        rv_friends.adapter = mAdapter



    }

    private fun setUpData() {


        mPresenter.getFriendsList()

    }

    private fun setUpListener() {



        et_search.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {



            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

        })




        //滑动字母表跳转位置
        sb_letter.setOnTouchingLetterChangedListener {

           val position   = mAdapter.getPositonForSection(it[0])

            if (position != -1){


                mManager.scrollToPositionWithOffset(position,0)
            }

        }


       // 切换状态
        status_bar_main.setOnConfirmClickListener(object :
                StatusBarView.OnConfirmClickListener{
            override fun onClick() {

                LocaleUtils.changeLocale(this@MyFriendActivity)
            }


        })


    }

    override fun initPresenter() {

        mPresenter = MyFriendPresenter(MyFriendModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int  = R.layout.activity_my_friend


    override fun setTitle(): String {

        return ""
    }


    override fun setRightButton(): String  {
        return ""
    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun onSuccessFriends(data: FriendBean.DataBean) {


        val friends = data.friends

        mAdapter.refreshData(friends)


    }

    override fun onFailFriends(code: Int, msg: String) {
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
