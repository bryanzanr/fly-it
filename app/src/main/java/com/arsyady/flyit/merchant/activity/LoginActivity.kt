package com.arsyady.flyit.merchant.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arsyady.flyit.merchant.service.ProfileService
import com.arsyady.flyit.merchant.R
import com.arsyady.flyit.merchant.`object`.RetrofitObject
import com.arsyady.flyit.merchant.`object`.UtilityObject.getAPIService
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.Callback
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
//            loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false)
            loginRequest()
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        if (this.getSharedPreferences("flyit", Context.MODE_PRIVATE).getBoolean("login", false)){
            startActivity(Intent(applicationContext, MenuActivity::class.java))
        }

    }

    private fun loginRequest(){
        getAPIService().loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
            .enqueue(object: retrofit2.Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful()){
//                            loading.dismiss();
                        try {
                            val jsonRESULTS = JSONObject(response.body().string());
//                            Toast.makeText(this@LoginActivity, jsonRESULTS.toString(), Toast.LENGTH_LONG).show()
                            if (jsonRESULTS.getString("auth").equals("true")){
                                // Jika login berhasil maka data nama yang ada di response API
                                // akan diparsing ke activity selanjutnya.
                                Toast.makeText(this@LoginActivity, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                val token = jsonRESULTS.getString("token");
//                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                // Shared Pref ini berfungsi untuk menjadi trigger session login
//                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                                intent.putExtra("token", token)
                                startActivity(intent)
//                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                                finish();
                            } else {
                                // Jika login gagal
//                                val error_message = jsonRESULTS.getString("error_msg");
                                val error_message = getString(R.string.invalid)
                                Toast.makeText(this@LoginActivity, error_message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace();
                        } catch (e: IOException) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, getString(R.string.network), Toast.LENGTH_SHORT).show();
//                            loading.dismiss();
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
//                        loading.dismiss();
                }
            })
    }

}