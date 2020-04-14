package com.igw.igw.widget.storm

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.igw.igw.R
import com.igw.igw.app.IGWApplication
import com.igw.igw.utils.AppUtils
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.UIUtils
import kotlinx.android.synthetic.main.status_bar_view.view.*



/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe custom statusBar
 */
class StatusBarView : FrameLayout {


    companion object {
        val TAG = StatusBarView::class.java.canonicalName

        var statusBarHeight = 0

    }


    private lateinit var mContext: Context


    private lateinit var status_bar: View
    private lateinit var app_action_bar: RelativeLayout
    private lateinit var rl_back: RelativeLayout
    private lateinit var iv_back: ImageView

    private var statusBarColor = 0
    private var appActionBarColor = 0
    private var backTextColor = 0
    private var menuTextColor = 0
    private var subTitleColor = 0
    private var statusTitleColor = 0
    private var confirmTextColor = 0

    private var appActionBarHeight = 0

    private var showStatusBar = true
    private var showAppActionBar = true
    private var showBack = true
    private var showBackImage = true
    private var showBackTitle = true

    private var showShare = true
    private var showConfirm = true

    private var showMenu = true
    private var showMenuImage = true
    private var showMenuTitle = true
    private var showDo = true

    private var isShowMenu = true
    private var showTitle = true


    private var backTextSize = 0F
    private var menuTextSize = 0F
    private var titleSize = 0F
    private var subTitleSize = 0F
    private var confirmTextSize = 0F


    private var backImageRes = 0
    private var menuImageRes = 0
    private var subTitleRes = 0
    private var doTitleRes = 0
    private var shareRes = 0


    private var backText: String? = null
    private var menuText: String? = null
    private var statusTitle: String? = null
    private var subTitle: String? = null
    private var confirmTitle: String? = null


    private var mBackClickListener: OnBackClickListener? = null
    private var mMenuClickListener: OnMenuClickListener? = null
    private var mOnShareClickListener: OnShareClickListener? = null
    private var mOnConfirmClickListener: OnConfirmClickListener? = null


    constructor(context: Context) : super(context, null) {

        init(context, null)

    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        init(context, attrs)


    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)

    }


    fun init(context: Context, attrs: AttributeSet?) {
        this.mContext = context
        if (attrs != null) {
            config(context, attrs)
        }
        initView(context)


    }

    private fun initView(context: Context) {
        val inflate: FrameLayout = View.inflate(context, R.layout.status_bar_view, this) as FrameLayout

        status_bar = inflate.findViewById<View>(R.id.status_bar)
        app_action_bar = inflate.findViewById<RelativeLayout>(R.id.app_action_bar)
        rl_back = inflate.findViewById<RelativeLayout>(R.id.rv_back)
        iv_back = inflate.findViewById<ImageView>(R.id.iv_back)

        // 设置 action bar 的高额度

        val layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, appActionBarHeight)

        app_action_bar.layoutParams = layoutParams

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            status_bar.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        }


        // params

        app_action_bar.setBackgroundColor(appActionBarColor)
        status_bar.setBackgroundColor(statusBarColor)

        tv_back.setTextColor(backTextColor)
        tv_title.setTextColor(statusTitleColor)
        tv_menu.setTextColor(menuTextColor)
        tv_subTitle.setTextColor(subTitleColor)
        tv_confirm.setTextColor(confirmTextColor)


        tv_back.textSize = backTextSize
        tv_menu.textSize = menuTextSize
        tv_subTitle.textSize = subTitleSize
        tv_title.textSize = titleSize
        tv_confirm.textSize = confirmTextSize

//
//        iv_back.setImageResource(backImageRes)
//        iv_menu.setImageResource(menuImageRes)
//        iv_do.setImageResource(subTitleRes)
//        iv_share.setImageResource(shareRes)


        iv_back.setBackgroundResource(backImageRes)
        iv_menu.setBackgroundResource(menuImageRes)
        iv_do.setBackgroundResource(subTitleRes)
        iv_share.setBackgroundResource(shareRes)


//        iv_share.setImageDrawable(ContextCompat.getDrawable(mContext,shareRes))


        tv_back.text = backText
        tv_menu.text = menuText


        if (statusTitle == null) {

            tv_title.text = "标题"
        } else {
            tv_title.text = statusTitle
        }

        if (confirmTitle == null) {
            tv_confirm.text = "确认"
        } else {
            tv_confirm.text = confirmTitle
        }



        status_bar.visibility = if (showStatusBar) View.VISIBLE else View.GONE
        app_action_bar.visibility = if (showAppActionBar) View.VISIBLE else View.GONE

        iv_share.visibility = if (showShare) View.VISIBLE else View.GONE
        tv_confirm.visibility = if (showConfirm) View.VISIBLE else View.GONE

//        tv_confirm.visibility = if (showConfirm) View.VISIBLE else View.GONE
//        iv_share.visibility = if (showShare) View.VISIBLE else View.GONE

        rl_menu.visibility = if (showMenu) View.VISIBLE else View.GONE
        rl_back.visibility = if (showBack) View.VISIBLE else View.GONE


        if (isShowMenu) {
            // 显示menu

            rv_back.visibility = View.GONE
            rl_menu.visibility = if (showMenu) View.VISIBLE else View.GONE

            iv_back.visibility = if (showBackImage) View.VISIBLE else View.GONE
            tv_back.visibility = if (showBackTitle) View.VISIBLE else View.GONE


        } else {
            // 显示back
            rv_back.visibility = if (showBack) View.VISIBLE else View.GONE
            rl_menu.visibility = View.GONE

            iv_menu.visibility = if (showMenuImage) View.VISIBLE else View.GONE
            tv_menu.visibility = if (showMenuTitle) View.VISIBLE else View.GONE


        }

        tv_title.visibility = if (showTitle) View.VISIBLE else View.GONE
        iv_do.visibility = if (showDo) View.VISIBLE else View.GONE



        setUpListener()

    }

    private fun setUpListener() {

        iv_back.setOnClickListener {

            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            if (mBackClickListener != null) {
                mBackClickListener?.onClick()
            } else {

                doOnBack(mContext)
            }
        }

        iv_menu.setOnClickListener {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            showLeftMenu(mContext)
            mMenuClickListener?.onClick()


        }

        tv_confirm.setOnClickListener {

            if (mOnConfirmClickListener != null) {
                mOnConfirmClickListener?.onClick()
            }
        }

        iv_share.setOnClickListener {
            mOnShareClickListener?.let {
                it.onShare()
            }
        }
    }

    private fun doOnBack(context: Context) {

        if (context is AppCompatActivity) {
            (context as AppCompatActivity).onBackPressed()
            (context as AppCompatActivity).finish()
        } else if (context is ContextWrapper) {
            doOnBack((context as ContextWrapper).baseContext)
        }
    }

    private fun showLeftMenu(context: Context) {

        if (context is AppCompatActivity) {
//            val leftMenu = (context as AppCompatActivity).findViewById<DrawerLayout>(R.id.main_activity)
//
//            if (leftMenu.)
            (context as AppCompatActivity).findViewById<DrawerLayout>(R.id.root).openDrawer(Gravity.START)

            LogUtils.d(TAG, "呼唤侧边菜单")

        } else if (context is ContextWrapper) {
            showLeftMenu((context as ContextWrapper).baseContext)

        }

    }


    private fun config(context: Context, attrs: AttributeSet) {


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusBarView)

        try {
            showStatusBar = typedArray.getBoolean(R.styleable.StatusBarView_statusBarVisible, true)
            showAppActionBar = typedArray.getBoolean(R.styleable.StatusBarView_appActionBarVisible, true)
            showBack = typedArray.getBoolean(R.styleable.StatusBarView_backVisible, false)
            showBackImage = typedArray.getBoolean(R.styleable.StatusBarView_backImageVisible, false)
            showBackTitle = typedArray.getBoolean(R.styleable.StatusBarView_backTitleVisible, false)

            showMenu = typedArray.getBoolean(R.styleable.StatusBarView_menuVisible, true)
            showMenuImage = typedArray.getBoolean(R.styleable.StatusBarView_menuImageVisible, true)
            showMenuTitle = typedArray.getBoolean(R.styleable.StatusBarView_menuTitleVisible, true)

            isShowMenu = typedArray.getBoolean(R.styleable.StatusBarView_isShowMenu, true)
            showTitle = typedArray.getBoolean(R.styleable.StatusBarView_showTitle, true)

            showShare = typedArray.getBoolean(R.styleable.StatusBarView_shareVisible, false)
            showConfirm = typedArray.getBoolean(R.styleable.StatusBarView_confirmVisible, false)



            statusBarColor = typedArray.getColor(R.styleable.StatusBarView_statusBarColor, Color.parseColor("#FFFFFF"))
            appActionBarColor = typedArray.getColor(R.styleable.StatusBarView_appActionBarColor, Color.parseColor("#FFFFFF"))
            backTextColor = typedArray.getColor(R.styleable.StatusBarView_backTextColor, Color.parseColor("#000000"))
            menuTextColor = typedArray.getColor(R.styleable.StatusBarView_menuTextColor, Color.parseColor("#000000"))
            subTitleColor = typedArray.getColor(R.styleable.StatusBarView_subTitleColor, Color.parseColor("#000000"))
            statusTitleColor = typedArray.getColor(R.styleable.StatusBarView_statusTitleColor, Color.parseColor("#000000"))
            confirmTextColor = typedArray.getColor(R.styleable.StatusBarView_confirmTextColor, Color.parseColor("#000000"))

            appActionBarHeight = typedArray.getDimension(R.styleable.StatusBarView_appActionBarHeight, UIUtils.dp2px(44F)).toInt()
            backTextSize = UIUtils.px2sp(typedArray.getDimension(R.styleable.StatusBarView_backTextSize, UIUtils.sp2px(16F)))
            menuTextSize = UIUtils.px2sp(typedArray.getDimension(R.styleable.StatusBarView_menuTextSize, UIUtils.sp2px(16F)))
            titleSize = UIUtils.px2sp(typedArray.getDimension(R.styleable.StatusBarView_titleSize, UIUtils.sp2px(17F)))
            subTitleSize = UIUtils.px2sp(typedArray.getDimension(R.styleable.StatusBarView_subTitleSize, UIUtils.sp2px(14F)))
            confirmTextSize = UIUtils.px2sp(typedArray.getDimension(R.styleable.StatusBarView_confirmTextSize, UIUtils.sp2px(16F)))

            backImageRes = typedArray.getResourceId(R.styleable.StatusBarView_backImageRes, R.drawable.back_arrow_default)
            menuImageRes = typedArray.getResourceId(R.styleable.StatusBarView_menuImageRes, R.drawable.status_menu)
            subTitleRes = typedArray.getResourceId(R.styleable.StatusBarView_subTitleRes, R.drawable.status_menu)
            doTitleRes = typedArray.getResourceId(R.styleable.StatusBarView_doTitleRes, R.drawable.status_menu)
            shareRes = typedArray.getResourceId(R.styleable.StatusBarView_shareRes, R.drawable.share_icon)


            backText = typedArray.getString(R.styleable.StatusBarView_backText)
            menuText = typedArray.getString(R.styleable.StatusBarView_menuText)
            statusTitle = typedArray.getString(R.styleable.StatusBarView_statusTitle)
            subTitle = typedArray.getString(R.styleable.StatusBarView_subTitle)
            confirmTitle = typedArray.getString(R.styleable.StatusBarView_confirmText)


        } finally {

            typedArray.recycle()
        }

    }


    /**
     * 获取状态栏的高度
     */
    fun getStatusBarHeight(): Int {

        if (statusBarHeight != 0) {
            return statusBarHeight
        }


        val context = IGWApplication.getContext()

        var result = 0

        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }


        statusBarHeight = result

        return result


    }


    /**
     * 设置 菜单监听
     */
    public fun setOnMenuClickListener(listener: OnMenuClickListener) {
        this.mMenuClickListener = listener
    }


    public fun setOnBackClickListener(listener: OnBackClickListener) {
        this.mBackClickListener = listener

    }

    public fun setOnShareClickListener(listener: OnShareClickListener) {
        this.mOnShareClickListener = listener
    }

    public fun setOnConfirmClickListener(listener: OnConfirmClickListener) {
        this.mOnConfirmClickListener = listener
    }

    /**
     * 菜单监听
     *
     */
    interface OnMenuClickListener {
        fun onClick()
    }

    interface OnBackClickListener {
        fun onClick()
    }

    interface OnShareClickListener {
        fun onShare()
    }

    interface OnConfirmClickListener {
        fun onClick()
    }


    //---------------------------------------------

    public fun setConfirmTextColor(color: Int) {
        setTextColor(tv_confirm, color)
    }

    public fun setConfirmText(confirmText: String) {
        setText(tv_confirm, confirmText)
    }

    public fun setBackText(backText: String) {
        setText(tv_back, backText)

    }

    public fun setMenuText(menuText: String) {

        setText(tv_menu, menuText)
    }

    public fun setTitle(title: String) {
        setText(tv_title, title)
    }

    public fun setSubTitle(subTitle: String) {
        setText(tv_subTitle, subTitle)

    }


    public fun setConfirmVisible(visible: Int) {
        setVisible(tv_confirm, visible)
    }

    fun setShareVisible(visible: Int) {
        setVisible(iv_share, visible)
    }

    public fun setStatusBarVisible(visible: Int) {
        setVisible(status_bar, visible)
    }

    public fun setAppActionBarVisible(visible: Int) {
        setVisible(app_action_bar, visible)
    }

    public fun setBackVisible(visible: Int) {
        setVisible(rl_back, visible)

    }

    public fun setMenuVisible(visible: Int) {
        setVisible(rl_menu, visible)

    }


    public fun setBackImageVisible(visible: Int) {
        setVisible(iv_back, visible)

    }


    public fun setBackTitleVisible(visible: Int) {
        setVisible(tv_back, visible)

    }

    public fun setMenuImageVisible(visible: Int) {
        setVisible(iv_menu, visible)

    }

    public fun setMenuTitleVisible(visible: Int) {
        setVisible(tv_menu, visible)

    }

    public fun setTitleVisible(visible: Int) {
        setVisible(tv_title, visible)

    }


    fun setConfirmTextSize(textSize: Float) {
        setTextSize(tv_confirm, textSize)
    }


    public fun setBackTextSize(textSize: Float) {

        setTextSize(tv_back, textSize)

    }

    public fun setMenuTextSize(textSize: Float) {

        setTextSize(tv_menu, textSize)

    }

    public fun setTitleTextSize(textSize: Float) {

        setTextSize(tv_title, textSize)

    }

    public fun setSubTitleTextSize(textSize: Float) {

        setTextSize(tv_subTitle, textSize)

    }


    public fun setShareBackground(redId: Int) {

//       iv_share.setImageResource(redId)
        setBackGroundResource(iv_share, redId)
    }

    public fun setBackImageBackground(redId: Int) {
        setBackGroundResource(iv_back, redId)

    }


    public fun setMenuImageBackground(redId: Int) {
        setBackGroundResource(iv_menu, redId)

    }

    //--------------------------------------------------

    public fun setTextColor(textView: TextView, color: Int) {
        textView.setTextColor(ContextCompat.getColor(mContext, color))
    }


    // -- 新添加
    /**
     * 标题字体颜色
     */
    public fun setTitleTextColor(color: Int) {
        setTextColor(tv_title, color)
    }


    public fun setBackTextColor(color: Int) {
        setTextColor(tv_back, color)
    }

    public fun setSubTitleTextColor(color: Int) {
        setTextColor(tv_subTitle, color)
    }


    public fun setTextSize(textView: TextView, textSize: Float) {
        textView.textSize = textSize

    }

    public fun setText(textView: TextView, text: String) {
        textView.text = text
    }


    public fun setVisible(view: View, visible: Int) {

        view.visibility = visible
    }


    private fun setBackGroundResource(imageView: ImageView, resId: Int) {
        imageView.setBackgroundResource(resId)
    }




    // 状态view 设置背景颜色

    public fun setStateBarBackgroundColor(colorId: Int) {
        setBackgroundColor(status_bar, colorId);


    }


    public fun setActionBarBackgroundColor(colorId: Int) {
        setBackgroundColor(app_action_bar, colorId)
    }


    private fun setBackgroundColor(view: View, colorId: Int) {
        view.setBackgroundColor(ContextCompat.getColor(mContext, colorId))
    }
}