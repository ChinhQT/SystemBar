package com.imobile.systembar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.imobile.systembar.databinding.ActivityOtherBinding


class OtherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // You can use WindowInsetsControllerCompat API instead of theme.xml to control the status bar content color
        // val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // windowInsetsController.isAppearanceLightNavigationBars = true

//        Following are some points which you need to keep in mind when using this approach.
//
//        When you hide navigation bar (and status bar) using this approach ,
//        if user touch anywhere on screen then navigation bar (and status bar)
//        will reappear and not hide again automatically.User interaction clears
//        those flags.
//        Once flags have cleared by any cause,your app needs to set them again
//        if you want to hide system bars again.
//        If you set those flag in activity's onCreate() method and user tab on
//        home then system bars will appears and not hide when user came back in app.
//        Activity's onCreate() method will not call when user reopen app.You can set those flags in onResume() or in onWindowFocusChanged() when you again want to hide system bar user reopen app.

        binding = ActivityOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.appBarMain.toolbar
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(binding.navView) { view, windowInsets ->

            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updatePadding(0, insets.top, 0, insets.bottom)

            // Return CONSUMED if you don't want want the window insets to keep being
            // passed down to descendant views.
            WindowInsetsCompat.CONSUMED
        }
    }
}