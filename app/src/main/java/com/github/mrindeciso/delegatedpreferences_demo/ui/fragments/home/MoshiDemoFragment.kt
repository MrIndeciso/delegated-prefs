package com.github.mrindeciso.delegatedpreferences_demo.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mrindeciso.delegatedpreferences_demo.R
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.MoshiDemoPreference
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.MoshiUserData
import kotlinx.android.synthetic.main.fragment_gson_moshi_demo.*

class MoshiDemoFragment : Fragment(R.layout.fragment_gson_moshi_demo) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = MoshiDemoPreference(requireContext())

        textView.text = prefs.userData.toString()
        username.setText(prefs.userData.username)
        password.setText(prefs.userData.password)
        privacySettings.isChecked = prefs.userData.acceptedPrivacySettings

        button.setOnClickListener {
            prefs.userData = MoshiUserData(
                username.text.toString(),
                password.text.toString(),
                privacySettings.isChecked
            )
            textView.text = prefs.userData.toString()
        }
    }

}