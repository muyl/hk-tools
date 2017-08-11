package com.hk.utils;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;


/**
 * User: hk
 * Date: 2017/8/10 下午4:31
 * version: 1.0
 */
public class FTPUtil implements AutoCloseable {

    private FTPClient ftpClient;


    public FTPUtil (String serverIP, int port, String userName, String password) throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(serverIP, port);
        ftpClient.login(userName, password);
        ftpClient.setBufferSize(1024);//设置上传缓存大小
        ftpClient.setControlEncoding("UTF-8");//设置编码
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件类型
    }

    /**
     * 下载ftp文件到本地
     *
     * @param remoteFileName 远程文件
     * @param localFileName  本地文件
     * @return true/false
     */
    public boolean downloadFile(String remoteFileName, String localFileName) throws IOException {
        boolean isSucc;
        File outFileName = new File(localFileName);
        if (ftpClient == null) throw new IOException("ftp server not login");
        try (OutputStream outputStream = new FileOutputStream(outFileName)) {
            isSucc = ftpClient.retrieveFile(remoteFileName, outputStream);
        }
        return isSucc;
    }

    /**
     * 上传文件制定目录
     *
     * @param remoteFileName    远程文件名
     * @param localFile         本地文件[必须带路径]
     * @return true/false
     */
    public boolean uploadFile(String remoteFileName, String localFile) throws IOException{
        boolean isSucc;
        try(InputStream inputStream = new FileInputStream(localFile)){
            if (ftpClient == null) throw new IOException("ftp server not login");
            isSucc = ftpClient.storeFile(remoteFileName,inputStream);
        }
        return isSucc;
    }

    /**
     * 切换目录
     *
     * @param path         目录
     * @throws IOException 异常
     */
    private void changeDirectory(String path) throws IOException {
        ftpClient.changeWorkingDirectory(path);
    }


    /**
     * 自动关闭资源
     */
    @Override
    public void close() throws Exception {
        if (ftpClient != null && ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}
