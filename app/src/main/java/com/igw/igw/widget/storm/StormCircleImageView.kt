package com.igw.igw.widget.storm


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.igw.igw.utils.LogUtils
import com.igw.igw.utils.UIUtils
import kotlin.math.log

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
class StormCircleImageView : AppCompatImageView {

    companion object{
        val TAG = "StormCircleImageView"
    }

    private var mPaint: Paint = Paint()

    private var mRadius: Int = 0

    private var mScale: Float = 0.0f

    constructor(context: Context) : super(context) {

    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var size = Math.min(measuredWidth, measuredHeight)
//
//        LogUtils.d(TAG,"获取额控件宽高 ${UIUtils.px2dp(size.toFloat())}")
//        LogUtils.d(TAG,"获取额控件宽高 --- ${size}")

        mRadius = size / 2
//        setMeasuredDimension(size, size)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {



        if (null != drawable) {

            val bitmap = (drawable as BitmapDrawable).bitmap
            val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

            mScale = (mRadius * 2.0f) / (bitmap.height.coerceAtMost(bitmap.width))

            val matrix = Matrix()
            matrix.setScale(mScale, mScale)
            bitmapShader.setLocalMatrix(matrix)

            mPaint.shader = bitmapShader
            mRadius.let {
                canvas.drawCircle(mRadius.toFloat(), mRadius.toFloat(), mRadius.toFloat(), mPaint)
            }


        } else {
            super.onDraw(canvas)
        }


    }

}
