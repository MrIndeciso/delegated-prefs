package com.github.mrindeciso.delegatedprefs_gson

import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> DelegatePrefInterface.gsonTypeOf(): Type {
    return object : TypeToken<T>() {}.type
}

inline fun <reified T> DelegatePrefInterface.gsonSerializerOf(defaultValue: T): GsonSerializer<T> {
    return GsonSerializer(
        gsonTypeOf<T>(),
        defaultValue
    )
}