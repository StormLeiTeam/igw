package com.igw.igw.widget.storm

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.igw.igw.R

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

public class MorePopWindow : PopupWindow {

    var listener: OnPopWindowItemClickListener? = null
//    private var contentView: View? = null

    var rootView: View? = null

    constructor(context: Activity, listener: OnPopWindowItemClickListener) {

        this.listener = listener

        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        rootView = inflater.inflate(R.layout.main_popup_title_more, null)

        this.contentView = rootView
        this.width = LinearLayout.LayoutParams.WRAP_CONTENT
        this.height = LinearLayout.LayoutParams.WRAP_CONTENT
        this.isFocusable = true
        this.isOutsideTouchable = true

        this.update()
        val dw = ColorDrawable(0)
        this.setBackgroundDrawable(dw)
        this.animationStyle = R.style.AnimationMainTitleMore


        setUpListener()
    }

    private fun setUpListener() {



        rootView?.findViewById<Button>(R.id.btn_action_1)?.setOnClickListener {


            listener?.let {
                it.languageSelector()
            }
            dismiss()
        }

        rootView?.findViewById<Button>(R.id.btn_action_2)?.setOnClickListener {
            listener?.let {
                it.groupMember()
            }
            dismiss()
        }
    }


    interface OnPopWindowItemClickListener {
        fun languageSelector()
        fun groupMember()
    }


    public fun showPopupWindow(parent: View) {

        if (!this.isShowing) {

            this.showAsDropDown(parent, 0, 0)

        }else{
            this.dismiss()

        }
    }

}