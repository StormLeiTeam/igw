package com.igw.igw.modoule.login.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.igw.igw.R
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.common_status_bar.*
import java.lang.Exception


/**
 * 欢迎页面的web页面
 */
class WebActivity : AppCompatActivity() {


    companion object {

        val TAG = "WebActivity"


        fun startSelf(activity: Activity, link: String) {


            val intent = Intent(activity, WebActivity::class.java)
            intent.putExtra("link", link)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_web)


        StatusBarUtils.setTransparentForWindowBar(this)
        StatusBarUtils.setDarkMode(this)


        status_bar_main.setTitle("")
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(visible = View.GONE)
        status_bar_main.setConfirmText("中/en")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)
        initWeb()

    }

    private fun initWeb() {

        val settings = wb_web.settings
        settings.javaScriptEnabled = true

        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true

        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true

        settings.loadsImagesAutomatically = true
        settings.defaultTextEncodingName = "utf-8"


        var link = intent.getStringExtra("link")


        wb_web.loadUrl("http://www.baidu.com")



        wb_web.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                try {

                    url?.let {
                        if (it.startsWith("http:") || it.startsWith("https:")) {
                            view?.loadUrl(it)

                        } else {

                            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))

                            startActivity(intent)
                        }

                    }

                    return true


                } catch (e: Exception) {

                    return false
                }


            }

        }

//        webView.setWebViewClient(object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
//                //使用WebView加载显示url
//                view.loadUrl(url)
//                //返回true
//                return true
//            }
//        })
    }
}