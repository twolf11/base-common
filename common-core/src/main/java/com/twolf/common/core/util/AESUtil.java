package com.twolf.common.core.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密工具类
 * @Author twolf
 * @Date 2024/11/13
 */
@Slf4j
public class AESUtil {

    /**
     * 数据加密
     * @param plainText 需要加密的文本
     * @param secretKey 加密秘钥
     * @author twolf
     * @date 2024/11/13 10:33
     **/
    public static byte[] encrypt(String plainText, String secretKey) {
        try {
            //秘钥的字节数组
            byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            //截取秘钥的字节数组当初始化向量
            byte[] iv = getIvBytes(secretKeyBytes);

            //初始化加密对象
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(secretKeyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

            //执行加密
            return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            log.error("加密失败", e);
            throw new SecurityException("加密失败");
        }
    }

    /**
     * 数据解密
     * @param encryptedBytes 加密文本字节数组
     * @param secretKey      秘钥
     * @author twolf
     * @date 2024/11/13 10:14
     **/
    public static byte[] decrypt(byte[] encryptedBytes, String secretKey) {
        try {
            //秘钥的字节数组
            byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            //截取秘钥的字节数组当初始化向量
            byte[] iv = getIvBytes(secretKeyBytes);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(secretKeyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

            return cipher.doFinal(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            log.error("解密失败", e);
            throw new SecurityException("解密失败");
        }
    }

    /**
     * Base64数据加密
     * @param plainText 需要加密的文本
     * @param secretKey 加密秘钥
     * @author twolf
     * @date 2024/11/13 10:08
     **/
    public static String encryptBase64(String plainText, String secretKey) {
        byte[] encrypt = encrypt(plainText, secretKey);
        return Base64.getEncoder().encodeToString(encrypt);
    }

    /**
     * Base64数据解密
     * @param encryptedText 加密文本
     * @param secretKey     秘钥
     * @author twolf
     * @date 2024/11/13 10:14
     **/
    public static String decryptBase64(String encryptedText, String secretKey) {
        byte[] decryptedBytes = decrypt(Base64.getDecoder().decode(encryptedText), secretKey);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Hex数据加密
     * @param plainText 需要加密的文本
     * @param secretKey 加密秘钥
     * @author twolf
     * @date 2024/11/13 10:08
     **/
    public static String encryptHex(String plainText, String secretKey) {
        byte[] encrypt = encrypt(plainText, secretKey);
        return HexUtil.encodeHex(encrypt);
    }

    /**
     * Hex数据解密
     * @param encryptedText 加密文本
     * @param secretKey     秘钥
     * @author twolf
     * @date 2024/11/13 10:14
     **/
    public static String decryptHex(String encryptedText, String secretKey) {
        byte[] decryptedBytes = decrypt(HexUtil.decodeHex(encryptedText), secretKey);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * 获取秘钥的字节-必须为16字节，密钥长度不足16字节，则补充0；如果超出16字节，则截断
     * @param secretKeyBytes 秘钥
     * @author twolf
     * @date 2024/11/13 10:29
     **/
    public static byte[] getIvBytes(byte[] secretKeyBytes) {
        byte[] ivBytes = new byte[16];
        if (secretKeyBytes.length == 16) {
            ivBytes = secretKeyBytes;
        } else if (secretKeyBytes.length > 16) {
            ivBytes = new byte[16];
            System.arraycopy(secretKeyBytes, 0, ivBytes, 0, 16);
        } else {
            byte[] newKey = new byte[16];
            System.arraycopy(secretKeyBytes, 0, ivBytes, 0, secretKeyBytes.length);
            for (int i = secretKeyBytes.length; i < 16; i++) {
                // 填充0
                newKey[i] = 0;
            }
            ivBytes = newKey;
        }
        return ivBytes;
    }

}
