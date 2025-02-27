package sec05.impl;

import sec05.Cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESCipher implements Cipher {
    public byte[] encrypt(byte[] source, byte[] key) {
        SecretKey skey = new SecretKeySpec(key, "AES");
        javax.crypto.Cipher cipher;
        try {
            cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skey);
            return cipher.doFinal(source);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            var ex2 = new RuntimeException();
            ex2.initCause(ex);
            throw ex2;
        }
    }

    public byte[] decrypt(byte[] source, byte[] key) {
        SecretKey skey = new SecretKeySpec(key, "AES");
        javax.crypto.Cipher cipher;
        try {
            cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, skey);
            return cipher.doFinal(source);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            var ex2 = new RuntimeException();
            ex2.initCause(ex);
            throw ex2;
        }
    }

    @Override
    public int strength() {
        return 10;
    }
}
