package com.github.mrindeciso.delegatedpreferences_demo.ui.fragments.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mrindeciso.annotated_prefs_android.AnnotatedPrefs
import com.github.mrindeciso.delegatedpreferences_demo.R
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.AnnotatedPreference
import com.github.mrindeciso.delegatedpreferences_demo.utils.onKeyboardEnter
import kotlinx.android.synthetic.main.fragment_basic_crypto_annotation_demo.*

class AnnotationDemoFragment : Fragment(R.layout.fragment_basic_crypto_annotation_demo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = AnnotatedPrefs.getInstanceOf<AnnotatedPreference>(requireContext())

        /* We load the data first so the onCheckedChangeListener
        doesn't get called at startup for nothing */
        loadData(preferences)
        setupListeners(preferences)
    }

    private fun loadData(preferences: AnnotatedPreference) {
        inputInt.setText(preferences.intValue.toString())
        inputFloat.setText(preferences.floatValue.toString())
        inputLong.setText(preferences.longValue.toString())
        inputString.setText(preferences.stringValue)
        //inputStringSet.setText(preferences.stringSetValue.joinToString(","))
        inputBoolean.isChecked = preferences.boolValue
    }

    private fun setupListeners(preferences: AnnotatedPreference) {
        inputInt.onKeyboardEnter {
            preferences.intValue = it.toIntOrNull() ?: 0
            Toast.makeText(requireContext(), "Registered an Int", Toast.LENGTH_SHORT).show()
        }

        inputFloat.onKeyboardEnter {
            preferences.floatValue = it.toFloatOrNull() ?: 0f
            Toast.makeText(requireContext(), "Registered a Float", Toast.LENGTH_SHORT).show()
        }

        inputLong.onKeyboardEnter {
            preferences.longValue = it.toLongOrNull() ?: 0
            Toast.makeText(requireContext(), "Registered a Long", Toast.LENGTH_SHORT).show()
        }

        inputString.onKeyboardEnter {
            preferences.stringValue = it
            Toast.makeText(requireContext(), "Registered a String", Toast.LENGTH_SHORT).show()
        }

        inputStringSet.onKeyboardEnter {
            //preferences.stringSetValue = it.split(',').toSet()
            Toast.makeText(requireContext(), "Registered a String Set", Toast.LENGTH_SHORT).show()
        }

        inputBoolean.setOnCheckedChangeListener { _, isChecked ->
            preferences.boolValue = isChecked
            Toast.makeText(requireContext(), "Registered a Boolean", Toast.LENGTH_SHORT).show()
        }
    }

}