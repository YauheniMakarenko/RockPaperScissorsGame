package com.task3;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {

    private String key;
    private String value;
    private String shaType;

    public String generateHmac() {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), shaType);
            Mac mac = Mac.getInstance(shaType);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(value.getBytes("UTF-8"));
            byte[] hexArray = {(byte) '0', (byte) '1', (byte) '2', (byte) '3',
                    (byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8',
                    (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'};
            byte[] hexChars = new byte[rawHmac.length * 2];
            for (int j = 0; j < rawHmac.length; j++) {
                int v = rawHmac[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static class Builder{
        private HMAC hmac;

        public Builder() {
            hmac = new HMAC();
        }

        public Builder setKey(String key){
            hmac.key = key;
            return this;
        }

        public Builder setValue(String value){
            hmac.value = value;
            return this;
        }

        public Builder setShaType(String SHA_TYPE){
            hmac.shaType = SHA_TYPE;
            return this;
        }

        public HMAC build(){
            return hmac;
        }

    }
}
