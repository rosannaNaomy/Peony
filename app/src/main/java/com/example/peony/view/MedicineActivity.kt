package com.example.peony.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peony.R
import com.example.peony.adapter.RecyclerViewAdapter
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_medicine.*

//, RecyclerViewAdapter.RowClickListener
@AndroidEntryPoint
class MedicineActivity : AppCompatActivity() {
    lateinit var viewModel: MedicineActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        //val medicationData = MedicationData(userName = enterDescription_editText.text.toString(),"N/A")
       // viewModel.insertUser(userEntity)

//        searchButton.setOnClickListener {
//            //val userEntity = UserEntity(userName = enterDescription_editText.text.toString(),"N/A")
//            val userEntity = UserEntity(userName = enterDescription_editText.text.toString(),"N/A")
//            //viewModel.insertUser(userEntity)
//            enterDescription_editText.setText("")
//        }

     //   deleteAll_button.setOnClickListener { viewModel.deleteAllUsers() }
        //initVM()
        initViewModel()
        initMainViewModel()
    }

//    private fun initVM(){
//        viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
////        viewModel.getRecordsObserver().observe(this, object : Observer<List<MedicationData>>{
////            override fun onChanged(t: List<MedicationData>?) {
////                result_textView.setText("")
////                t?.forEach {
////                    result_textView.append(it.brand_name + "\n")
////                }
////            }
////        })
//    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
        viewModel.makeApiCall("wellbutrin") //pass sample query for api call
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MedicineActivity)

            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()//this@MedicineActivity)
            adapter = recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
       // val viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this, Observer<List<MedicationData>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

//    override fun onDeleteCLickListener(userEntity: UserEntity) {
//        viewModel.deleteUser(userEntity)
//    }

}