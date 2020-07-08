package com.igw.igw.widget.storm.searchpop

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.media.tv.TvContentRating
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.igw.igw.R
import com.igw.igw.bean.FriendBean
import com.igw.igw.modoule.im.adapter.MyFriendSearchAdapter

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 搜索结果
 */
public class SearchFriendPopWindow : PopupWindow {


    var rootView: View? = null

    var mAdapter: MyFriendSearchAdapter? = null

    lateinit var rv_search_friend: RecyclerView

    lateinit var mContext: Activity


    constructor(context: Activity) {
        this.mContext = context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        rootView = inflater.inflate(R.layout.search_friend_popwindow, null)
        this.contentView = rootView
        this.width = LinearLayout.LayoutParams.MATCH_PARENT
        this.height = LinearLayout.LayoutParams.WRAP_CONTENT
        this.isFocusable = true
        this.isOutsideTouchable = true

        this.update()

        val dw = ColorDrawable(0)

        setUpView()

        setUpListener()
    }

    private fun setUpView() {

        rv_search_friend = rootView!!.findViewById(R.id.rv_search_friend)


    }

    private fun setUpListener() {


    }


    public fun setAdapter(data: FriendBean.DataBean) {

        mAdapter = MyFriendSearchAdapter(data.friends)
        val manager = LinearLayoutManager(mContext)

        manager.orientation = LinearLayoutManager.VERTICAL

        rv_search_friend.layoutManager = manager
        rv_search_friend.adapter = mAdapter


    }


    private fun getAdapter(): MyFriendSearchAdapter? {

        return mAdapter
    }

    public fun showPopupWindow(parent: View) {

        if (!this.isShowing) {
            this.showAsDropDown(parent, 0, 0)

        } else {

            this.dismiss()
        }
    }

    public  fun filter(s: CharSequence) {
        mAdapter!!.filter.filter(s)

    }


}