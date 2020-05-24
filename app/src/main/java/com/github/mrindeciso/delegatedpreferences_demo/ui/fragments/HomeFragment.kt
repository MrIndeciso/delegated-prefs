package com.github.mrindeciso.delegatedpreferences_demo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mrindeciso.delegatedpreferences_demo.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bttBaseDemo.setOnClickListener {
            findNavController().navigate(R.id.basicDemoFragment)
        }

        bttCryptoDemo.setOnClickListener {
            findNavController().navigate(R.id.cryptoDemoFragment)
        }

        bttGsonDemo.setOnClickListener {
            findNavController().navigate(R.id.gsonDemoFragment)
        }

        bttMoshiDemo.setOnClickListener {
            findNavController().navigate(R.id.moshiDemoFragment)
        }
    }

}