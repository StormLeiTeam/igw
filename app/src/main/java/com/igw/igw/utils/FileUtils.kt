package com.igw.igw.utils

import android.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.util.zip.GZIPOutputStream


/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
object FileUtils{


    public fun File2bytes(file: File?): ByteArray? {

        if (file == null || !file.exists()){

            throw NullPointerException("this file is null or not exists")
        }

        var data : ByteArray? = null

        var fis : FileInputStream? = null
        var bos: ByteArrayOutputStream? = null

        try {
            fis = FileInputStream(file)
            bos = ByteArrayOutputStream(file.length().toInt())


            var buffer : ByteArray= ByteArray(1024)

            var len : Int  = -1



            while (fis.read(buffer).apply { len = this } != -1){

               bos.write(buffer,0,len)
            }

            data = bos.toByteArray()
        }catch (e:Exception){

            e.printStackTrace()
        }finally {
            CloseUtils.closeIO(fis,bos)

        }

        return data

    }


    fun bytes2String(bytes: ByteArray): String? {
        var dataString: String? = null

        try {
            dataString = String(bytes, charset("utf-8"))

        }catch (e:Exception){
            e.printStackTrace()
        }

        return dataString
    }


    fun compress(data:String): String? {
        var finalData: String? = null

        try {

            //打开压缩用的输出流,压缩后的结果放在bout中
            //打开压缩用的输出流,压缩后的结果放在bout中
//            val gout: GZIPOutpuStream = GZIPOutputStream(bout)
//            //写入待压缩的字节数组
//            //写入待压缩的字节数组
//            gout.write(R.attr.data.getBytes("ISO-8859-1"))
//            //完成压缩写入
//            //完成压缩写入
//            gout.finish()
//            //关闭输出流
//            //关闭输出流
//            gout.close()
//            finalData = bout.toString("ISO-8859-1")



            var bos = ByteArrayOutputStream()
            var gzs = GZIPOutputStream(bos)


            gzs.write(data.toByteArray(charset("UTF-8")))

            gzs.finish()

            CloseUtils.closeIO(gzs)
            finalData = gzs.toString()
        }catch (e:Exception){
            e.printStackTrace()
        }

        return finalData


    }


}