package com.example.peony.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peony.R
import com.example.peony.database.entities.MedicationData
import kotlinx.android.synthetic.main.display_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_medicine_info.*
import kotlinx.android.synthetic.main.fragment_medicine_info.view.*
import kotlinx.android.synthetic.main.fragment_my_medicine.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MedicineInfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_medicine_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        Log.d("MedicineInfoFrag", arguments?.size().toString())
        val medicationData: MedicationData = args!!.getSerializable("name") as MedicationData
        val title = view.header_textview
        val description = description_textView
        val warning = warning_textView

        title.text = medicationData.opendfda.brand_name[0]
        description.text = medicationData.opendfda.generic_name[0]
        warning.text = medicationData.result[0]?.warnings_and_cautions!![0]

    }

}