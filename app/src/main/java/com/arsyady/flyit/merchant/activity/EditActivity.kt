package com.arsyady.flyit.merchant.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import com.arsyady.flyit.merchant.R
import com.arsyady.flyit.merchant.`object`.UtilityObject
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.profile_merchant.*
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class EditActivity : AppCompatActivity() {

    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var numberPickerPriority: NumberPicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

//        editTextTitle = findViewById(R.id.edit_text_title)
//        editTextDescription = findViewById(R.id.edit_text_description)
//        numberPickerPriority = findViewById(R.id.number_picker_priority)
//
//        numberPickerPriority!!.minValue = 1
//        numberPickerPriority!!.maxValue = 10
//
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px)
//
//        val intent = intent
//
//        if (intent.hasExtra(EXTRA_ID)) {
//            title = "Edit Note"
//            editTextTitle!!.setText(intent.getStringExtra(EXTRA_TITLE))
//            editTextDescription!!.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
//            numberPickerPriority!!.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
//        } else {
//            title = "Add Note"
//        }

    }

    private fun editRequest(token: String, profpic: RequestBody){
        UtilityObject.getAPIService().editRequest(edit_text_title.getText().toString(),
                edit_text_description.getText().toString(), edit_text_merchant.getText().toString(),
                token, profpic)
                .enqueue(object: retrofit2.Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful()){
                            try {
                                startActivity(Intent(this@EditActivity, MainActivity::class.java))
                            } catch (e: JSONException) {
                                e.printStackTrace();
                            } catch (e: IOException) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(this@EditActivity, getString(R.string.network), Toast.LENGTH_SHORT).show();
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                })
    }
//
//    private fun saveNote() {
//        val title = editTextTitle!!.text.toString()
//        val description = editTextDescription!!.text.toString()
//        val priority = numberPickerPriority!!.value
//
//        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
//            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val data = Intent()
//        data.putExtra(EXTRA_TITLE, title)
//        data.putExtra(EXTRA_DESCRIPTION, description)
//        data.putExtra(EXTRA_PRIORITY, priority)
//
//        val id = intent.getLongExtra(EXTRA_ID, -1L)
//        //        Toast.makeText(this, id + "detail", Toast.LENGTH_SHORT).show();
//        if (id != -1L) {
//            data.putExtra(EXTRA_ID, id)
//        }
//
//        setResult(Activity.RESULT_OK, data)
//        finish()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
//                editRequest()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

//    companion object {
//        val EXTRA_ID = "com.codinginflow.architectureexample.EXTRA_ID"
//        val EXTRA_TITLE = "com.codinginflow.architectureexample.EXTRA_TITLE"
//        val EXTRA_DESCRIPTION = "com.codinginflow.architectureexample.EXTRA_DESCRIPTION"
//        val EXTRA_PRIORITY = "com.codinginflow.architectureexample.EXTRA_PRIORITY"
//    }
}
