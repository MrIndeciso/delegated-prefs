package com.github.mrindeciso.annotated_prefs_android

import android.content.Context

object AnnotatedPrefs {

    inline fun <reified T> getInstanceOf(ctx: Context): T {
        return Class.forName(T::class.java.name + "Impl").getConstructor(Context::class.java)
            .newInstance(ctx) as T
    }

}