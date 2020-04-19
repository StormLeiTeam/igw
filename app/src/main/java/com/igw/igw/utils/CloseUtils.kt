package com.igw.igw.utils

import java.io.Closeable
import java.io.IOException

object CloseUtils {

//
//    fun closeIO(vararg closeables: Closeable) {
//
//        if (closeables == null) return
//
//        for (closeable in closeables) {
//
//            if (closeable != null) {
//                try {
//                    closeable.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//
//            }
//        }
//    }


    fun closeIO(vararg closeables: Closeable?) {

        if (closeables == null) return

        for (closeable in closeables) {

            if (closeable != null) {

                try {

                    closeable.close()
                } catch (e: IOException) {
                    e.printStackTrace()

                }

            }
        }
    }


}