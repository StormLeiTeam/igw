package com.igw.igw.app

import android.annotation.SuppressLint
import android.content.Context
import android.nfc.Tag
import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.RvViewHolder

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
abstract open class BaseAdapter<T>(protected open val mContext: Context, protected val mIsOpenLoadMore: Boolean) : RecyclerView.Adapter<RvViewHolder>() {


    private val TAG = this::class.java.simpleName

    /**
     *
     * 数据级
     */
    protected var mDatas: MutableList<T>? = null

    protected var isAutoLoadMore = true  //自动加载

    protected var mFooterLayout: RelativeLayout? = null

    protected var mOnLoadMoreListener: OnLoadMoreListener? = null

    protected var mLoadingView: View? = null

    protected var mLoadFailView: View? = null

    protected var mLoadEndView: View? = null

    init {
        mDatas = ArrayList()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {


        var viewHolder: RvViewHolder? = null
        when (viewType) {
            TYPE_FOOTER -> {
                if (mFooterLayout == null) {
                    mFooterLayout = RelativeLayout(mContext)
                }
                viewHolder = RvViewHolder.create(mFooterLayout!!)
            }
        }
        return viewHolder!!
    }


    /**
     * 判断是否是常规类型
     */
    fun isCommonViewType(viewType: Int) = viewType != TYPE_FOOTER

    /**
     * 返回item类型
     */
    fun getItem(position: Int): T? {
        if (mDatas!!.isEmpty()) {
            return null
        } else {
        }
        return mDatas!![position]
    }

    /**
     * 获取 item 的类型
     */
    override fun getItemViewType(position: Int): Int {

        if (isFooterView(position)) {
            return TYPE_FOOTER
        } else {
        }
        return getViewType(position, mDatas?.get(position))
    }

    abstract fun getViewType(position: Int, t: T?): Int

    /**
     * 判断是否在加载更多的视图
     */
    private fun isFooterView(position: Int): Boolean {

        return mIsOpenLoadMore && position >= itemCount - 1
    }

    override fun getItemCount(): Int {
        return if (mDatas != null) mDatas!!.size + footerCount else 0
    }

    /**
     * 获取上拉加载更多视图个数
     */
    protected val footerCount: Int
        get() = if (mIsOpenLoadMore && !mDatas!!.isEmpty()) 1 else 0

    /**
     * staggeredGridLayoutManager 加载更多可以占据一行
     */
    override fun onViewAttachedToWindow(holder: RvViewHolder) {

        if (isFooterView(holder.layoutPosition)) {
            val lp = holder.itemView.layoutParams

            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {

                lp.isFullSpan = true
            }
        }
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager

        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (isFooterView(position)) {
                        layoutManager.spanCount
                    } else 1
                }
            }
        }
        startLoadMore(recyclerView, layoutManager!!)
    }

    /*
     * 判断滑动时 最后一个item 是否显示
     */
    public fun startLoadMore(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager) {

        if (!mIsOpenLoadMore || mOnLoadMoreListener == null) {
            return
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (isAutoLoadMore && findLastVisableItemPosition(layoutManager) + 1 == itemCount) {

                    val first = findFirstVisableItemPosition(layoutManager)
                    val last = findLastVisableItemPosition(layoutManager)

                    if (last + 1 == itemCount && 0 == first) {

                        setFooterViewState(LOAD_INIT)

                    } else {
                        scrollLoadMore()
                    }

                } else if (isAutoLoadMore) {
                    isAutoLoadMore = false
                }


            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val first = findFirstVisableItemPosition(layoutManager)
                val last = findLastVisableItemPosition(layoutManager)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isAutoLoadMore && findLastVisableItemPosition(layoutManager) + 1 == itemCount) {
                        if (last + 1 == itemCount && first == 0) {
                            LogUtils.d(TAG,"这个状态 不来吗? .......")
//                            mFooterLayout!!.visibility = View.GONE
                        } else {
                            setFooterViewState(LOAD_LOADING)
                            scrollLoadMore()
                        }

                    }
                }
            }
        })
    }

    /**
     * 开启加载更多
     */
    private fun scrollLoadMore() {

        if (mFooterLayout?.getChildAt(0) == mLoadingView) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener?.onLoadMore(false)
            }
        }

    }


    fun findFirstVisableItemPosition(layoutManager: RecyclerView.LayoutManager): Int {

        if (layoutManager is LinearLayoutManager) {
            return layoutManager.findFirstCompletelyVisibleItemPosition()
        } else if (layoutManager is StaggeredGridLayoutManager) {

            val ints = layoutManager.findFirstCompletelyVisibleItemPositions(null)

            val first = ints[0]
            return first

        }

        return -1

    }

    /**
     *  获取显示最后的item
     */
    private fun findLastVisableItemPosition(layoutManager: RecyclerView.LayoutManager): Int {

        if (layoutManager is LinearLayoutManager) {
            return layoutManager.findLastVisibleItemPosition()
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val ints: IntArray = layoutManager.findLastVisibleItemPositions(null)

            var max = ints[0]

            for (value: Int in ints) {
                if (value > max) {
                    max = value
                }
            }
            return max
        }

        return -1
    }

    /**
     * loading View for view
     */
    fun setLoadingView(loadingView: View) {
        addFooterView(loadingView)
    }

    /**
     *  loading View for layoutId
     */
    @SuppressLint("ResourceType")
    fun setLoadingView(@LayoutRes viewId: Int) {

        if (viewId <= 0) {
            return
        }


        this.mLoadingView = layoutInflaterToView(viewId)
    }

    /**
     *  layoutInflater view for view
     *  return  view
     */
    private fun layoutInflaterToView(@LayoutRes viewId: Int): View =
            LayoutInflater.from(mContext).inflate(viewId, null)

    /**
     * load fail view for view
     */
    fun setLoadFailView(loadFailView: View) {
        addFooterView(loadFailView)
    }

    @SuppressLint("ResourceType")
            /**
             * load fail view for layoutId
             */
    fun setLoadFailView(@LayoutRes viewId: Int) {
        if (viewId <= 0) {
            return
        }
        this.mLoadFailView = layoutInflaterToView(viewId)

    }

    /**
     * load end view for view
     */
    fun setLoadEndView(loadEndView: View) {
        addFooterView(loadEndView)
    }

    @SuppressLint("ResourceType")
            /**
             *  load end view for layoutId
             */
    fun setLoadEndView(@LayoutRes viewId: Int) {

        if (viewId <= 0) {
            return
        }
        this.mLoadEndView = layoutInflaterToView(viewId)
    }

    /**
     * 加载更多视图时,添加视图View
     */
    fun addFooterView(footView: View) {

        if (mFooterLayout == null) {
            mFooterLayout = RelativeLayout(mContext)
        }

        removeAllFootView()

        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        footView.layoutParams = params
        mFooterLayout?.addView(footView)
    }

    /**
     * remove all foot View
     */
    private fun removeAllFootView() {
        if (mFooterLayout != null) {
            mFooterLayout?.removeAllViews()
        }
    }

    /**
     * 初始化数据 以及刷新功能
     */
    public fun refreshData(datas: List<T>?) {
        if (datas != null && datas.isNotEmpty()) {
            mDatas?.clear()
            mDatas?.addAll(datas)
            notifyDataSetChanged()
        }

    }

    /**
     * 上拉加载更多添加数据
     */
    public fun refreshLoadMoreData(datas: List<T>?) {
        if (datas != null && datas.isNotEmpty()) {
            mDatas?.addAll(datas)
            notifyDataSetChanged()
        }

    }

    /**
     *  设置加载更多的视图状态
     */
    public fun setFooterViewState(loadState: Int) {

        when (loadState) {


            LOAD_INIT -> {

                removeAllFootView()
            }

            LOAD_LOADING -> {
                mLoadingView.let { if (it != null) setLoadingView(it) }
            }

            LOAD_FAIL -> {
                mLoadFailView.let { if (it != null) setLoadFailView(it) }
            }


            LOAD_END -> {
                mLoadEndView.let { if (it != null) setLoadEndView(it) }
            }
        }

        mLoadFailView.let {
            if (it != null) {
                it.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        setFooterViewState(LOAD_LOADING)

                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener?.onLoadMore(true)
                        }

                    }
                })
            }
        }
    }

    /**
     * 设置监听
     */
    public fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener

    }

    /**
     *  设置 上拉加载的view
     */
    fun setLoadView(loadingView: View, loadFailView: View, loadEndView: View) {
        setLoadingView(loadingView)
        setLoadFailView(loadFailView)
        setLoadEndView(loadEndView)
    }

    /**
     * 设置 加载更多的视图
     */
    fun setLoadView(@LayoutRes loadingViewLayoutId: Int, @LayoutRes loadFailLayoutId: Int,
                    @LayoutRes loadEndLayoutId: Int) {
        loadingViewLayoutId.let { if (it > 0) setLoadingView(it) }

        loadFailLayoutId.let { if (it > 0) setLoadFailView(it) }

        loadEndLayoutId.let { if (it > 0) setLoadEndView(it) }

    }

    // LoadMore视图 interface
    interface OnLoadMoreListener {
        fun onLoadMore(isClickLoadMore: Boolean)
    }

    // 伴生对象
    companion object {
        val TYPE_COMMON = 1001  //基本类型
        val TYPE_FOOTER = 1002  //加载更多
        val LOAD_LOADING = 10001 //加载中
        val LOAD_FAIL = 10002    //加载失败
        val LOAD_END = 10003     // 加载结束
        val LOAD_INIT = 10004 // 初状态

    }


}