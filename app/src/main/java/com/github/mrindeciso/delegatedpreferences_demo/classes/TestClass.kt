package com.github.mrindeciso.delegatedpreferences_demo.classes

import com.github.mrindeciso.annotated_prefs.annotations.Pref
import com.github.mrindeciso.annotated_prefs.annotations.PrefClass

@PrefClass
interface TestClass {

    @Pref
    val shish: Int

    @Pref
    fun antonio(): Unit

    @Pref
    var coso: String?

}