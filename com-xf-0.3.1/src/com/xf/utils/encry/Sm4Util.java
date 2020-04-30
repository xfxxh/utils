package com.xf.utils.encry;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
 * sm4加解密
 * 
 * @author xf_Xxh
 *
 */
public class Sm4Util {

    /**
     * 加密密钥 16位
     */
    private static final String KEY = "31I85yYv0Af317Fw";

    /**
     * 偏移量 16位
     */
    private static final String IV = "5G6062vX1ZJM324j";
    /**
     * 编码
     */
    private static final String ENCODING = "UTF-8";

    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 生成ECB暗号
     * 
     * @param algorithmName
     * @param mode
     * @param key
     * @return
     * @throws Exception
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
            Key sm4Key = new SecretKeySpec(key, "SM4");
            cipher.init(mode, sm4Key);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 加密
     * 
     * @param hexKey
     * @param paramStr
     * @return
     * @throws Exception
     */
    public static String encryptEcb(String paramStr) {
        String cipherText = "";
        try {
            // 16进制字符串-->byte[]
            byte[] keyData = ByteUtils.fromHexString(KEY);
            // String-->byte[]
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 加密后的数组
            Cipher cipher = generateEcbCipher("SM4/ECB/PKCS5Padding", Cipher.ENCRYPT_MODE, keyData);
            byte[] cipherArray = cipher.doFinal(srcData);
            // byte[]-->hexString
            cipherText = ByteUtils.toHexString(cipherArray);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * 解密
     * 
     * @param hexKey
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decryptEcb(String cipherText) {
        // 用于接收解密后的字符串
        String decryptStr = "";
        try {
            // hexString-->byte[]
            byte[] keyData = ByteUtils.fromHexString(KEY);
            // hexString-->byte[]
            byte[] cipherData = ByteUtils.fromHexString(cipherText);
            // 解密
            Cipher cipher = generateEcbCipher("SM4/ECB/PKCS5Padding", Cipher.DECRYPT_MODE, keyData);
            byte[] srcData = cipher.doFinal(cipherData);
            // byte[]-->String
            decryptStr = new String(srcData, ENCODING);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return decryptStr;
    }

    /**
     * 生成CBC暗号
     * 
     * @param algorithmName
     * @param mode
     * @param key
     * @return
     * @throws Exception
     */
    private static Cipher generateCbcCipher(String algorithmName, int mode, byte[] key) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
            Key sm4Key = new SecretKeySpec(key, "SM4");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(mode, sm4Key, iv);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 加密
     * 
     * @param hexKey
     * @param paramStr
     * @return
     * @throws Exception
     */
    public static String encryptCbc(String paramStr) {
        String cipherText = "";
        try {
            // 16进制字符串-->byte[]
            byte[] keyData = ByteUtils.fromHexString(KEY);
            // String-->byte[]
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 加密后的数组
            Cipher cipher = generateCbcCipher("SM4/CBC/PKCS5Padding", Cipher.ENCRYPT_MODE, keyData);
            byte[] cipherArray = cipher.doFinal(srcData);
            // byte[]-->hexString
            cipherText = ByteUtils.toHexString(cipherArray);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * 解密
     * 
     * @param hexKey
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decryptCbc(String cipherText) {
        // 用于接收解密后的字符串
        String decryptStr = "";
        try {
            // hexString-->byte[]
            byte[] keyData = ByteUtils.fromHexString(KEY);
            // hexString-->byte[]
            byte[] cipherData = ByteUtils.fromHexString(cipherText);
            // 解密
            Cipher cipher = generateCbcCipher("SM4/CBC/PKCS5Padding", Cipher.DECRYPT_MODE, keyData);
            byte[] srcData = cipher.doFinal(cipherData);
            // byte[]-->String
            decryptStr = new String(srcData, ENCODING);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return decryptStr;
    }
}
