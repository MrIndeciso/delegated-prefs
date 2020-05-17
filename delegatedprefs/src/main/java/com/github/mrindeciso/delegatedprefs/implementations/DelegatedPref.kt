package com.github.mrindeciso.delegatedprefs.implementations

import android.content.Context
import android.content.SharedPreferences
import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface

/**
 * The default preference implementation, which uses the normal [SharedPreferences]
 *
 * @property [prefs] the [SharedPreferences] interface
 * @constructor passes the constructor to the preferences property
 */
abstract class DelegatedPref(private val prefs: SharedPreferences) : DelegatePrefInterface {

    /**
     * The main constructor and probably the most frequently used one
     * @property [ctx] any kind of context
     * @property [prefName] the file name of the generated XML file, without the extension,
     * if null we use the one defined in the base interface
     * @constructor creates an instance of SharedPreferences from the Context
     */
    constructor(
        ctx: Context,
        prefName: String? = null
    ) : this(
        ctx.getSharedPreferences(
            prefName ?: DelegatePrefInterface.DEFAULT_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    )

    override val preferences: SharedPreferences = prefs

}