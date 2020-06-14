package com.github.mrindeciso.delegatedpreferences_demo.ui.fragments.home

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.github.mrindeciso.delegatedpreferences_demo.R
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.EnumDemoPreference
import com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses.Selection
import kotlinx.android.synthetic.main.fragment_enum_demo.*

class EnumDemoFragment : Fragment(R.layout.fragment_enum_demo) {

    private val greenTint = Color.GREEN
    private val redTint = Color.RED

    override fun onStart() {
        super.onStart()

        val preference = EnumDemoPreference(requireContext())

        displayData(preference)

        bttNorth.setOnClickListener {
            preference.selectedEnumType = Selection.NORTH
            displayData(preference)
        }

        bttSouth.setOnClickListener {
            preference.selectedEnumType = Selection.SOUTH
            displayData(preference)
        }

        bttEast.setOnClickListener {
            preference.selectedEnumType = Selection.EAST
            displayData(preference)
        }

        bttWest.setOnClickListener {
            preference.selectedEnumType = Selection.WEST
            displayData(preference)
        }
    }

    private fun displayData(preference: EnumDemoPreference) {
        bttNorth.setBackgroundColor(redTint)
        bttSouth.setBackgroundColor(redTint)
        bttEast.setBackgroundColor(redTint)
        bttWest.setBackgroundColor(redTint)

        when (preference.selectedEnumType) {
            Selection.NORTH -> bttNorth.setBackgroundColor(greenTint)
            Selection.SOUTH -> bttSouth.setBackgroundColor(greenTint)
            Selection.EAST -> bttEast.setBackgroundColor(greenTint)
            Selection.WEST -> bttWest.setBackgroundColor(greenTint)
        }
    }

}