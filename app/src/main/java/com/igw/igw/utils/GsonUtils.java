package com.igw.igw.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Observable;


/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
public class GsonUtils {

    private static volatile GsonUtils INSTANCEJ;
    private Gson gson;

    private GsonUtils() {
        gson = new Gson();
    }


    public static GsonUtils getInstance() {

        if (INSTANCEJ == null) {
            synchronized (GsonUtils.class) {
                if (INSTANCEJ == null) {
                    INSTANCEJ = new GsonUtils();
                }
            }
        }


        return INSTANCEJ;
    }


    /**
     *  object --> json
     * @param obj 源bean类
     * @return json 字符串
     */
    public String toJson(Object obj) {
        return gson.toJson(obj);
    }


    /**
     * @param json  源数据 json
     * @param clazz 需要转换的bean 类
     * @param <T>
     * @return 返回转换的对象
     */
//    public <T extends Object> T fromJson(String json, Class clazz) {
//        return (T)(gson.fromJson(json, clazz));
//    }
    public <T> T fromJson(String json, Class clazz) {

        return (T) (gson.fromJson(json, clazz));

    }


    /**
     * json 直接转化为list
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> fromJsonString2list(String json, Class clazz) {

        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list  = gson.fromJson(json, type);
        return list;
    }




    private class ParameterizedTypeImpl implements  ParameterizedType{

        Class clazz;


        public ParameterizedTypeImpl(Class clazz) {
            this.clazz = clazz;
        }

        @NonNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz} ;
        }

        @NonNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Nullable
        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
