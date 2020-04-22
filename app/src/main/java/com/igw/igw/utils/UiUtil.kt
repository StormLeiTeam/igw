package com.igw.igw.utils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
object UiUtil {

    fun Int.sp2px(): Int {
        val scale = AppUtils.appContext.resources.displayMetrics.scaledDensity;
        return (this * scale + 0.5f).toInt()
    }


    fun Int.px2sp(): Int {
        val scaledDensity = AppUtils.appContext.resources.displayMetrics.scaledDensity
        return (this / scaledDensity + 0.5f).toInt()
    }



    fun Int.dip2px(): Int {
        var scale = AppUtils.appContext.resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }


    fun Int.px2dip(): Int {
        val scale = AppUtils.appContext.resources.displayMetrics.density
        return (this / scale + 0.5f).toInt()
    }

}