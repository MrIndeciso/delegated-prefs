package com.github.mrindeciso.delegatedprefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.mrindeciso.delegatedprefs.delegates.BoolPref
import com.github.mrindeciso.delegatedprefs.implementations.DelegatedPref
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class GenericInstrumentedTest {

    private class Prefs(ctx: Context) : DelegatedPref(ctx) {
        var boolPref by BoolPref()
    }

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val prefs = Prefs(context)

    @Test
    fun testBooleanPref() {
        val testBool = Random.nextBoolean()
        prefs.boolPref = testBool
        assert(testBool == prefs.boolPref)
    }

}
