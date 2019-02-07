package com.arsyady.flyit.merchant

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.Toast
//import com.arsyady.flyit.merchant.R.id.level
//import com.arsyady.flyit.merchant.R.id.next_level_button
import com.google.android.gms.ads.MobileAds

import kotlinx.android.synthetic.main.activity_main.*

// Remove the line below after defining your own ad unit ID.
//private const val TOAST_TEXT = "Test ads are being shown. " +
//        "To show live ads, replace the ad unit ID in res/values/strings.xml " +
//        "with your own ad unit ID."
//private const val START_LEVEL = 1

class MainActivity : AppCompatActivity() {

    private var currentLevel: Int = 0
    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-2667938700445321~9427081770")

        // Create the next level button, which tries to show an interstitial when clicked.
//        next_level_button.isEnabled = false
//        next_level_button.setOnClickListener { showInterstitial() }

        // Create the text view to show the level number.
//        currentLevel = START_LEVEL

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
//        interstitialAd = newInterstitialAd()
//        loadInterstitial()

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()
        (WebViewClient())
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("http://flyit.arsyady.com:8000/")

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
//        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack()
            showInterstitial()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    // Inflate the menu; this adds items to the action bar if it is present.
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem) =
//            when (item.itemId) {
//                R.id.action_settings -> true
//                else -> super.onOptionsItemSelected(item)
//            }

    private fun newInterstitialAd(): InterstitialAd {
        return InterstitialAd(this).apply {
            adUnitId = getString(R.string.interstitial_ad_unit_id)
            adListener = object : AdListener()
            {
                // Proceed to the next level.
//                override fun onAdLoaded() {
//                    next_level_button.isEnabled = true
//                }
//
//                override fun onAdFailedToLoad(errorCode: Int) {
//                    next_level_button.isEnabled = true
//                }
//
//                override fun onAdClosed() {
//                    goToNextLevel()
//                }
            }
        }
    }

    private fun showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (interstitialAd?.isLoaded == true) {
            interstitialAd?.show()
        }
        else {
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
//            goToNextLevel()
            interstitialAd = newInterstitialAd()
            loadInterstitial()
        }
    }

    private fun loadInterstitial() {
        // Disable the next level button and load the ad.
//        next_level_button.isEnabled = false
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        interstitialAd?.loadAd(adRequest)
    }

    // Show the next level and reload the ad to prepare for the level after.
//    private fun goToNextLevel() {
//        level.text = "Level " + (++currentLevel)
//        interstitialAd = newInterstitialAd()
//        loadInterstitial()
//    }
}
