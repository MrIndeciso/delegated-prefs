package com.github.mrindeciso.delegatedpreferences_demo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mrindeciso.delegatedpreferences_demo.R
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.GsonDemoPreference
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.GsonUserData
import kotlinx.android.synthetic.main.fragment_gson_moshi_demo.*

class GsonDemoFragment : Fragment(R.layout.fragment_gson_moshi_demo) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = GsonDemoPreference(requireContext())

        textView.text = prefs.userData.toString()
        username.setText(prefs.userData.username)
        password.setText(prefs.userData.password)
        privacySettings.isChecked = prefs.userData.acceptedPrivacySettings

        button.setOnClickListener {
            prefs.userData = GsonUserData(
                username.text.toString(),
                password.text.toString(),
                privacySettings.isChecked
            )
            textView.text = prefs.userData.toString()
        }
    }

}