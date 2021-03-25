package yzx.util.sys.res.lib

import android.content.Context
import yzx.util.sys.res.lib.callbacks.SelectSystemImagesCallback


object SystemResourceUtil {

    /**
     * 选择系统图片
     */
    fun selectSystemImages(context: Context, resultCallback: SelectSystemImagesCallback) =
        SystemResourceHolderActivity.launchBySelectedImage(context, resultCallback)


}