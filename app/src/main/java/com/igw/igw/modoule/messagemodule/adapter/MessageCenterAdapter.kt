package com.igw.igw.modoule.messagemodule.adapter

import android.content.Context
import android.media.Image
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.message.MessageCenterBean
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.RvViewHolder
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.internal.operators.flowable.FlowableOnErrorReturn

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class MessageCenterAdapter(context: Context, isLoadMore: Boolean) : BaseAdapter<MessageCenterBean.DataBean.RowsBean>(context, isLoadMore) {


    companion object {

        val TAG = "MessageCenterAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {

        if (isCommonViewType(viewType)) {
            return RvViewHolder.create(mContext, getItemLayoutId(viewType), parent)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getItemLayoutId(viewType: Int): Int {


        when (viewType) {

            1 -> return R.layout.item_message_invite

            else -> return R.layout.item_message_notifation
        }


    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {

        val itemViewType = holder.itemViewType

        when (itemViewType) {

            1 -> {

                var name = holder.getView<TextView>(R.id.tv_name)
                var content = holder.getView<TextView>(R.id.tv_content)
                var headImg = holder.getView<ImageView>(R.id.iv_headview)

                var agree = holder.getView<Button>(R.id.btn_agree)
                var refuse = holder.getView<Button>(R.id.btn_refuse)


                name.text = mDatas?.get(position)?.messageName
                content.text = mDatas?.get(position)?.messageContent


                var imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587645470981&di=f49b3559b8f5eb08d80b104ef30a2a3d&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd009b3de9c82d1587e249850820a19d8bd3e42a9.jpg"
                GlideUtils.loadImage(mContext, imagePath, headImg)


                holder.itemView.setOnClickListener {
                    mDatas?.let {

                        mOnItemClickListener?.onItemClick(it[position], position)

                    }
                }


                agree.setOnClickListener {
                    mDatas?.let {

                        mOnAgreeListener?.onAgree(it[position], position)

                    }
                }


                refuse.setOnClickListener {
                    mDatas?.let {
                        mOnRefuseListener?.onRefuse(it[position], position)
                    }
                }

            }

            2 -> {


                var title = holder.getView<TextView>(R.id.tv_title)
                var headerView = holder.getView<ImageView>(R.id.iv_head_view)

                var isreadView = holder.getView<ImageView>(R.id.iv_point)

                var timeView = holder.getView<TextView>(R.id.tv_time)




                title.text = mDatas?.get(position)?.messageName
                timeView.text = mDatas?.get(position)?.ctime


                var imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587645470981&di=f49b3559b8f5eb08d80b104ef30a2a3d&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd009b3de9c82d1587e249850820a19d8bd3e42a9.jpg"
                GlideUtils.loadImage(mContext, imagePath, headerView)


                val isread = mDatas?.get(position)?.isRead

                isreadView.visibility = if (isread == 0) View.VISIBLE else View.GONE


                holder.itemView().setOnClickListener {
                    mDatas?.let {
                        mOnItemClickListener?.onItemClick(it[position], position)

                    }
                }
            }
        }


    }


    override fun getViewType(position: Int, t: MessageCenterBean.DataBean.RowsBean?): Int {

        return mDatas?.get(position)!!.messageType

    }


    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnAgreeListener: OnAgreeListener? = null
    private var mOnRefuseListener: OnRefuseListener? = null

    public fun addOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    public fun addOnAgreeListener(listener: OnAgreeListener) {
        this.mOnAgreeListener = listener

    }

    public fun addOnRefuseListener(listener: OnRefuseListener) {
        this.mOnRefuseListener = listener


    }


    public interface OnItemClickListener {

        fun onItemClick(bean: MessageCenterBean.DataBean.RowsBean, positon: Int)
    }


    public interface OnAgreeListener {

        fun onAgree(bean: MessageCenterBean.DataBean.RowsBean, position: Int)
    }

    public interface OnRefuseListener {
        fun onRefuse(bean: MessageCenterBean.DataBean.RowsBean, position: Int)

    }


}