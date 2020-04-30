package com.xf.utils.encry;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    /**
     * 密钥
     */
    private static final String PASSWORD = "752a9267741035bb92cad9dd64816d2e"; // X5msbJUt63Zw87s0
    /**
     * 模式
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 类别
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法
    /**
     * 类别
     */
    private static final String CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";// 默认的加密算法
    /**
     * 偏移量，必须保证16位
     */
    private static final String IV = "dj6Pz4GyJ1vo428Q";// 默认的加密算法

    /**
     * AES 加密操作
     *
     * @param content
     *            待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encryptEcb(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(PASSWORD));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return new String(Base64.getEncoder().encode(result));// 通过Base64转码返回

        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String str = "Cnl9wKfYYUE6OXz2i3w9sQ==";
        // String ddd = DESUtil.encrypt(str);
        String ddd = decryptEcb(str);
        System.out.println(ddd);
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decryptEcb(String content) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(PASSWORD));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 加密操作
     *
     * @param content
     *            待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encryptCbc(String content) {
        try {
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(PASSWORD), iv);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return new String(Base64.getEncoder().encode(result));// 通过Base64转码返回

        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decryptCbc(String content) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(PASSWORD), iv);

            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     * 
     * @param key
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key.getBytes()));

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}