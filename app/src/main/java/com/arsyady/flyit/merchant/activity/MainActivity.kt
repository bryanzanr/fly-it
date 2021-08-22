package com.arsyady.flyit.merchant.activity

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
//import android.webkit.WebView
//import android.webkit.WebViewClient
import com.arsyady.flyit.merchant.R
import com.arsyady.flyit.merchant.`object`.UtilityObject
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.Toast
//import com.arsyady.flyit.merchant.R.id.level
//import com.arsyady.flyit.merchant.R.id.next_level_button
import com.google.android.gms.ads.MobileAds

import kotlinx.android.synthetic.main.profile_merchant.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import android.graphics.drawable.Drawable
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.InputStream
import java.net.MalformedURLException
import android.os.AsyncTask
import android.widget.ImageView


// Remove the line below after defining your own ad unit ID.
//private const val TOAST_TEXT = "Test ads are being shown. " +
//        "To show live ads, replace the ad unit ID in res/values/strings.xml " +
//        "with your own ad unit ID."
//private const val START_LEVEL = 1

class MainActivity : AppCompatActivity() {

    private var currentLevel: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-2667938700445321~9427081770")

//        Toast.makeText(this@MainActivity, intent.getStringExtra("token"), Toast.LENGTH_LONG).show()
        val token = intent.getStringExtra("token")
        profileRequest(token)
        don.setOnClickListener{
            startActivity(Intent(this@MainActivity, EditActivity::class.java))
        }
//        fab.setOnClickListener{
//            Dexter.withActivity(this)
//                    .withPermission(Manifest.permission.CAMERA)
//                    .withListener(object :PermissionListener{
//                        override fun onPermissionGranted(response: PermissionGrantedResponse?) {
//                            val photo = Intent("android.media.action.IMAGE_CAPTURE")
//                            startActivityForResult(photo, 1)
//                        }
//
//                        override fun onPermissionRationaleShouldBeShown(
//                                permission: PermissionRequest?,
//                                token: PermissionToken?
//                        ) {
//                            Toast.makeText(this@Employee, R.string.app_name, Toast.LENGTH_LONG).show()
//                        }
//
//                        override fun onPermissionDenied(response: PermissionDeniedResponse?) {
//                            Toast.makeText(this@Employee, R.string.action_settings, Toast.LENGTH_LONG).show()
//                        }
//                    })
//                    .check();
//        }
//        profile_picture.setOnClickListener{
//            openImage()
//        }

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
//        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()
    }

    private fun profileRequest(token: String){
        UtilityObject.getAPIService().profileRequest(token)
                .enqueue(object: retrofit2.Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful()){
                            try {
                                val jsonRESULTS = JSONObject(response.body().string());
                                tv_name.setText(jsonRESULTS.getString("name"))
                                tv_id.setText(jsonRESULTS.getString("email"))
                                tv_current_role.setText(jsonRESULTS.getString("merchant_name"))
                                DownloadImageTask(profile_picture).execute(jsonRESULTS.getString("profile_picture"))
                            } catch (e: JSONException) {
                                e.printStackTrace();
                            } catch (e: IOException) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(this@MainActivity, getString(R.string.network), Toast.LENGTH_SHORT).show();
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                })
    }



//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        // Check if the key event was the Back button and if there's history
//        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
//            webview.goBack()
//            showInterstitial()
//            return true
//        }
//        // If it wasn't the Back key or there's no web page history, bubble up to the default
//        // system behavior (probably exit the activity)
//        return super.onKeyDown(keyCode, event)
//    }

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

    // Show the next level and reload the ad to prepare for the level after.
//    private fun goToNextLevel() {
//        level.text = "Level " + (++currentLevel)
//        interstitialAd = newInterstitialAd()
//        loadInterstitial()
//    }
}

private class DownloadImageTask(internal var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val urldisplay = urls[0]
        var mIcon11: Bitmap? = null
        try {
            val `in` = java.net.URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        return mIcon11
    }

    override fun onPostExecute(result: Bitmap) {
        bmImage.setImageBitmap(result)
    }
}
