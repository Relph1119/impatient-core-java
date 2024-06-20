package sec05.impl;

import sec05.Cipher;

public class AESCipherFactory {
    public static Cipher provider() { return new AESCipher(); }
}
