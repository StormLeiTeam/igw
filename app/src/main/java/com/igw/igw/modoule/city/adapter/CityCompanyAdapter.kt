package com.igw.igw.modoule.city.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.igw.igw.R
import com.igw.igw.app.BaseAdapter
import com.igw.igw.bean.city.CityCompanyBean
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.RvViewHolder
import com.shengshijingu.yashiji.common.Constants

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class CityCompanyAdapter(context: Context, isOpenLoadMore: Boolean) :
        BaseAdapter<CityCompanyBean.DataBean.RowsBean>(context, isOpenLoadMore) {

    companion object {

        val TAG = "CityCompanyAdapter"
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {

        if (isCommonViewType(viewType)) {

            return RvViewHolder.create(mContext, getItemLayoutId(), parent)

        }
        return super.onCreateViewHolder(parent, viewType)

    }

    private fun getItemLayoutId(): Int {

        return R.layout.item_city_company
    }

    override fun getViewType(position: Int, t: CityCompanyBean.DataBean.RowsBean?): Int {


        return 0;
    }

    override fun onBindViewHolder(rvViewHolder: RvViewHolder, position: Int) {


        if (isCommonViewType(rvViewHolder.itemViewType)) {

            bindData(rvViewHolder,position)
        }
    }

    private fun bindData(rvViewHolder: RvViewHolder, position: Int) {

        var ivCompanyLogo = rvViewHolder.getView<ImageView>(R.id.iv_company_logo)



        GlideUtils.loadImage(mContext, Constants.BASE_URL + mDatas!![position].companyLogo, ivCompanyLogo)


        rvViewHolder.itemView.setOnClickListener{


            onItemClickListener?.let {
                it.onItemClick(mDatas!![position].id)

            }
        }

    }



    public var onItemClickListener : OnItemClickListener? = null

    public fun onItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener

    }

    public interface OnItemClickListener{

        fun onItemClick(companyId: Int)

    }
}
