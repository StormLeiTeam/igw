package com.igw.igw.modoule.abouthelp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.help.HelpBean
import com.igw.igw.utils.RvViewHolder
import java.net.ConnectException

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class HelpAdapter(context: Context,isOpenLoadMore: Boolean) :BaseAdapter<HelpBean.DataBean.RowsBean>(context,isOpenLoadMore){


    companion object{

        val TAG  = "HelpAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {

        if (isCommonViewType(viewType)){

            return RvViewHolder.create(mContext,getItemLayoutId(),parent)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getItemLayoutId(): Int {


        return R.layout.item_help
    }


    override fun getViewType(position: Int, t: HelpBean.DataBean.RowsBean?): Int {

        return 0
    }

    override fun onBindViewHolder(rvViewHolder: RvViewHolder, position: Int) {

        if (isCommonViewType(rvViewHolder.itemViewType)){

            bindData(rvViewHolder,position)
        }
    }




    private fun bindData(rvViewHolder: RvViewHolder, position: Int) {


        val content
                = rvViewHolder.getView<TextView>(R.id.tv_content)

        var  main = rvViewHolder.getView<RelativeLayout>(R.id.rv_main)

        mDatas?.let {
            content.text = "${it.get(position).helpTitle}"

        }


        main.setOnClickListener {

            mListener?.onItemClick(mDatas?.get(position), position)
        }


    }



    private var mListener: OnItemClickListener? = null

    public fun  onItemClickListener(onItemClickListener: OnItemClickListener){

        this.mListener = onItemClickListener
    }

    public interface  OnItemClickListener{

      fun  onItemClick(data:HelpBean.DataBean.RowsBean?,position:Int)
    }




}