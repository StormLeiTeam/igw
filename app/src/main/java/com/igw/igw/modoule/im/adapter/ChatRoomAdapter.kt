package com.igw.igw.modoule.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.GlideUtils.loadImage
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.RvViewHolder
import com.igw.igw.widget.storm.StormCircleImageView
import com.shengshijingu.yashiji.common.Constants

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 聊天室成员
 */
class ChatRoomAdapter(context: Context, isOpenLoadMore: Boolean)
    : BaseAdapter<ChatRoomUsesBean.DataBean.RoomUsersBean>(context, isOpenLoadMore) {


    companion object {

        val TAG = "ChatRoomAdapter"
    }

    var userType: Int = 2;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {


        if (isCommonViewType(viewType)) {

            return RvViewHolder.create(mContext, getItemLayoutId(), parent)

        }



        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getItemLayoutId(): Int {

        if (userType == 1) { // 当前用户不是管理员
            return R.layout.item_chat_room_users
        } else { // 当前用户是管理员
            return R.layout.item_chat_room_administrator
        }
    }


    override fun getViewType(position: Int, t: ChatRoomUsesBean.DataBean.RoomUsersBean?): Int {


        return 0

    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {

        if (isCommonViewType(holder.itemViewType)) {
            bindData(holder, position)
        }
    }

    private fun bindData(holder: RvViewHolder, position: Int) {


        when (userType) {


            1 -> {

                // 非管理员
                var headImage = holder.getView<StormCircleImageView>(R.id.iv_head_img)
                var tv_administrator = holder.getView<TextView>(R.id.tv_administrator)
                var tv_name = holder.getView<TextView>(R.id.tv_name)
                var add_friend = holder.getView<Button>(R.id.btn_add_friend)


                loadImage(mContext, Constants.BASE_URL + mDatas!![position].headImage, headImage)

                tv_name.text = mDatas!![position].nickName

                if (mDatas!![position].isFriend == 1) {

                    add_friend.visibility = View.GONE

                } else {
                    add_friend.visibility = View.VISIBLE

                }


                if (mDatas!![position].isAdmin == 1) {

                    tv_administrator.visibility = View.VISIBLE

                } else {

                    tv_administrator.visibility = View.GONE

                }



                add_friend.setOnClickListener {
                    mListener?.onAddFriend(mDatas!![position], position)

                }

                holder.itemView.setOnClickListener {
                    mListener?.onItemClick(mDatas!![position], position)

                }


            }


            else -> {

                // 当前用户不是管理员

                var headImage = holder.getView<StormCircleImageView>(R.id.iv_head_img)
                var tv_name = holder.getView<TextView>(R.id.tv_name)
                var tv_banned = holder.getView<Button>(R.id.btn_banned)
                var tv_unBanned = holder.getView<Button>(R.id.btn_unbanned)
                var add_friend = holder.getView<Button>(R.id.btn_add_friend)



                loadImage(mContext, Constants.BASE_URL + mDatas!![position].headImage, headImage)
                tv_name.text = mDatas!![position].nickName


                if (mDatas!![position].isFriend == 1) {

                    add_friend.visibility = View.GONE

                } else {
                    add_friend.visibility = View.VISIBLE

                }


                if (mDatas!![position].isBlock == 0) {
                    // 未被禁言

                    tv_banned.visibility = View.VISIBLE
                    tv_unBanned.visibility = View.GONE
                } else {
                    tv_banned.visibility = View.GONE
                    tv_unBanned.visibility = View.VISIBLE
                }


//
//                if (LocaleUtils.isLocaleEn(mContext)) {
//
//                    // en
//
//                } else {
//
//                    //cn
//
//
//
//                }

                tv_banned.setOnClickListener {

                    mListener?.onBanned(mDatas!![position], position)

                }

                tv_unBanned.setOnClickListener {
                    mListener?.onUnbanned(mDatas!![position], position)

                }

                add_friend.setOnClickListener {

                    mListener?.onAddFriend(mDatas!![position], position)

                }

                holder.itemView.setOnClickListener {

                    mListener?.onItemClick(mDatas!![position], position)

                }


            }


        }


    }


    public interface OnItemClickListener {

        fun onItemClick(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int)

        fun onAddFriend(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int)
        fun onBanned(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int)

        fun onUnbanned(bean: ChatRoomUsesBean.DataBean.RoomUsersBean, position: Int)

    }


    private var mListener: OnItemClickListener? = null

    public fun onItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener

    }

}