package com.github.mrindeciso.delegatedprefs.impl

import android.content.Context
import android.content.SharedPreferences
import com.github.mrindeciso.delegatedprefs.`interface`.DelegatePrefInterface

open class DelegatedPref(private val ctx: Context, private val prefName: String): DelegatePrefInterface {

    override val preferences: SharedPreferences by lazy {
        ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

}