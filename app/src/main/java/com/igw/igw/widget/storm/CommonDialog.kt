package com.igw.igw.widget.storm

import android.animation.TypeEvaluator
import android.app.Dialog
import android.content.Context
import android.opengl.Visibility
import android.os.Build
import android.support.constraint.solver.GoalRow
import android.support.v4.content.ContextCompat
import android.util.Size
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.utils.UIUtils
import java.util.zip.Inflater

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */

class CommonDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {


    companion object {
        val TAG = CommonDialog
    }


    public  class Builder {

        private lateinit var context: Context
        private lateinit var mDialog: CommonDialog
        private lateinit var mLayout: View


        lateinit var mTitle: TextView
        lateinit var mCustomLayout: LinearLayout
        lateinit var mContent: TextView
        lateinit var mCancel: TextView
        lateinit var mCommit: TextView
        lateinit var lineView: View

        var isCustomLayout = false

        var isTitle = false


        private var mCommitListener: View.OnClickListener? = null
        private var mCancelListener: View.OnClickListener? = null


        constructor(context: Context) {

            this.context = context

            mDialog = CommonDialog(context, R.style.Theme_AppCompat_Dialog)

            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            mLayout = layoutInflater.inflate(R.layout.dialog_common, null)

            mDialog.addContentView(mLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))


            // init
            mTitle = mLayout.findViewById(R.id.tv_title)
            mContent = mLayout.findViewById(R.id.tv_content)
            mCustomLayout = mLayout.findViewById(R.id.ll_custom)
            mCancel = mLayout.findViewById(R.id.tv_cancel)
            mCommit = mLayout.findViewById(R.id.tv_commit)
            lineView = mLayout.findViewById(R.id.lineview)


        }

        private fun setTextSize(view: TextView, value: Float) {

            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)

        }

        public fun setTitle(title: String): Builder {

            mTitle.text = title
            isTitle = true
            return this
        }

        public fun setTitleColor(colorId: Int): Builder {

            mTitle.setTextColor(ContextCompat.getColor(context, colorId))
            return this

        }

        public fun setTitleSize(value: Float): Builder {

            setTextSize(mTitle, value)
            return this
        }


        public fun setContent(content: String): Builder {
            mContent.text = content
            return this
        }

        public fun setContentTextColor(colorId: Int): Builder {

            mContent.setTextColor(ContextCompat.getColor(context, colorId))
            return this
        }

        public fun setContentTextSize(value: Float): Builder {
            setTextSize(mContent, value)
            return this
        }

        public fun setImage(resId: Int): Builder {
            mCustomLayout.removeAllViews()

            var imageView = inflate(context, R.layout.dialog_image, null)

            imageView.setBackgroundResource(resId)

            mCustomLayout.addView(imageView)
            isCustomLayout = true
            return this
        }


        public fun setView(view: View): Builder {
            mCustomLayout.removeAllViews()
            mCustomLayout.addView(view)
            isCustomLayout = true
            return this

        }


        public fun setCommit(text: String , listen: View.OnClickListener?): Builder{

            mCommit.text = text
            mCommitListener = listen

            return this
        }

        public fun setCancel(text: String, listen: View.OnClickListener?): Builder {
            mCancel.text = text
            mCancelListener = listen
            return this
        }


        public fun cancelVisable(visibility: Int): Builder {
            if (visibility == VISIBLE) {
                mCancel.visibility = VISIBLE
                lineView.visibility = VISIBLE
            } else if (visibility == GONE) {
                mCancel.visibility = GONE
                lineView.visibility = GONE
            }

            return this
        }


        public fun build(): CommonDialog {
            mCommit.setOnClickListener{

                mCommitListener?.onClick(it)
                mDialog.dismiss()

            }

            mCancel.setOnClickListener {
                mCancelListener?.onClick(it)
                mDialog.dismiss()
            }


            if (isCustomLayout) mCustomLayout.visibility = VISIBLE  else mCustomLayout.visibility = GONE

            if (isTitle) mTitle.visibility = VISIBLE else mTitle.visibility = GONE


            mDialog.setContentView(mLayout)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)

            var params = mDialog.window!!.attributes


            if (isCustomLayout) {

                params.width = UIUtils.dp2px(270F).toInt()
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT

            } else {

                params.width = UIUtils.dp2px(270F).toInt()
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT


            }


            mDialog.window?.setBackgroundDrawableResource(R.color.transparent)
            mDialog.window?.attributes = params

            return mDialog

        }

    }
}
