package com.igw.igw.utils

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
class GsonUtils private constructor() {
    private val gson: Gson
    /**
     * object --> json
     * @param obj 源bean类
     * @return json 字符串
     */
    fun toJson(obj: Any?): String {
        return gson.toJson(obj)
    }

    /**
     * @param json  源数据 json
     * @param clazz 需要转换的bean 类
     * @param <T>
     * @return 返回转换的对象
    </T> */
//    public <T extends Object> T fromJson(String json, Class clazz) {
//        return (T)(gson.fromJson(json, clazz));
//    }
    fun <T> fromJson(json: String?, clazz: Class<*>?): T {
        return gson.fromJson<Any>(json, clazz) as T
    }

    /**
     * json 直接转化为list
     * @param json
     * @param clazz
     * @param <T>
     * @return
    </T> */
    fun <T> fromJsonString2list(json: String?, clazz: Class<*>?): List<T> {
        val type: Type = ParameterizedTypeImpl(clazz!!)
        return gson.fromJson(json, type)
    }

    private inner class ParameterizedTypeImpl(var clazz: Class<*>) : ParameterizedType {
        override fun getActualTypeArguments(): Array<Type> {
            return arrayOf(clazz)
        }

        override fun getRawType(): Type {
            return MutableList::class.java
        }

        override fun getOwnerType(): Type? {
            return null
        }

    }

    companion object {
        @Volatile
        private var INSTANCEJ: GsonUtils? = null

        @JvmStatic
        val instance: GsonUtils
            get() {
                if (INSTANCEJ == null) {
                    synchronized(GsonUtils::class.java) {
                        if (INSTANCEJ == null) {
                            INSTANCEJ = GsonUtils()
                        }
                    }
                }
                return INSTANCEJ!!
            }
    }


    init {
        gson = Gson()
    }
}