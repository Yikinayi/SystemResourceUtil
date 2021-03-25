package yzx.util.sys.res

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import yzx.util.sys.res.lib.SystemResourceUtil
import yzx.util.sys.res.lib.callbacks.SelectSystemImagesCallback


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.systemImageButton).setOnClickListener {
            SystemResourceUtil.selectSystemImages(this, object : SelectSystemImagesCallback {
                override fun onResult(uri: Uri?) {
                    val image = findViewById<ImageView>(R.id.systemImage)
                    if (uri == null) {
                        image.setImageBitmap(null)
                    } else {
                        image.setImageURI(uri)
                    }
                }
            })
        }

    }


}