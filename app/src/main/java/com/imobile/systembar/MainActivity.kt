package com.imobile.systembar

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import com.imobile.systembar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // You can use WindowInsetsControllerCompat API instead of theme.xml to control the status bar content color
        // val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // windowInsetsController.isAppearanceLightNavigationBars = true

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->

            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            Log.e("insets.left", insets.left.toString())
            Log.e("insets.top", insets.top.toString())
            Log.e("insets.right", insets.right.toString())
            Log.e("insets.bottom", insets.bottom.toString())

            // Apply the insets as a margin to the view. Here the system is setting
            // only the bottom, left, and right dimensions, but apply whichever insets are
            // appropriate to your layout. You can also update the view padding
            // if that's more appropriate.

//            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//                topMargin = insets.top
//                leftMargin = insets.left
//                bottomMargin = insets.bottom
//                rightMargin = insets.right
//            }

            view.updatePadding(0, insets.top, 0, insets.bottom)

            // Return CONSUMED if you don't want want the window insets to keep being
            // passed down to descendant views.
            WindowInsetsCompat.CONSUMED
        }

//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
//
//            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
//
//            Log.e("insets.left", insets.left.toString())
//            Log.e("insets.top", insets.top.toString())
//            Log.e("insets.right", insets.right.toString())
//            Log.e("insets.bottom", insets.bottom.toString())
//
//            // Apply the insets as padding to the view. Here we're setting all of the
//            // dimensions, but apply as appropriate to your layout. You could also
//            // update the views margin if more appropriate.
//            view.updatePadding(insets.left, insets.top, insets.right, insets.bottom)
//
//            // Return CONSUMED if we don't want the window insets to keep being passed
//            // down to descendant views.
//            WindowInsetsCompat.CONSUMED
//        }

//        val decorView = WindowCompat.getInsetsController(window, window.decorView)
//        // Hide the system bars.
//        decorView.hide(WindowInsetsCompat.Type.systemBars())

//        setFullscreen()

//        makeStatusBarTransparent()
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(
            window, binding.root
        ).show(WindowInsetsCompat.Type.systemBars())
    }

    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }

    fun showFirstFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.containerLayout, FirstFragment.newInstance(), FirstFragment.TAG)
            .addToBackStack(null).commit()
    }

    fun showSecondFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.containerLayout, SecondFragment.newInstance(), SecondFragment.TAG)
            .addToBackStack(null).commit()
    }
}