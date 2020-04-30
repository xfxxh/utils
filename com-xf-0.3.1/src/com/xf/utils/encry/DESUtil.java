package com.xf.utils.encry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * des 加密数据和文件
 * 
 * @author xf_Xxh
 *
 */
public class DESUtil {

    /**
     * 偏移变量，固定占8位字节
     */
    private final static String PASSWORD = "IUlK3t5Q92r2DnbY";

    /**
     * 偏移变量，固定占8位字节
     */
    private final static String IV_PARAMETER = "4DRGTRR5";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final String CHARSET = "utf-8";

    /**
     * 生成key
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static Key generateKey() {
        DESKeySpec dks;
        try {
            dks = new DESKeySpec(PASSWORD.getBytes(CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            return keyFactory.generateSecret(dks);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密字符串
     *
     * @param password
     *            加密密码，长度不能够小于8位
     * @param data
     *            待加密字符串
     * @return 加密后内容
     */
    public static String encrypt(String data) {
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));

            // JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
            // Android平台可以使用android.util.Base64
            return new String(Base64.getEncoder().encode(bytes));

        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES解密字符串
     *
     * @param password
     *            解密密码，长度不能够小于8位
     * @param data
     *            待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String data) {
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES加密文件
     *
     * @param srcFile
     *            待加密的文件
     * @param destFile
     *            加密后存放的文件路径
     * @return 加密后的文件路径
     */
    public static String encryptFile(String srcFile, String destFile) {
        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(), iv);
            is = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
            return destFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cis != null) {
                try {
                    cis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * DES解密文件
     *
     * @param srcFile
     *            已加密的文件
     * @param destFile
     *            解密后存放的文件路径
     * @return 解密后的文件路径
     */
    public static String decryptFile(String srcFile, String destFile) {
        InputStream is = null;
        OutputStream out = null;
        CipherOutputStream cos = null;
        try {
            File file = new File(destFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(), iv);
            is = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            cos = new CipherOutputStream(out, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }
            return destFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cos != null) {
                try {
                    cos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
