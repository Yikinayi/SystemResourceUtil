package yzx.util.sys.res.lib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import yzx.util.sys.res.lib.callbacks.SelectSystemImagesCallback
import java.util.*
import kotlin.collections.HashMap


internal class SystemResourceHolderActivity : AppCompatActivity() {

    companion object {
        /** 存放全部回调 */
        internal val callbacks = HashMap<String, Any>()
    }


    private val mKey by lazy { intent.getStringExtra("key") ?: "" }
    private val mCallback: Any? by lazy { callbacks[mKey] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dispatchCallback(object : CallbackDispatcher {

            override fun onNone() = finish()

            override fun onSystemImage(cb: SelectSystemImagesCallback) {
                kotlin.runCatching { requestSystemImages(this@SystemResourceHolderActivity) }
                    .onFailure { finish(); cb.onResult(null) }
            }

        }, mCallback)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mRequestCode) {
            finish()
            dispatchCallback(object : CallbackDispatcher {
                override fun onNone() = finish()
                override fun onSystemImage(cb: SelectSystemImagesCallback) = cb.onResult(data?.data)
            }, mCallback)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        callbacks.remove(mKey)
    }


    override fun onBackPressed() = Unit

}