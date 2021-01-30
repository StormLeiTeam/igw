package com.igw.igw.httpclient;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.igw.igw.utils.LogUtils;

import java.io.File;

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe 下载器管理
 */
public class DownLoadUtils {

    public static final String TAG = "DownLoadUtils";

    public static boolean upDateState = false; // 下载状态

    private DownloadManager mDownLoadManager;

    private Context mContext;
    private String apkName;
    private long downloadId; //
    private String pathStr;

    public DownLoadUtils(Context context, String url, String apkName) {
        this.mContext = context;
        downLoadApk(url, apkName);
        this.apkName = apkName;
    }


    /**
     * 获取待安装apk 的name;
     *
     * @param url
     * @return
     */
    public static String getApkName(String url) {
      return  url.substring(url.lastIndexOf(File.separator) + 1);
//        return url.substring(url.lastIndexOf("\\") + 1);
    }

    /**
     * 开启下载功能
     *
     * @param url
     * @param apkName
     */
    private void downLoadApk(String url, String apkName) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setAllowedOverRoaming(false);
        request.setTitle("e驾照下载");
        request.setDescription("新版本下载中");
        request.setVisibleInDownloadsUi(true);
        // SHEZHIXIAZAILUJING

        File file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName);

        LogUtils.d(TAG, "获取的值 -- file" + file.exists());

        request.setDestinationUri(Uri.fromFile(file));

        pathStr = file.getAbsolutePath();

        // 获取
        if (mDownLoadManager == null) {

            mDownLoadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

            if (mDownLoadManager != null) {
                downloadId = mDownLoadManager.enqueue(request);
                upDateState = true;
            }
        }

        mContext.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkState();
        }
    };

    private void checkState() {

        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor cursor = mDownLoadManager.query(query);

        if (cursor.moveToFirst()) {
            int state = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));

            switch (state) {
                case DownloadManager.STATUS_PAUSED:
                    break;
                case DownloadManager.STATUS_PENDING:
                    break;
                case DownloadManager.STATUS_RUNNING:
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:

                    installApk();
                    cursor.close();
                    break;
                case DownloadManager.STATUS_FAILED:
//                    ToastUtils.showShort(mContext, "下载失败");

                    Toast.makeText(mContext,"下载更新",Toast.LENGTH_LONG).show();
                    upDateState = false;
                    unRegisterReceiver();
                    break;
            }

        } else {
            upDateState = false;
            unRegisterReceiver();
        }

    }

    private void installApk() {


        //setPermission(pathStr);

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File file = new File(pathStr);
        if (file.exists()) {
            if (Build.VERSION.SDK_INT >= 24) {


                Uri apkUrl = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", file);

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUrl, "application/vnd.android.package-archive");

            } else {
                intent.setDataAndType(Uri.fromFile(new File(Environment.DIRECTORY_DOWNLOADS, apkName)), "application/vnd.android.package-archive");
            }
            upDateState = false;
            unRegisterReceiver();
            mContext.startActivity(intent);
        }

    }

    private void setPermission(String pathStr) {

    }

    /**
     * 解除注册
     */
    public void unRegisterReceiver() {
        if (receiver != null) {
            mContext.unregisterReceiver(receiver);
            receiver = null;
        }
    }

}
