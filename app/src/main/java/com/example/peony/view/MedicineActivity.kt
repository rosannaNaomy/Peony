package com.example.peony.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peony.R
import com.example.peony.adapter.RecyclerViewAdapter
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.TempMedData
import com.example.peony.database.entities.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_medicine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MedicineActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    lateinit var viewModel: MedicineActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val user = UserEntity("Ms.User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)


        initViewModel()
        addUser()
        initMainViewModel()
        search()
    }

    private fun search() {
        searchButton.setOnClickListener {
            val searchQuery = enterDescription_editText.text.toString()
            viewModel.makeApiCall(searchQuery)
            enterDescription_editText.setText("")
        }
    }

    private fun addUser(){
        CoroutineScope(Dispatchers.IO).launch { viewModel.insertUser(user)}
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MedicineActivity)

            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MedicineActivity, user)
            adapter = recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
        viewModel.getTempMedsObserver().observe(this, Observer<List<TempMedData>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onAddMedCLickListener(medicationData: MedicationData) {
        lifecycleScope.launch {
            viewModel.insertMed(medicationData)
            Toast.makeText(applicationContext, "Added!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}