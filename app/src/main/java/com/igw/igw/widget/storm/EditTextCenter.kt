package com.igw.igw.widget.storm

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.text.TextUtils
import android.util.AttributeSet
import android.view.TextureView
import android.widget.TextView
import com.igw.igw.R
import com.igw.igw.utils.SoftKeyBoardManager
import com.igw.igw.utils.UIUtils
/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe 图片居中显示
 */
class EditTextCenter : AppCompatEditText {

    companion object {

        val TAG = "EditTextCenter"
    }


    private  var drawableIcon: Int = R.drawable.search
    private var isShowCenter = false // 是否居中显示icon 默认不居中
    private var isShowLeft  = false // 键盘打开后 icon 是否 显示在左边 默认 不是
    private  var isShowHint = false  // 键盘打开后是否显示提示文字  默认不显示
    private  var  isOpen = false  // 是否开启使用

    private var  isDraw = true  // 是否绘制 ,配居中显示使用
    private var hintText = ""


    private lateinit var mContext: Context

    constructor(context: Context?) : super(context, null) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        this.mContext = context!!

        attrs?.let {
            config(context, it)
        }
    }

    private fun config(context: Context, attrs: AttributeSet) {


        val array = context.obtainStyledAttributes(attrs, R.styleable.EditTextCenter)

        try {
            drawableIcon = array.getResourceId(R.styleable.EditTextCenter_drawableIcon, R.drawable.search)
            isShowCenter = array.getBoolean(R.styleable.EditTextCenter_isCenter, false)
            isShowLeft  = array.getBoolean(R.styleable.EditTextCenter_isShowLeft,false)
            isShowHint = array.getBoolean(R.styleable.EditTextCenter_isShowHint, true)
            isOpen = array.getBoolean(R.styleable.EditTextCenter_isOpen,false)


        } finally {
            array.recycle()
        }


        // 通过监听挼键盘 看显示
        if (context is Activity && isOpen){

            hintText  = hint.toString()
            SoftKeyBoardManager.setOnSoftKeyBoardChangeListener(mContext as Activity,object :SoftKeyBoardManager.OnSoftKeyBoardChangeListener{
                override fun keyBoardShow(height: Int) {

                    // 键盘显示
                    isCursorVisible = true
                    isDraw = false
                    if (isShowHint) {

                        hint = hintText

                    }else{
                        if (!TextUtils.isEmpty(hintText)) {
                            hint = ""
                        }

                    }

                }

                override fun keyBoardHide(height: Int) {

                    // 键盘消失
                    isCursorVisible = false
                    isDraw = TextUtils.isEmpty(text.toString())

                    if (!TextUtils.isEmpty(hintText)) {
                        hint = hintText
                    }

                }


            })

        }


    }

    override fun onDraw(canvas: Canvas?) {

        if (!isOpen){
            super.onDraw(canvas)
            return

        }

        if (isShowCenter && isDraw){

            setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, drawableIcon), null, null, null)
            var textWidth = paint.measureText(hint.toString())

            val drawablePadding = compoundDrawablePadding

            var drawableWidth =    ContextCompat.getDrawable(mContext,drawableIcon)!!.intrinsicWidth

            var bodyWidth = textWidth + drawableWidth + drawablePadding

            canvas?.translate((width - bodyWidth - paddingLeft - paddingRight)/2, 0F )

            super.onDraw(canvas)
        }else{


            if (isShowLeft){

                setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, drawableIcon), null, null, null)

            }else{
                setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)


            }
            super.onDraw(canvas)


        }
    }


}