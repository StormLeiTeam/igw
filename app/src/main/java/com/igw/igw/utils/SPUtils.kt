package com.igw.igw.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.util.SimpleArrayMap
import com.igw.igw.app.IGWApplication


/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class SPUtils private constructor(spName: String) {


    private var sp: SharedPreferences


    init {

        sp = IGWApplication.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE)

    }


    public val all: Map<String, *>
        get() = sp.all


    /**
     * String
     */
    public fun put(key: String, value: String) = put(key,value,false)

    public fun put(key: String, value: String, isCommit: Boolean) {
        if (isCommit) {
            sp.edit().putString(key, value).commit()
        } else {
            sp.edit().putString(key,value).apply()
        }

    }

    public fun getString(key: String): String? = sp.getString(key, "")


    public fun getString(key: String, defaultValue: String): String? = sp.getString(key, defaultValue)


    /**
     * Int
     */
    public fun put(key: String, value: Int) = put(key, value,false)
    public fun put(key: String, value: Int, isCommit: Boolean) {

        if (isCommit)
            sp.edit().putInt(key,value).commit()
        else
            sp.edit().putInt(key,value).apply()

    }

    public fun getInt(key: String): Int = sp.getInt(key, -1)

    public fun getInt(key: String, defaultValue: Int): Int = sp.getInt(key, defaultValue)


    /**
     * float
     */
    public fun put(key: String, value: Float) = put(key,value,false)
    public fun put(key: String, value: Float, isCommit: Boolean) {
        if (isCommit)
            sp.edit().putFloat(key,value).commit()
        else
            sp.edit().putFloat(key,value).apply()
    }

    public fun getFloat(key: String): Float = sp.getFloat(key, -1F)

    public fun getFloat(key: String, defaultValue: Float) = sp.getFloat(key, defaultValue)


    /**
     * long
     */
    public fun put(key: String, value: Long) = put(key, value, false)


    public fun put(key: String, value: Long, isCommit: Boolean) {
        if (isCommit)
            sp.edit().putLong(key,value).commit()
        else
            sp.edit().putLong(key,value).apply()
    }

    public fun getLong(key: String): Long = sp.getLong(key, -1L)

    public fun getLong(key: String, defaultValue: Long) = sp.getLong(key, defaultValue)


    /**
     *  boolean
     */
    public fun put(key: String, value: Boolean) = put(key,value,false)

    public fun put(key: String, value: Boolean, isCommit: Boolean) {
        if (isCommit)
            sp.edit().putBoolean(key,value).commit()
        else
            sp.edit().putBoolean(key,value).apply()
    }

    public fun getBoolean(key: String): Boolean = sp.getBoolean(key, false)

    public fun getBoolean(key: String, defaultValue: Boolean) = sp.getBoolean(key, defaultValue)


    public fun remove(key: String) = sp.edit().remove(key)


    public fun clear() = sp.edit().clear().apply()

    public fun contains(key: String): Boolean = sp.contains(key)



    /**
     * 伴生对象
     */
    companion object {

        val TAG = "SPUtils"

        public val SP_UTILS_MAP = SimpleArrayMap<String, SPUtils>()


        val INSTANCE: SPUtils
            get() = getInstance("")

        public fun getInstance(spName: String): SPUtils {

            var spName = spName
            if (isSpace(spName)) {
                spName = "storm"

            }

            var spUtil = SP_UTILS_MAP.get(spName)

            if (spUtil == null) {
                spUtil = SPUtils(spName)

                SP_UTILS_MAP.put(spName, spUtil)

            }

            return spUtil
        }




        /**
         *  str is null? and ""?
         */
        fun isSpace(str: String?): Boolean {

            if (str == null) return true

            for (i in str.indices) {

                if (!Character.isWhitespace(str[i])) {

                    return false
                }
            }

            return true

        }
    }


}