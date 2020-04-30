package com.xf.utils.uiUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 验证码生成器
 * 
 * @author xf_Xxh
 *
 */
public class ValidateCodeFun {
    /**
     * 【原始方法】将指定字符串生成指定宽高的BufferedImage
     *
     * @param strCode
     * @param width
     * @param heigh
     * @return
     */
    public static BufferedImage generalImage(String strCode, Integer width, Integer height) {

        BufferedImage vBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = vBufferedImage.getGraphics();
        Random random = new Random();

        g.setColor(generalRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(generalRandColor(160, 200));

        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        char[] arr = strCode.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            String randChar = "" + arr[i]; // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(randChar, 13 * i + 6, 16);
        }
        g.dispose();
        return vBufferedImage;
    }

    /**
     * 将指定字符串生成指定宽高的BufferedImage : 80 * 20
     *
     * @param strCode
     * @return
     */
    public static BufferedImage generalImage(String strCode) {
        return generalImage(strCode, 80, 20);
    }

    // ---------------------------------------------------------
    /**
     * 【原始方法】生成给定范围的随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    public static Color generalRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // ---------------------------------------------------------
    /**
     * BufferedImage转为ImageOutputStream
     *
     * @param pBufferedImage
     * @return
     */
    public static ImageOutputStream toImageOutputStream(BufferedImage pBufferedImage) {
        BufferedImage vBufferedImage = pBufferedImage;
        ImageOutputStream imageOut = null;
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            imageOut = ImageIO.createImageOutputStream(outStream);
            ImageIO.write(vBufferedImage, "JPEG", imageOut);
            imageOut.close();
        } catch (Exception e) {
            System.out.println("toImageOutputStream()时产生错误：" + e.toString());
        }
        return imageOut;
    }

    /**
     * BufferedImage转为ByteArrayInputStream
     *
     * @param pBufferedImage
     * @return
     */
    public static ByteArrayInputStream toByteArrayInputStream(BufferedImage pBufferedImage) {
        BufferedImage vBufferedImage = pBufferedImage;
        ByteArrayInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(outStream);
            ImageIO.write(vBufferedImage, "JPEG", imageOut);
            imageOut.close();
            inStream = new ByteArrayInputStream(outStream.toByteArray());
        } catch (Exception e) {
            System.out.println("图片转换为流对象时产生错误：" + e.toString());
        }
        return inStream;
    }

    // ---------------------------------------------------------
    /**
     * 获取指定字符串的图片输入流：80 * 20
     *
     * @param strCode
     * @return
     */
    public static ByteArrayInputStream getByteArrayInputStream(String strCode) {
        return getByteArrayInputStream(strCode, 80, 20);
    }

    /**
     * 获取指定字符串的图片输入流：指定宽高
     *
     * @param strCode
     * @param width
     * @param height
     * @return
     */
    public static ByteArrayInputStream getByteArrayInputStream(String strCode, Integer width, Integer height) {
        if (width < 1) {
            width = 80;
        }
        if (height < 1) {
            height = 20;
        }
        BufferedImage vBufferedImage = generalImage(strCode, width, height);
        ByteArrayInputStream imageStream = toByteArrayInputStream(vBufferedImage);
        return imageStream;
    }
}
