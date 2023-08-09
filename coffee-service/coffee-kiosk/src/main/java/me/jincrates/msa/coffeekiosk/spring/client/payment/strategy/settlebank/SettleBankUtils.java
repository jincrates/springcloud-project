package me.jincrates.msa.coffeekiosk.spring.client.payment.strategy.settlebank;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SettleBankUtils {

    public static String sha256(String plain) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }
        md.update(plain.getBytes());
        return toHex(md.digest());
    }

    public static String aesEncryptEcb(String key, String plainText) {
        Cipher cipher;
        byte[] keys;
        byte[] encrypts;
        String encrypted = null;

        try {
            keys = key.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec skeySpec = new SecretKeySpec(keys, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            encrypts = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            encrypted = toHex(encrypts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    private static String toHex(byte[] encryptedBytes) {
        StringBuilder builder = new StringBuilder();
        for (byte encryptedByte : encryptedBytes) {
            builder.append(String.format("%02x", encryptedByte));
        }

        return builder.toString();
    }
}
