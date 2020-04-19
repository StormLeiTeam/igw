package com.igw.igw.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
object TimesUtils {



    // 时间转化
    fun time2StringTime(time: String?, currentFormat: String?, targetFormat: String?): String? {
        if (null == time || time.isEmpty()) {
            return null
        }
        var date: Date? = Date()
        try {
            date = SimpleDateFormat(currentFormat).parse(time + "") //先按照原格式转换为时间
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return SimpleDateFormat(targetFormat).format(date)
    }

    /**
     * date 转String -- 2010-07-06
     */
    fun data2String(date: Date): String {

        // 获取当前时间
        val format = SimpleDateFormat("yyyy-MM-dd")

        return format.format(date)

    }

}