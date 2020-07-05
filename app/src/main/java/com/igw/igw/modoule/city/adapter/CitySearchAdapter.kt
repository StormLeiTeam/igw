package com.igw.igw.modoule.city.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.city.CityResultBean
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.RvViewHolder
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.net.NetApi

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class CitySearchAdapter(context: Context, isOpenLoadMore: Boolean) :
        BaseAdapter<CityResultBean.DataBean.RowsBean>(context, isOpenLoadMore) {


    companion object {

        val TAG = "CitySearchAdapter"
    }


    override fun getViewType(position: Int, t: CityResultBean.DataBean.RowsBean?): Int {

        return 0

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {


        if (isCommonViewType(viewType)) {
            return RvViewHolder.create(mContext, getItemLayoutId(), parent)


        }
        return super.onCreateViewHolder(parent, viewType)

    }

    private fun getItemLayoutId(): Int {

        return R.layout.item_city_search_result
    }

    override fun onBindViewHolder(rvViewHolder: RvViewHolder, position: Int) {

        if (isCommonViewType(rvViewHolder.itemViewType)) {
            bindData(rvViewHolder, position)
        }

    }

    private fun bindData(rvViewHolder: RvViewHolder, position: Int) {


        var tv_id = rvViewHolder.getView<TextView>(R.id.tv_id)
        var logo = rvViewHolder.getView<ImageView>(R.id.iv_logo)

        var tv_content = rvViewHolder.getView<TextView>(R.id.tv_content)
        var tv_compony_name = rvViewHolder.getView<TextView>(R.id.tv_compony_name)



        tv_id.text = "${position + 1}"

        GlideUtils.loadImage(mContext, Constants.BASE_URL + mDatas!!.get(position).companyLogo, logo)


        if (LocaleUtils.isLocaleEn(mContext)) {

            tv_content.text = mDatas!![position].enName
        } else{
            tv_content.text = mDatas!![position].cnName

        }


        rvViewHolder.itemView.setOnClickListener{

            onItemClickListener?.let {
                it.onItemClick(mDatas!![position].id)
            }
        }

    }


    public fun onItemClickListener(onItemClickListener: OnItemClickListener){

        this.onItemClickListener = onItemClickListener
    }

    public var onItemClickListener : OnItemClickListener? = null

    public interface  OnItemClickListener{

        fun onItemClick(companyId: Int)

    }


}