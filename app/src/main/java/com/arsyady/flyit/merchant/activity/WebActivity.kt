package com.arsyady.flyit.merchant.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arsyady.flyit.merchant.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class WebActivity: AppCompatActivity() {

    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

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
//        myWebView.loadUrl("http://flyit.arsyady.com:8000/")
        myWebView.loadUrl("https://csui-bot-1.herokuapp.com/hello")
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

}