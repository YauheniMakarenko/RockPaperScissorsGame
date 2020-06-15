package com.task3;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Key {

    public String createSecretKey() {
        byte[]       bytes  = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

        byte[] key = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            key = md.digest(bytes);
            key = Arrays.copyOf(key, 16);
        } catch (NoSuchAlgorithmException e) {
        }
        return bytesToHex(key);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
