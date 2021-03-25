package yzx.util.sys.res.lib.callbacks

import android.net.Uri


interface SelectSystemImagesCallback {
    fun onResult(uri: Uri?)
}