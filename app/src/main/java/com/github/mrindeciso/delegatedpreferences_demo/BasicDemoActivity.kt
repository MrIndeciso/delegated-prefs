package com.github.mrindeciso.delegatedpreferences_demo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mrindeciso.delegatedprefs.delegates.SerializedPref
import com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref
import com.github.mrindeciso.delegatedprefs.interfaces.PreferenceSerializer
import com.github.mrindeciso.delegatedprefs.pref
import kotlinx.android.synthetic.main.activity_basic_demo.*

class BasicDemoActivity : AppCompatActivity(R.layout.activity_basic_demo) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val basicPreference = BasicPreference(this)

        displayInitialData(basicPreference)
        setupListeners(basicPreference)
    }

    private fun displayInitialData(pref: BasicPreference) {
        boolPref.isChecked = pref.bool
        floatPref.setText(pref.float.toString())
        intPref.setText(pref.int.toString())
        longPref.setText(pref.long.toString())
        username.setText(pref.serialized.username)
        password.setText(pref.serialized.password)
        string.setText(pref.string)
        stringSet.setText(pref.stringSet.joinToString(", "))
    }

    private fun setupListeners(pref: BasicPreference) {
        boolPref.setOnCheckedChangeListener { buttonView, isChecked ->
            pref.bool = isChecked
        }
        putFloat.setOnClickListener {
            pref.float = floatPref.text.toString().toFloatOrNull() ?: 0f
        }
        putInt.setOnClickListener {
            pref.int = intPref.text.toString().toIntOrNull() ?: 0
        }
        putLong.setOnClickListener {
            pref.long = longPref.text.toString().toLongOrNull() ?: 0
        }
        putSerialized.setOnClickListener {
            val loginInfo = LoginInfo(username.text.toString(), password.text.toString())
            pref.serialized = loginInfo
        }
        putString.setOnClickListener {
            pref.string = string.text.toString()
        }
        putStringSet.setOnClickListener {
            pref.stringSet = stringSet.text.toString().split(", ").toSet()
        }
    }

    private data class LoginInfo(val username: String, val password: String)

    private class LoginInfoSerializer : PreferenceSerializer<LoginInfo> {
        override fun fromString(string: String?): LoginInfo {
            return if (string != null) {
                val split = string.split(", ")
                LoginInfo(split[0], split[1])
            } else {
                LoginInfo("default", "defaultPsw")
            }
        }

        override fun toString(obj: LoginInfo): String {
            return "${obj.username}, ${obj.password}"
        }
    }

    private class BasicPreference(ctx: Context) : DelegatedPref(ctx) {
        var bool by pref(Boolean::class)
        var float by pref(Float::class)
        var int by pref(Int::class)
        var long by pref(Long::class)
        var serialized: LoginInfo by SerializedPref(LoginInfoSerializer())
        var string by pref(String::class)
        var stringSet by pref(Set::class)
    }

}