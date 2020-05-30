package com.github.mrindeciso.delegatedprefs_moshi

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import com.squareup.moshi.Moshi

inline fun <reified T> DelegatePrefInterface.moshiSerializerOf(
    defaultValue: T,
    moshi: Moshi = Moshi.Builder().build()
): MoshiSerializer<T> {
    return MoshiSerializer(
        moshi.adapter(T::class.java),
        defaultValue
    )
}