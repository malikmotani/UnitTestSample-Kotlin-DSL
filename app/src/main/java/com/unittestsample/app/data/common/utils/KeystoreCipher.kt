package com.unittestsample.app.data.common.utils

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.security.auth.x500.X500Principal

@Suppress("DEPRECATION")
class KeystoreCipher(context: Context) {

    private val keystore = KeyStore.getInstance(KEYSTORE_TYPE)
    private val rsaCipher = Cipher.getInstance(RSA_TRANSFORMATION)

    private lateinit var aesKey: ByteArray
    private lateinit var aesVectorSpecs: ByteArray

    init {
        keystore.load(null)
        if (!keystore.containsAlias(KEYSTORE_ALIAS_NAME)) {
            val keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM, KEYSTORE_TYPE)

            val spec = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                    KeyGenParameterSpec.Builder(
                        KEYSTORE_ALIAS_NAME,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                    ).run {
                        setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                        setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                        build()
                    }
                }
                else -> {
                    val start = Calendar.getInstance()
                    val end = Calendar.getInstance()
                    end.add(Calendar.YEAR, 30)
                    KeyPairGeneratorSpec.Builder(context).run {
                        setAlias(KEYSTORE_ALIAS_NAME)
                        setSubject(X500Principal("CN=$KEYSTORE_ALIAS_NAME"))
                        setSerialNumber(BigInteger.TEN)
                        setStartDate(start.time)
                        setEndDate(end.time)
                        build()
                    }
                }
            }
            keyPairGenerator.initialize(spec)
            keyPairGenerator.generateKeyPair()
        }
    }

    fun setAesKey(key: String, vectorSpec: String) {
        aesKey = decryptRSA(key)
        aesVectorSpecs = decryptRSA(vectorSpec)
    }

    fun getNewAesKey(): Pair<String, String> {
        val key = ByteArray(AES_BYTE_KEY_SIZE)
        val ivSpec = ByteArray(AES_BYTE_KEY_SIZE)
        SecureRandom().apply {
            nextBytes(key)
            nextBytes(ivSpec)
        }
        aesKey = key
        aesVectorSpecs = ivSpec
        return Pair(encryptRSA(key), encryptRSA(ivSpec))
    }

    private fun getRsaKey() = keystore.getEntry(KEYSTORE_ALIAS_NAME, null) as? KeyStore.PrivateKeyEntry

    private fun encryptRSA(value: ByteArray): String {
        return rsaCipher.apply {
            init(Cipher.ENCRYPT_MODE, getRsaKey()?.certificate?.publicKey)
        }.doFinal(value).encodeToString()
    }

    private fun decryptRSA(value: String): ByteArray {
        return Cipher.getInstance(RSA_TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getRsaKey()?.privateKey)
        }.doFinal(value.decodeToByteArray())
    }

    fun decrypt(encrypted: String): String {
        if (encrypted.isEmpty()) return ""

        val iv = IvParameterSpec(aesVectorSpecs)
        val skeySpec = SecretKeySpec(aesKey, AES_ALGORITHM)

        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)

        val original = cipher.doFinal(encrypted.decodeToByteArray())
        return String(original)
    }

    fun encrypt(value: String): String {
        if (value.isEmpty()) return ""

        val iv = IvParameterSpec(aesVectorSpecs)
        val skeySpec = SecretKeySpec(aesKey, AES_ALGORITHM)

        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)

        val encrypted = cipher.doFinal(value.toByteArray())
        return encrypted.encodeToString()
    }

    companion object {
        private const val KEYSTORE_TYPE = "AndroidKeyStore"
        private const val KEYSTORE_ALIAS_NAME = "AndroidAlias"
        private const val RSA_ALGORITHM = "RSA"
        private const val RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding"
        private const val AES_ALGORITHM = "AES"
        private const val AES_TRANSFORMATION = "AES/CBC/PKCS5Padding"
        private const val AES_BYTE_KEY_SIZE = 16
    }

    private fun String.decodeToByteArray(): ByteArray = Base64.decode(this, Base64.DEFAULT)

    private fun ByteArray.encodeToString(): String = Base64.encodeToString(this, Base64.DEFAULT)
}