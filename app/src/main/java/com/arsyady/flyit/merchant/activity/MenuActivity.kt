package com.arsyady.flyit.merchant.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.widget.GridLayout
import android.widget.Toast
import com.arsyady.flyit.merchant.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(){

//    lateinit var mainGrid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

//        val mainGrid = findViewById<View>(R.id.mainGrid) as GridLayout

        //Set Event
        setSingleEvent(mainGrid)
        //setToggleEvent(mainGrid);
    }

    private fun setToggleEvent(mainGrid: GridLayout) {
        //Loop all child item of Main Grid
        for (i in 0 until mainGrid.childCount) {
            //You can see , all child item is CardView , so we just cast object to CardView
            val cardView = mainGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                if (cardView.cardBackgroundColor.defaultColor == -1) {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"))
                    Toast.makeText(this@MenuActivity, "State : True", Toast.LENGTH_SHORT).show()

                } else {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                    Toast.makeText(this@MenuActivity, "State : False", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setSingleEvent(mainGrid: GridLayout) {
        //Loop all child item of Main Grid
        for (i in 0 until mainGrid.childCount) {
            //You can see , all child item is CardView , so we just cast object to CardView
            val cardView = mainGrid.getChildAt(i) as CardView
            cardView.setOnClickListener {
                val intent = Intent(this@MenuActivity, MainActivity::class.java)
                intent.putExtra("info", "This is activity from card item index  $i")
                startActivity(intent)
            }
        }
    }

}