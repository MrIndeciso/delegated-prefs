package com.github.mrindeciso.delegatedpreferences_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bttBasicDemo.setOnClickListener {
            Intent(this, BasicDemoActivity::class.java).let {
                startActivity(it)
            }
        }
    }

}