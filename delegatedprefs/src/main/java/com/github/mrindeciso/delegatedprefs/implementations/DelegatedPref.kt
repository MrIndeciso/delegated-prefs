package com.github.mrindeciso.delegatedprefs.implementations

import android.content.Context
import android.content.SharedPreferences
import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface

/**
 * The default preference implementation, which uses the normal SharedPreferences
 *
 * @property [ctx] any kind of context
 * @property [prefName] the file name of the generated XML file, without the extension,
 * if null we use the one defined in the base interface
 */
open class DelegatedPref(
    private val ctx: Context,
    private val prefName: String? = null
) : DelegatePrefInterface {

    override val preferences: SharedPreferences by lazy {
        val name = prefName ?: DelegatePrefInterface.DEFAULT_PREFERENCE_NAME
        ctx.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

}