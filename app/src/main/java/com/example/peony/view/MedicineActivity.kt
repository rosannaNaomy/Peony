package com.example.peony.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peony.R
import com.example.peony.adapter.RecyclerViewAdapter
import com.example.peony.database.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_medicine.*


@AndroidEntryPoint
class MedicineActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    lateinit var viewModel: MedicineActivityViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)

        saveButton.setOnClickListener {
            val userEntity = UserEntity(name = enterDescription_editText.text.toString())
            viewModel.insertRecord(userEntity)
            enterDescription_editText.setText("")
        }

        deleteAll_button.setOnClickListener { viewModel.deleteAllUsers() }
        initVM()
        initViewModel()
        initMainViewModel()
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

    private fun initViewModel(){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MedicineActivity)

            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MedicineActivity)
            adapter = recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this, Observer<List<UserEntity>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun onDeleteCLickListener(userEntity: UserEntity) {
        viewModel.deleteUser(userEntity)
    }

}