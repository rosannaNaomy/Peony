package com.example.peony.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peony.R
import com.example.peony.adapter.MyMedRecyclerAdapter
import com.example.peony.adapter.RecyclerViewAdapter
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity
import com.example.peony.view.MainActivity
import com.example.peony.view.MedicineActivity
import com.example.peony.view.MedicineActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_medicine.*
import kotlinx.android.synthetic.main.fragment_my_medicine.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyMedicineFragment : Fragment(), MyMedRecyclerAdapter.RowClickListener {

    lateinit var viewModel: MedicineActivityViewModel
    private lateinit var recyclerViewAdapter: MyMedRecyclerAdapter
    private val user = UserEntity("Ms.User")
    private var fragmentCallbacklistener: FragmentCallback? = null
    private val medicineInfoFragment = MedicineInfoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        if (context is FragmentCallback) {
            fragmentCallbacklistener = context
        } else {
            throw RuntimeException(requireContext().toString() + " must implement OnFragmentInteractionListener")
        }
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_medicine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userAddMedicine()
        initViewModel()
        initMainViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MedicineActivityViewModel::class.java)
        myMedicine_recyclerView.apply{
            layoutManager = LinearLayoutManager(activity)

            val decoration = DividerItemDecoration(requireActivity().applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = MyMedRecyclerAdapter(this@MyMedicineFragment, user)
            adapter = recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
        viewModel.getRecordsObserver().observe(requireActivity(), Observer<List<MedicationData>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private fun userAddMedicine() {
        addMedicine_button.setOnClickListener {
            val intent = Intent(activity, MedicineActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun itemIntereact(medicationData: MedicationData) {
        fragmentCallbacklistener!!.passData(medicineInfoFragment, medicationData)
    }


}