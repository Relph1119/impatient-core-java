package sec05.impl;

import sec05.Cipher;

public class CaesarCipher implements Cipher {
    public byte[] encrypt(byte[] source, byte[] key) {
        var result = new byte[source.length];
        for (int i = 0; i < source.length; i++)
            result[i] = (byte) (source[i] + key[0]);
        return result;
    }

    public byte[] decrypt(byte[] source, byte[] key) {
        return encrypt(source, new byte[]{(byte) -key[0]});
    }

    public int strength() {
        return 1;
    }
}