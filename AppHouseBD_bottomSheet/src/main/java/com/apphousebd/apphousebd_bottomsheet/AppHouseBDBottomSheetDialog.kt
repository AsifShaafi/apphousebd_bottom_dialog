package com.apphousebd.apphousebd_bottomsheet

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.apphousebd_bottom_sheet.view.*

class AppHouseBDBottomSheetDialog : BottomSheetDialogFragment() {

    fun showDialog(fragmentManager: FragmentManager, tag: String = "AppHouseBD_dialog"){
        this.show(fragmentManager, tag)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.apphousebd_bottom_sheet, container, false)

        view.apphousebd_facebook.setOnClickListener {
            openLink("https://facebook.com/apphousebd")
        }

        view.apphousebd_instagram.setOnClickListener {
            openLink("https://www.instagram.com/apphousebd/")
        }

        view.apphousebd_linkedin.setOnClickListener {
            openLink("https://www.linkedin.com/company/apphousebd")
        }

        view.apphousebd_website.setOnClickListener {
            openInAppBrowser(context!!, "https://apphousebd.com")
        }

        view.apphousebd_mail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:apphousebd@gmail.com")
            startActivity(intent)
        }

        return view

    }

    fun openInAppBrowser(context: Context, url: String) {

        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.addDefaultShareMenuItem()
        builder.enableUrlBarHiding()

        val customTabsIntent = builder.build()

        customTabsIntent.intent.putExtra(
                Intent.EXTRA_REFERRER,
                Uri.parse("android-app://" + context.packageName)
        )
        try {

            customTabsIntent.launchUrl(context, Uri.parse(url))
        } catch (e: Exception) {
            if (e.localizedMessage.contains("No Activity found")) {
                Toast.makeText(context, "No app found to open the link", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}