package com.arsyady.flyit.merchant.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.arsyady.flyit.merchant.R
import com.arsyady.flyit.merchant.`object`.UtilityObject.getUserService
import com.arsyady.flyit.merchant.dto.request.RegisterRequest
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class RegisterActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener{
//            loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false)
            registerRequest()
        }

    }

    private fun registerRequest(){
        getUserService().registerRequest(RegisterRequest(etEmail.getText().toString(),
                etNamaPenjual.text.toString(), etPassword.text.toString(),
                etPasswordRepeat.getText().toString()))
                .enqueue(object: retrofit2.Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful()) {
                            Log.i("debug", "onResponse: BERHASIL");
//                            loading.dismiss();
                            try {
                                val jsonRESULTS = JSONObject(response.body().string());
//                                if (jsonRESULTS.getString("message").equals(getString(R.string.success))) {
                                if (jsonRESULTS.getInt("status").equals(200)){
                                    Toast.makeText(this@RegisterActivity, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    val intent = Intent(this@RegisterActivity, MenuActivity::class.java)
                                    intent.putExtra("token", jsonRESULTS.getInt("status").toString())
                                    startActivity(intent);
                                } else {
//                                    val error_message = jsonRESULTS.getString("error_msg");
                                    val error_message = getString(R.string.failed) + " " + jsonRESULTS
                                    Toast.makeText(this@RegisterActivity, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace();
                            } catch (e: IOException) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(this@RegisterActivity, getString(R.string.network), Toast.LENGTH_SHORT).show();
                            Log.i("debug", "onResponse: GA BERHASIL");
//                            loading.dismiss();
//                        }
                        }
                    }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                            Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                            Toast.makeText(this@RegisterActivity, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        }
                    })
    }

}