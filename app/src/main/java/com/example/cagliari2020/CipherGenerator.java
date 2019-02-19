package com.example.cagliari2020;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CipherGenerator {

    public static Cipher getCipher() {
        KeyStore keyStore;
        KeyGenerator keyGenerator;
        Cipher cipher;
        String keyName = "chiave_accesso";
        String keystore="AndroidKeyStore";

        try {
            // 1. recupero di un'istanza del keystore
            keyStore = KeyStore.getInstance(keystore);

            keyStore.load(null);

            // 2. recuperiamo un oggetto KeyGenerator, il generatore di chiavi
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, keystore);

            // 3. parametri di inizializzazione del KeyGenerator
            KeyGenParameterSpec keygenspec= null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keygenspec = new
                        KeyGenParameterSpec.Builder(keyName,
                        KeyProperties.PURPOSE_ENCRYPT |
                                KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                        .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(
                                KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .build();
            }

            // 4. inizializzazione del KeyGenerator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keyGenerator.init(keygenspec);
            }

            // 5. generazione di chiavi
            keyGenerator.generateKey();


        } catch (GeneralSecurityException | IOException e) {

            // comprende NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException
            return null;
        }

        try {
            cipher=Cipher.getInstance ("AES/CBC/PKCS7Padding");
            SecretKey skey = (SecretKey) keyStore.getKey(keyName,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            return cipher;
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

}
