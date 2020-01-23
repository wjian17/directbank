package com.qhh.bank.upload;

/**
 * Created by wyf on 2017/6/2.
 */
public interface BaseFileUtil {

    /**
     * 上传图片
     * @param data
     * @param suffix
     * @return
     */
    String upload(byte[] data, String suffix);

    /**
     * 下载原图
     * @param fileName
     * @return
     */
    byte[] download(String fileName);

    /**
     * 下载压缩图片
     * @return
     */
    byte[] downloadCompress();
}
