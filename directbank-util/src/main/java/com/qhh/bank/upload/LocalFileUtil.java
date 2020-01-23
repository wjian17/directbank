package com.qhh.bank.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

/**
 * Created by wyf on 2017/6/2.
 */
@Service("localFileUtil")
public class LocalFileUtil implements BaseFileUtil {

    private static Logger logger = LoggerFactory.getLogger(LocalFileUtil.class);

    /**
     * 将文件本地保存
     * @param data 数据
     * @param suffix 文件名后缀
     * @return
     */
    public String upload(byte[] data, String suffix) {
        File file;
        OutputStream os = null;
        String fileName = null;
        try {
            // 获取当前系统用户home目录
            String userHome = System.getProperty("user.home");
            String path = userHome + "/images/";
            File parentFile = new File(path);
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            file = new File(parentFile, fileName);
            os = new FileOutputStream(file);
            os.write(data);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("将字节数组写入硬盘出错,出错信息为:" + e.getLocalizedMessage(), e);
        }finally {
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("关闭文件数据流失败", e);
                }
            }
        }
        return fileName;
    }

    /**
     * 文件名
     * @param fileName
     * @return
     */
    public byte[] download(String fileName){
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/images/" + fileName;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        byte[] buff = null;
        try {
            fis = new FileInputStream(filePath);
            bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buff = bos.toByteArray();
        }catch (Exception e){
            logger.error("将本地文件转为二进制异常!异常信息为：" + e.getLocalizedMessage(), e);
        }finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != bos){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buff;
    }

    public byte[] downloadCompress() {
        return new byte[0];
    }

}
