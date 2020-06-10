package com.task3;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Key {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public String createSecretKey() {
        byte[]       bytes  = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        byte[] key = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            key = md.digest(bytes);
            key = Arrays.copyOf(key, 16);
        } catch (NoSuchAlgorithmException e) {
        }
        return bytesToHex(key);
    }

    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
