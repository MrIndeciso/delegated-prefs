package com.github.mrindeciso.delegatedpreferences_demo.ui.preferenceClasses

import android.content.Context
import com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref
import com.github.mrindeciso.delegatedprefs_enum.EnumPref
import com.github.mrindeciso.delegatedprefs_enum.enumSerializerOf

class EnumDemoPreference(ctx: Context) : DelegatedPref(ctx) {

    var selectedEnumType by EnumPref(enumSerializerOf(Selection.NORTH))

}

enum class Selection {
    NORTH,
    SOUTH,
    EAST,
    WEST,
}