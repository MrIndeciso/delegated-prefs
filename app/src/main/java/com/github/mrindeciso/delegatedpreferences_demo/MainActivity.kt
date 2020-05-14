package com.github.mrindeciso.delegatedpreferences_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferenceClass = PreferenceClass(this)

        setAge.setOnClickListener {
            preferenceClass.age = age.text.toString().toIntOrNull() ?: 0
        }
        setKey.setOnClickListener {
            preferenceClass.customKey = customKey.text.toString().toIntOrNull() ?: 1
        }
        setDefaultHigh.setOnClickListener {
            preferenceClass.defaultHigh = defaultHigh.text.toString().toIntOrNull() ?: 1
        }

        age.setText(preferenceClass.age.toString())
        customKey.setText(preferenceClass.customKey.toString())
        defaultHigh.setText(preferenceClass.defaultHigh.toString())
    }

}