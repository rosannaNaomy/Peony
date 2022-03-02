package com.example.peony.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.peony.R
import com.example.peony.database.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_medicine.*


@AndroidEntryPoint
class MedicineActivity : AppCompatActivity() {
    lateinit var viewModel: MedicineActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        saveButton.setOnClickListener {
            val userEntity = UserEntity(name = enterDescription_editText.text.toString())
            enterDescription_editText.setText("")
        }
        initVM()
    }

    private fun initVM(){
        viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this, object : Observer<List<UserEntity>>{
            override fun onChanged(t: List<UserEntity>?) {
                result_textView.setText("")
                t?.forEach {
                    result_textView.append(it.name + "\n")
                }
            }
        })
    }
}