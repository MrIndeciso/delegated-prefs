package com.github.mrindeciso.delegatedprefs_crypto

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.github.mrindeciso.delegatedprefs.interfaces.DelegatePrefInterface

/**
 * An implementation of [DelegatePrefInterface] which encrypts every key-value pair
 *
 * @property [ctx] the [Context]
 * @property [preferenceName] the name of the generated preferences file without the extension
 * @property [keyGenParameterSpec] the specifications for the key generator
 * @property [keyEncryptionScheme] the encryption method used for the key inside the pair
 * @property [valueEncryptionScheme] the encryption method used for the value inside the pair
 * @constructor the default constructor
 */
abstract class EncryptedPref(
    private val ctx: Context,
    private val preferenceName: String,
    private val keyGenParameterSpec: KeyGenParameterSpec,
    private val keyEncryptionScheme: EncryptedSharedPreferences.PrefKeyEncryptionScheme,
    private val valueEncryptionScheme: EncryptedSharedPreferences.PrefValueEncryptionScheme
) : DelegatePrefInterface {

    /**
     * The most frequently used constructor, very similar to the non-encrypted implementation
     *
     * @property [ctx] the [Context]
     * @property [preferenceName] the name of the generated preferences file without the extension]
     * @constructor a two-parameters constructor
     */
    constructor(
        ctx: Context,
        preferenceName: String? = null
    ) : this(
        ctx,
        preferenceName ?: ENCRYPTED_PREFERENCE_FILE_NAME,
        MasterKeys.AES256_GCM_SPEC,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override val preferences: SharedPreferences = EncryptedSharedPreferences.create(
        preferenceName,
        MasterKeys.getOrCreate(keyGenParameterSpec),
        ctx,
        keyEncryptionScheme,
        valueEncryptionScheme
    )

    companion object {

        const val ENCRYPTED_PREFERENCE_FILE_NAME = "encryptedPrefs"

    }

}