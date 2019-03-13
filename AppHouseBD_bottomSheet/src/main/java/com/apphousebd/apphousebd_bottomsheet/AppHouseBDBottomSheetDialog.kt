/*
 * Copyright (c) 2018-2019 AppHouseBD. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apphousebd.apphousebd_bottomsheet

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.apphousebd_bottom_sheet.view.*


class AppHouseBDBottomSheetDialog : BottomSheetDialogFragment() {

    fun showDialog(fragmentManager: FragmentManager, tag: String = "AppHouseBD_dialog"){
        this.show(fragmentManager, tag)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.apphousebd_bottom_sheet, container, false)
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val dialog = dialog as BottomSheetDialog?
            val bottomSheet = dialog!!.findViewById(R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 0
        }

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

    private fun openInAppBrowser(context: Context, url: String) {

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