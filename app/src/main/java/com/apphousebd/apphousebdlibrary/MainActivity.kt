package com.apphousebd.apphousebdlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apphousebd.apphousebd_bottomsheet.AppHouseBDBottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appHouseBDBottomSheetDialog = AppHouseBDBottomSheetDialog()

        btn.setOnClickListener {
            appHouseBDBottomSheetDialog.showDialog(supportFragmentManager)
        }
    }
}
