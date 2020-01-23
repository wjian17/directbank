package com.qhh.bank.images;

import com.grgbanking.biometrics.domains.jni.Image_;
import com.grgbanking.biometrics.util.base64.Base64Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

/**
 * Created by Administrator on 2017/6/15.
 */
public class ImageUtils {

    public static Image_ getImage(String base64) throws Exception{
        Image_ im = new Image_();
        byte[] imByte = Base64Util.decodeBase64(base64);
        byte[] imBGR = null;
        try {
            im=getImagePixel(imByte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return im;
    }

    public static Image_ getImagePixel(byte[] buff) throws Exception{
        ByteArrayInputStream in = null;
        BufferedImage bi = null;
        Image_ im = null;
        try {
            in = new ByteArrayInputStream(buff);    //将buff作为输入流；
            bi = ImageIO.read(in);
            if(null == bi) return null;
            int width = bi.getWidth();
            int height = bi.getHeight();
            byte [] imageBGR=new byte[width*height*3];
            int num=0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = bi.getRGB(x, y); // 下面三行代码将一个数字转换为BGR数字
                    imageBGR[num+2]=(byte)(rgb & 0xff);
                    imageBGR[num+1]=(byte)((rgb & 0xff00) >> 8);
                    imageBGR[num+0]=(byte)((rgb & 0xff0000) >> 16);
                    num += 3;
                }
            }
            im=new Image_();
            im.setRows(height); //高
            im.setCols(width); //宽
            im.setChns(3);
            im.setData(imageBGR);
        }catch (Exception e){
            throw e;
        }finally {
            if(null != in){
                in.close();
            }
        }
        return im;
    }




    public static Image_ getImageBGR(String base64) throws Exception{
        Image_ im = new Image_();
        byte[] imByte = Base64Util.decodeBase64(base64);
        byte[] imBGR = null;
        try {
            im=getImagePixelBGR(imByte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return im;
    }

    public static Image_ getImagePixelBGR(byte[] buff) throws Exception{
        ByteArrayInputStream in = null;
        BufferedImage bi = null;
        Image_ im = null;
        try {
            in = new ByteArrayInputStream(buff);    //将buff作为输入流；
            bi = ImageIO.read(in);
            if(null == bi) return null;
            int width = bi.getWidth();
            int height = bi.getHeight();
            byte [] imageBGR=new byte[width*height*3];
            int num=0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = bi.getRGB(x, y); // 下面三行代码将一个数字转换为BGR数字
                    imageBGR[num+0]=(byte)(rgb & 0xff);
                    imageBGR[num+1]=(byte)((rgb & 0xff00) >> 8);
                    imageBGR[num+2]=(byte)((rgb & 0xff0000) >> 16);
                    num += 3;
                }
            }
            im=new Image_();
            im.setRows(height); //高
            im.setCols(width); //宽
            im.setChns(3);
            im.setData(imageBGR);
        }catch (Exception e){
            throw e;
        }finally {
            if(null != in){
                in.close();
            }
        }
        return im;
    }


}
