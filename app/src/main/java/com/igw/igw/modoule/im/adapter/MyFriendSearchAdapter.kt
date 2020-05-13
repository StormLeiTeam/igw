package com.igw.igw.modoule.im.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.bean.FriendBean
import com.igw.igw.fragment.my.MyContract
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.RvViewHolder
import com.igw.igw.widget.storm.StormCircleImageView
import com.tuacy.fuzzysearchlibrary.FuzzySearchBaseAdapter
import com.tuacy.fuzzysearchlibrary.IFuzzySearchRule
import io.rong.imkit.widget.adapter.MessageListAdapter

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class MyFriendSearchAdapter : FuzzySearchBaseAdapter<FriendBean.DataBean.FriendsBean,RvViewHolder>  {


    constructor(rule: IFuzzySearchRule?) : super(rule)

    constructor(dataList: MutableList<FriendBean.DataBean.FriendsBean>?):super(null,dataList)
    constructor(rule: IFuzzySearchRule?, dataList: MutableList<FriendBean.DataBean.FriendsBean>?) : super(rule, dataList)


    companion object{
        val TAG = "MyFriendSearchAdapter"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {

        return RvViewHolder.create(parent.context,getItemLayoutId(),parent)

    }

    private fun getItemLayoutId(): Int {

        return  R.layout.item_friend
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {


        var firstLetter = holder.getView<TextView>(R.id.tv_first_letter)
        firstLetter.visibility = View.GONE



        var  nickName = holder.getView<TextView>(R.id.tv_friend_nick_name)

        var address = holder.getView<TextView>(R.id.tv_address)

        var headImg = holder.getView<StormCircleImageView>(R.id.iv_friend_head_view)


        mDataList.let {


            nickName.text = mDataList[position].friendNickName


            LocaleUtils.isLocaleEn(holder.itemView.context)

            if (LocaleUtils.isLocaleEn(holder.itemView.context)){
                address.text = it[position].friendCityEnName


            }else{
                address.text = it[position].friendCityCnName

            }

            GlideUtils.loadImage(holder.itemView.context, it[position].friendHeadImage, headImg)


            mListener?.let {

                it.onItemClick(mDataList[position], position)

            }

        }


    }


    private var mListener: OnItemClickListener<FriendBean.DataBean.FriendsBean>? = null


    public fun onItemClickListener(listener:OnItemClickListener<FriendBean.DataBean.FriendsBean>){

        this.mListener = listener
    }


//    public interface OnItemClickListener{
//
//        fun onItemClick(bean:FriendBean.DataBean.FriendsBean,position:Int)
//    }

}