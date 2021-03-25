package yzx.util.sys.res.lib

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import yzx.util.sys.res.lib.callbacks.SelectSystemImagesCallback
import java.util.*


/**
 * 发起选择系统图片请求
 */
internal fun requestSystemImages(context: SystemResourceHolderActivity) {
    context.startActivityForResult(Intent(Intent.ACTION_CHOOSER).apply {
        putExtra(Intent.EXTRA_INTENT, Intent(Intent.ACTION_PICK, null).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        })
    }, context.mRequestCode)
}


/**
 * 分发资源类型回调的接口
 */
internal interface CallbackDispatcher {
    fun onSystemImage(cb: SelectSystemImagesCallback)
    fun onNone()
}


/**
 * 判断是哪种资源回调
 */
internal fun SystemResourceHolderActivity.dispatchCallback(dispatcher: CallbackDispatcher, targetCallback: Any?) {
    when (targetCallback) {
        is SelectSystemImagesCallback -> dispatcher.onSystemImage(targetCallback)
        else -> dispatcher.onNone()
    }
}


/**
 * requestCode
 */
internal val SystemResourceHolderActivity.mRequestCode get() = 9988


/**
 * 启动选择系统图片意图的activity
 */
internal fun SystemResourceHolderActivity.Companion.launchBySelectedImage(context: Context, callback: SelectSystemImagesCallback) {
    val uniqueKey = UUID.randomUUID().toString()
    callbacks[uniqueKey] = callback
    context.startActivity(Intent(context, SystemResourceHolderActivity::class.java).apply {
        putExtra("key", uniqueKey)
    })
}










