package com.igw.igw.modoule.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.FriendBean
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.RvViewHolder
import com.igw.igw.widget.storm.StormCircleImageView

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class MyFriendAdapter(context: Context,isOpenLoadMore: Boolean ): BaseAdapter<FriendBean.DataBean.FriendsBean>(context,isOpenLoadMore){


    companion object{

        val TAG  = "MyFriendAdapter"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {

        if (isCommonViewType(viewType)){

            return RvViewHolder.create(mContext,getItemLayoutId(),parent)
        }

        return super.onCreateViewHolder(parent, viewType)

    }

    private fun getItemLayoutId(): Int {

        return R.layout.item_friend

    }


    override fun getViewType(position: Int, t: FriendBean.DataBean.FriendsBean?): Int {

        return 0

    }

    override fun onBindViewHolder(rvViewHolder: RvViewHolder, position: Int) {

        if (isCommonViewType(rvViewHolder.itemViewType)){

            bindData(rvViewHolder,position)
        }
    }

    private fun bindData(rvViewHolder: RvViewHolder, position: Int) {


        var  nickName = rvViewHolder.getView<TextView>(R.id.tv_friend_nick_name)

        var address = rvViewHolder.getView<TextView>(R.id.tv_address)

        var headImg = rvViewHolder.getView<StormCircleImageView>(R.id.iv_friend_head_view)

        var firstLetter = rvViewHolder.getView<TextView>(R.id.tv_first_letter)


        mDatas?.let {


            if (position != 0 && getFirstName(it[position].firstCharPinyin)
                    == getFirstName(it[position -1].firstCharPinyin) ){


                firstLetter.visibility = View.GONE
            }else{

                firstLetter.visibility = View.VISIBLE
            }

            firstLetter.text = getFirstName(it[position].firstCharPinyin).toString()


            // 数据

            nickName.text = it[position].friendNickName


            if (LocaleUtils.isLocaleEn(mContext)){

                address.text = it[position].friendCityEnName


            }else{

                address.text  = it[position].friendCityCnName
            }


            GlideUtils.loadImage(mContext,it[position].friendHeadImage,headImg)


            setUpListener(rvViewHolder, position)

        }






    }

    private fun setUpListener(holder: RvViewHolder, position: Int) {


        holder.itemView().setOnClickListener {

            mListener?.let {

                it.onItemClick(mDatas!![position],position)
            }
        }




    }


    fun getPositonForSection(s: Char): Int {
        for (i in 0 until itemCount - 1) {
            val c: Char = mDatas!![i].firstCharPinyin[0]
            if (s == c) {
                return i
            }
        }
        return -1
    }

    private fun getFirstName(firstName: String): Char {
        return firstName[0]
    }




    private var mListener: OnItemClickListener? = null

    public  fun onItemClickListener(listener:OnItemClickListener){
        this.mListener = listener
    }
    public interface OnItemClickListener{

        fun onItemClick(item:FriendBean.DataBean.FriendsBean,position: Int)
    }

}