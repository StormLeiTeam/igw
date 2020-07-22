package com.igw.igw.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
object GlideUtils {

    val TAG = "GlideUtils"
    var options: RequestOptions? = null


    fun loadImageNormal(context: Context, url: String,  normalImg : Int, imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .apply(getRequestOptions(normalImg, normalImg))
                .into(imageView)

    }


    fun loadImage(context: Context, url: String, imageView: ImageView) {

        Glide.with(context).load(url)
//                .apply(getRequestOptions())
                .into(imageView)
    }


    fun loadImage(context: Context, resId: Int, imageView: ImageView) {

        Glide.with(context).load(resId).into(imageView)

    }

    private fun getRequestOptions(placeHolder: Int, error: Int): RequestOptions {

        options = RequestOptions().placeholder(placeHolder).error(error)

        return options!!

    }

    fun loadLocalImage(context  : Context , url : String,imageView: ImageView){

        Glide.with(context)
                .load(Uri.fromFile(File(url)))
                .into(imageView)
    }


}