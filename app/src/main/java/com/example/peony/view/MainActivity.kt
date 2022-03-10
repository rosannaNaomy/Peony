package com.example.peony.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.peony.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addRecord_button.setOnClickListener {
            val intent = Intent(this, MedicineActivity::class.java)
            startActivity(intent)
        }

        setUpTabBar()
    }

    private fun setUpTabBar() {
        bottomnav.setOnItemSelectedListener {
            when(it){
                R.id.nav_home -> Toast.makeText(this,"Clicked on HOME", Toast.LENGTH_SHORT).show()
                R.id.nav_medication -> Toast.makeText(this,"Clicked on MEDICATION", Toast.LENGTH_SHORT).show()
                R.id.nav_profile -> Toast.makeText(this,"Clicked on PROFILE", Toast.LENGTH_SHORT).show()
                R.id.nav_stats -> Toast.makeText(this,"Clicked on STATS", Toast.LENGTH_SHORT).show()
            }
        }
    }
}