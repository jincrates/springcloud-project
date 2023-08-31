package me.jincrates.msa.coffeekiosk.spring.infra.payment.strategy.settlebank;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
        String encrypted = null;

        try {
            byte[] keys = key.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec secretKeySpec = new SecretKeySpec(keys, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encrypts = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
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
