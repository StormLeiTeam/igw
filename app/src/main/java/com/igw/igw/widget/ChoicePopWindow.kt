package com.igw.igw.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.igw.igw.R
import com.igw.igw.utils.LogUtils
import com.jakewharton.rxbinding2.view.RxView


/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe choice photo popwindow
 */
class ChoicePopWindow : PopupWindow {

    companion object {
        val TAG = ChoicePopWindow::class.java.simpleName
        val BUTTON_CHOICE_ONE = 0
        val BUTTON_CHOICE_TWO = 1


    }


    private lateinit var mContext: Context
    private lateinit var mImageView: ImageView
    private lateinit var mRootView: View

    private lateinit var mCancel: Button
    private lateinit var choiceOneButton: Button
    private lateinit var choiceTwoButton: Button

    private var mBtnChoiceOneListen: OnButtonChoiceOneListener? = null
    private var mBtnChoiceTwoListen: OnButtonChoiceTwoListener? = null

    constructor(context: Context ) {
        this.mContext = context

        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mRootView = inflate.inflate(R.layout.choice_bottom_popwindow, null)

        initView()
        this.contentView = mRootView
        this.width = LinearLayout.LayoutParams.MATCH_PARENT
        this.height = LinearLayout.LayoutParams.MATCH_PARENT
        this.isFocusable = true
        this.isOutsideTouchable = false
        val backGround = ColorDrawable(-0x78000000)
        this.setBackgroundDrawable(background)

        setUpListener()

    }

    /**
     * 点击框外取消
     *
     */
    private fun setDismiss() {


        mRootView.setOnTouchListener { v, event ->

            val bottom = mRootView.findViewById<LinearLayout>(R.id.ll_choice_bottom).bottom
            val top = mRootView.findViewById<LinearLayout>(R.id.ll_choice_bottom).top
            val downY = event.rawY.toInt()

            if (downY > top) {

                dismiss()
            }

            return@setOnTouchListener false
        }

    }


    @SuppressLint("CheckResult")
    private fun setUpListener() {

        RxView.clicks(choiceTwoButton).subscribe {
            LogUtils.d(TAG, "----------------___> 点击状态")
            dismiss()
            mBtnChoiceTwoListen?.onClick()
        }
        RxView.clicks(choiceOneButton).subscribe {

            LogUtils.d(TAG, "----------------___> 点击状态")

            if (mBtnChoiceOneListen != null) {
                dismiss()
                mBtnChoiceOneListen!!.onClick()

            }
        }


        RxView.clicks(mCancel).subscribe {
            dismiss()
        }

    }

    private fun initView() {

        mCancel = mRootView.findViewById<Button>(R.id.btn_cancel)
        choiceOneButton = mRootView.findViewById<Button>(R.id.btn_choice_one)
        choiceTwoButton = mRootView.findViewById<Button>(R.id.btn_choice_two)


    }

    /**
     * 设置是否隐藏
     *
     */
    fun setChoiceVisible(item: Int, visibility: Int) {
        when (item) {
            BUTTON_CHOICE_ONE -> {

                choiceOneButton.visibility = visibility
            }

            BUTTON_CHOICE_TWO -> {

                choiceTwoButton.visibility = visibility

            }
        }
    }

    fun setCancelText(text: String) {
        mCancel.text = text
    }

    fun setChoiceTwoText(text: String) {
        choiceTwoButton.text = text
    }

    fun setChoiceOneText(text: String) {
        choiceOneButton.text = text

    }


    public fun setOnButtonChoiceOneListener(listen: OnButtonChoiceOneListener) {

        this.mBtnChoiceOneListen = listen


    }

    public fun setOnButtonChoiceTwoListener(listen: OnButtonChoiceTwoListener) {
        this.mBtnChoiceTwoListen = listen
    }

    interface OnButtonChoiceTwoListener {
        fun onClick()
    }

    interface OnButtonChoiceOneListener {
        fun onClick()
    }

}