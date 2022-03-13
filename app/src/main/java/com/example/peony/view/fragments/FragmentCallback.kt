package com.example.peony.view.fragments

import androidx.fragment.app.Fragment
import com.example.peony.database.entities.MedicationData

interface FragmentCallback {
    fun replaceFragment(fragment: Fragment)
    fun passData(fragment: Fragment, medicationData: MedicationData)
}