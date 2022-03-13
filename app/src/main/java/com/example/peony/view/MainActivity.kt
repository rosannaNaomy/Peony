package com.example.peony.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.peony.R
import com.example.peony.database.entities.MedicationData
import com.example.peony.view.fragments.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentCallback{

    private val homeFragment = HomeFragment()
    private val myMedicineFragment = MyMedicineFragment()
    private val insightsFragment = InsightsFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(homeFragment)
        setUpTabBar()
    }

    private fun setUpTabBar() {
        bottomnav.setOnItemSelectedListener {
            when(it){
                R.id.nav_home -> replaceFragment(homeFragment)
                R.id.nav_medication -> replaceFragment(myMedicineFragment)
                R.id.nav_profile -> replaceFragment(profileFragment)
                R.id.nav_stats -> replaceFragment(insightsFragment)
            }
        }
    }

    override fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragmentContainer, fragment)
        transaction.commit()
    }

    override fun passData(fragment: Fragment, medicationData: MedicationData) {
        val bundle = Bundle()
        bundle.putParcelable("data", medicationData)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragmentContainer, fragment)
        transaction.commit()
    }
}