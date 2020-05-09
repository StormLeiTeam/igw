package com.igw.igw.utils

import android.app.Activity
import android.graphics.Rect
import android.view.View

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class SoftKeyBoardManager(activity: Activity) {

    companion object{
        val TAG = "SoftKeyBoardManager"


        public fun setOnSoftKeyBoardChangeListener(activity: Activity,
                                                   listener: OnSoftKeyBoardChangeListener) {
            val softKeyBoardManager = SoftKeyBoardManager(activity)
            softKeyBoardManager.setOnSoftKeyBoardChangeListener(listener)
        }

    }

    var rootViewVisibleHeight: Int = 0;

    private var mListener: OnSoftKeyBoardChangeListener? = null
    private var rootView:View = activity.window.decorView


    init {


        // 监听视图中全部布局发生变化或者某个视图可视状态发生变化

        rootView.viewTreeObserver.addOnGlobalLayoutListener {

            val r = Rect()
            rootView.getWindowVisibleDisplayFrame(r) // 可视区域大小
            val visibleHeight = r.height()

            if (rootViewVisibleHeight == 0 ){
                rootViewVisibleHeight = visibleHeight
                return@addOnGlobalLayoutListener
            }

            if (rootViewVisibleHeight == visibleHeight){
                return@addOnGlobalLayoutListener
            }

            //
            if (rootViewVisibleHeight - visibleHeight >200){

                if (mListener != null){

                    mListener!!.keyBoardShow(rootViewVisibleHeight - visibleHeight)

                }

                rootViewVisibleHeight = visibleHeight
                return@addOnGlobalLayoutListener
            }


            if (visibleHeight - rootViewVisibleHeight > 200){

                if (mListener != null){

                    mListener!!.keyBoardHide(visibleHeight - rootViewVisibleHeight)
                    rootViewVisibleHeight = visibleHeight
                    return@addOnGlobalLayoutListener
                }
            }



        }


    }


    private fun setOnSoftKeyBoardChangeListener(listener: OnSoftKeyBoardChangeListener) {
        this.mListener = listener
    }


    interface OnSoftKeyBoardChangeListener {
        fun keyBoardShow(height: Int)
        fun keyBoardHide(height: Int)
    }





}