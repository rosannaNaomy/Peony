package com.example.peony.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peony.R
import com.example.peony.database.PreferenceListener
import com.example.peony.database.PreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_medicine.*
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(), PreferenceListener {

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameSetup()
    }

    private fun userNameSetup() {
        userProfileName_textview.text = preferencesHelper.getUser()

        edit_Button.setOnClickListener {
            profile_editText.visibility = View.VISIBLE
            save_button.visibility = View.VISIBLE
        }

        save_button.setOnClickListener {
            val name = profile_editText.text.toString()
            preferencesHelper.addUser(name)
            profile_editText.setText("")
            profile_editText.visibility = View.INVISIBLE
            save_button.visibility = View.INVISIBLE
        }
        preferencesHelper.subscribeToOnNameChange(this)
    }

    override fun onDestroyView() {
        preferencesHelper.unsubscribeToOnNameChange()
        super.onDestroyView()
    }

    override fun OnNameChange(name: String?) {
        userProfileName_textview.text = name
    }
}