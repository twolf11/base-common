package com.twolf.common.core.util;

/**
 * HEX工具
 * @Author twolf
 * @Date 2024/11/13
 */
public class HexUtil {

    /**
     * 将字节数组转换为 HEX 编码的字符串
     * @param bytes 字节数组
     * @author twolf
     * @date 2024/11/13 10:52
     **/
    public static String encodeHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // 将字节转换为两个字符的十六进制表示
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    /**
     * 将 HEX 编码的字符串转换为字节数组
     * @param hex hex编码字符
     * @author twolf
     * @date 2024/11/13 10:52
     **/
    public static byte[] decodeHex(String hex) {
        int length = hex.length();
        byte[] bytes = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

}
