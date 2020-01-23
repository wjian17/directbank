package com.qhh.bank.upload;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wyf on 2017/6/2.
 */
@Service("ftpFileUtil")
public class FTPFileUtil implements BaseFileUtil {

	private static Logger logger = LoggerFactory.getLogger(FTPFileUtil.class);

	private static Map<String,String> hashMap = new HashMap<String,String>();
	/*static {
		Properties properties = new Properties();
		try {
			properties.load(FTPFileUtil.class.getClassLoader().getResourceAsStream("/ftp.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		hashMap.put("host",properties.getProperty("ftp.host"));
		hashMap.put("port",properties.getProperty("ftp.port"));
		hashMap.put("username",properties.getProperty("ftp.username"));
		hashMap.put("password",properties.getProperty("ftp.password"));
		hashMap.put("basePath",properties.getProperty("ftp.basePath"));
	}*/

	/**
	 * Description: 向FTP服务器上传文件
	 *
	 * @param host     FTP服务器hostname
	 * @param port     FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input    输入流
	 * @return 成功返回true，否则返回false
	 */
	public boolean ftpUploadFile(String host, int port, String username, String password, String basePath,
	                             String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			//切换到上传目录
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//设置上传文件的类型为二进制类型
			ftp.setBufferSize(1024);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//上传文件
			ftp.enterLocalPassiveMode();
//			ftp.enterLocalActiveMode();
			Boolean bool = ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			logger.error("将字节数组写入硬盘出错,出错信息为:" + e.getLocalizedMessage(), e);
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("关闭ftp出错",e);
				}
			}
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 *
	 * @param host       FTP服务器hostname
	 * @param port       FTP服务器端口
	 * @param username   FTP登录账号
	 * @param password   FTP登录密码
	 * @param filePath   FTP服务器上的相对路径
	 * @param fileName   要下载的文件名
	 * @return
	 */
	public byte[] ftpDownloadFile(String host, int port, String username, String password, String filePath,
	                               String fileName) {
		byte[] bytes= null;
		InputStream inputStream = null;
		ByteArrayOutputStream swapStream = null;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return bytes;
			}
			ftp.changeWorkingDirectory(filePath);// 转移到FTP服务器目录
			inputStream = ftp.retrieveFileStream(fileName);
			swapStream = new ByteArrayOutputStream();
			if(inputStream!=null) {
				byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据
				int rc = 0;
				while ((rc = inputStream.read(buff, 0, 100)) > 0) {
					swapStream.write(buff, 0, rc);
				}
				bytes = swapStream.toByteArray();
				swapStream.close();
			}
			inputStream.close();
			ftp.logout();
		} catch (IOException e) {
			logger.error("将ftp文件转换为二进制数组出错,出错信息为:" + e.getLocalizedMessage(), e);
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {
					logger.error("ftp连接关闭失败");
					e.printStackTrace();
				}
			}
			if(swapStream!=null){
				try {
					swapStream.close();
				} catch (IOException e) {
					logger.error("swapStream流关闭失败");
					e.printStackTrace();
				}
			}
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("inputStream流关闭失败");
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	public String upload(byte[] data, String suffix) {
		String fileName = null;
		InputStream inputStream = null;
		try {
			/*String host = "192.168.1.21";
			int port = 8087;
			String username = "user";
			String password = "user";
			String basePath = "/";
			String filePath = "";*/
			String host = hashMap.get("port");
			int port = Integer.parseInt(hashMap.get("port"));
			String username = hashMap.get("username");
			String password = hashMap.get("password");
			String basePath = hashMap.get("basePath");
			String filePath = hashMap.get("filePath");
			fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
			inputStream = new ByteArrayInputStream(data);
			this.ftpUploadFile(host, port, username, password, basePath, filePath, fileName, inputStream);
		}catch (Exception e){
			e.printStackTrace();
			logger.error("将ftp上传文件异常!异常信息为：" + e.getLocalizedMessage(), e);
		}finally {
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("关闭文件数据流失败", e);
				}
			}
		}
		return fileName;
	}

	public byte[] download(String fileName) {
		/*String host = "192.168.1.21";
		int port = 8087;
		String username = "user";
		String password = "user";
		String basePath = "/";*/

		String host = hashMap.get("port");
		int port = Integer.parseInt(hashMap.get("port"));
		String username = hashMap.get("username");
		String password = hashMap.get("password");
		String basePath = hashMap.get("basePath");
		byte[] bytes = new byte[0];
		try {
			bytes = this.ftpDownloadFile(host, port, username, password, basePath, fileName);
		} catch (Exception e) {
			logger.error("将ftp下载文件异常!异常信息为：" + e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return bytes;
	}

	public byte[] downloadCompress() {
		return new byte[0];
	}

}
