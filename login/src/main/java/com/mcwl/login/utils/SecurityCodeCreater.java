package com.mcwl.login.utils;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *  功能描述：  用来生成验证码图片
 */
@Service
public class SecurityCodeCreater {

    //图片的宽度
    private final  int IMAGEWIDTH = 15;
    //图片的高度
    private final  int IMAGEHEIGHT = 22;

    //字体大小
    private final  int FONTSIZE = 18;

    //字符串长度
    private final  int CODE_LENGTH = 4;

    //随机字符范围
    private final  char[] CHAR_RANGE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9'
    };
    private  Random random = new Random();

    /**
     * 生成随机字符串
     * @return 随机字符串
     */
    public   String getRandString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++)

            sb.append(CHAR_RANGE[random.nextInt(CHAR_RANGE.length)]);
        return sb.toString();
    }

    /**
     * 生成随机颜色
     * @param ll 产生颜色值下限(lower limit)
     * @param ul 产生颜色值上限(upper limit)
     * @return 生成的随机颜色对象
     */
    private  Color getRandColor(int ll, int ul){
        if (ll > 255) ll = 255;
        if (ll < 1) ll = 1;
        if (ul > 255) ul = 255;
        if (ul < 1) ul = 1;
        if (ul == ll) ul = ll + 1;
        int r = random.nextInt(ul - ll) + ll;
        int g = random.nextInt(ul - ll) + ll;
        int b = random.nextInt(ul - ll) + ll;
        Color color = new Color(r,g,b);
        return color;
    }

    /**
     * 生成指定字符串的图像数据
     * @param verifyCode 即将被打印的随机字符串
     * @return 生成的图像数据
     * */
    public   BufferedImage getImage(String verifyCode){

        //生成画布
        BufferedImage image = new BufferedImage(IMAGEWIDTH * CODE_LENGTH, IMAGEHEIGHT, BufferedImage.TYPE_INT_RGB);

        //获取图形上下文 （生成画笔）
        Graphics graphics = image.getGraphics();

        //设置背景色（）
        graphics.setColor(getRandColor(1,50));

        //填充矩形区域 ，作为背景
        graphics.fillRect(0, 0, IMAGEWIDTH * 4, IMAGEHEIGHT);

        //设置边框颜色
        graphics.setColor(new Color(0,255,0));

        //画出边框
        for (int i=0; i<2; i++)
            graphics.drawRect(i, i, IMAGEWIDTH * CODE_LENGTH - i * 2 - 1, IMAGEHEIGHT - i * 2 - 1);

        //设置随机干扰线条颜色
        graphics.setColor(getRandColor(50,100));

        //产生50条干扰线条
        for (int i=0; i<50; i++){
            int x1 = random.nextInt(IMAGEWIDTH * CODE_LENGTH - 4) + 2;
            int y1 = random.nextInt(IMAGEHEIGHT - 4) + 2;
            int x2 = random.nextInt(IMAGEWIDTH * CODE_LENGTH - 2 - x1) + x1;
            int y2 = y1;
            graphics.drawLine(x1, y1, x2, y2);
        }

        //设置字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, FONTSIZE));

        //画字符串
        for (int i=0; i<CODE_LENGTH; i++){

            String temp = verifyCode.substring(i, i+1);
            graphics.setColor(getRandColor(100,255));
            graphics.drawString(temp, 13 * i + 6, 16);
        }

        //图像生效
        graphics.dispose();

        return image;


    }
}
