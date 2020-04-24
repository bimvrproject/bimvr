package com.jhbim.bimvr.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.util.TimeZone;

import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTPUtils {
    private FTPClient ftpClient;
    private String strIp;
    private int intPort;
    private String user;
    private String password;

    private static Logger logger =  LoggerFactory.getLogger(FTPUtils.class.getName());

    /* *
     * Ftp构造函数
     */
    public FTPUtils(String strIp, int intPort, String user, String Password) {
        this.strIp = strIp;
        this.intPort = intPort;
        this.user = user;
        this.password = Password;
        this.ftpClient = new FTPClient();
    }
    /**
     * @return 判断是否登入成功
     * */
    public boolean ftpLogin() {
        boolean isLogin = false;
        FTPClientConfig ftpClientConfig = new FTPClientConfig();
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
        this.ftpClient.setControlEncoding("GBK");
        this.ftpClient.configure(ftpClientConfig);
        try {
            if (this.intPort > 0) {
                this.ftpClient.connect(this.strIp, this.intPort);
            }else {
                this.ftpClient.connect(this.strIp);
            }
            // FTP服务器连接回答
            int reply = this.ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftpClient.disconnect();
                logger.error("登录FTP服务失败！");
                return isLogin;
            }
            this.ftpClient.login(this.user, this.password);
            // 设置传输协议
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            logger.info("恭喜" + this.user + "成功登陆FTP服务器");
            isLogin = true;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(this.user + "登录FTP服务失败！" + e.getMessage());
        }
        this.ftpClient.setBufferSize(1024 * 2);
        this.ftpClient.setDataTimeout(30 * 1000);
        return isLogin;
    }

    /**
     * @退出关闭服务器链接
     * */
    public void ftpLogOut() {
        if (null != this.ftpClient && this.ftpClient.isConnected()) {
            try {
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器
                if (reuslt) {
                    logger.info("成功退出服务器");
                }
            }catch (IOException e) {
                e.printStackTrace();
                logger.warn("退出FTP服务器异常！" + e.getMessage());
            }finally {
                try {
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接
                }catch (IOException e) {
                    e.printStackTrace();
                    logger.warn("关闭FTP服务器的连接异常！");
                }
            }
        }
    }

    /***
     * 上传Ftp文件
     * @param localFile 当地文件
     * @param romotUpLoadePath 上传服务器路径 - 应该以/结束
     * */
    public boolean uploadFile(File localFile, String romotUpLoadePath) {
        BufferedInputStream inStream = null;
        boolean success = false;
        try {
            this.ftpClient.changeWorkingDirectory(romotUpLoadePath);// 改变工作路径
            inStream = new BufferedInputStream(new FileInputStream(localFile));
            logger.info(localFile.getName() + "开始上传.....");
            success = this.ftpClient.storeFile(localFile.getName(), inStream);
            if (success == true) {
                logger.info(localFile.getName() + "上传成功");
                return success;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(localFile + "未找到");
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inStream != null) {
                try {
                    inStream.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    /***
     * 下载文件
     * @param remoteFileName   待下载文件名称
     * @param localDires 下载到当地那个路径下
     * @param remoteDownLoadPath remoteFileName所在的路径
     * */

//    public boolean downloadFile(String remoteFileName, String localDires,
//                                String remoteDownLoadPath) {
//        String strFilePath = localDires + remoteFileName;
//        BufferedOutputStream outStream = null;
//        boolean success = false;
//        try {
//            this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);
//            outStream = new BufferedOutputStream(new FileOutputStream(
//                    strFilePath));
//            logger.info(remoteFileName + "开始下载....");
//            success = this.ftpClient.retrieveFile(remoteFileName, outStream);
//            if (success == true) {
//                logger.info(remoteFileName + "成功下载到" + strFilePath);
//                return success;
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//            logger.error(remoteFileName + "下载失败");
//        }finally {
//            if (null != outStream) {
//                try {
//                    outStream.flush();
//                    outStream.close();
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        if (success == false) {
//            logger.error(remoteFileName + "下载失败!!!");
//        }
//        return success;
//    }

    /***
     * @上传文件夹（使用）
     * @param localDirectory
     *            当地文件夹
     * @param remoteDirectoryPath
     *            Ftp 服务器路径 以目录"/"结束
     * */
    public boolean uploadDirectory(String localDirectory,
                                   String remoteDirectoryPath) {
        System.out.println(remoteDirectoryPath);
        File src = new File(localDirectory);
//        String address = "/TomcatRoot/project/1/1/Rvt/";
        remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";
        boolean makeDirFlag = false;
        try {
            makeDirFlag = this.ftpClient.makeDirectory(remoteDirectoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("localDirectory : " + localDirectory);
//        System.out.println("remoteDirectoryPath : " + remoteDirectoryPath);
//        System.out.println("src.getName() : " + src.getName());
//        System.out.println("remoteDirectoryPath : " + remoteDirectoryPath);
//        System.out.println("makeDirFlag : " + makeDirFlag);
        File[] allFile = src.listFiles();
        for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
            if (!allFile[currentFile].isDirectory()) {
                String srcName = allFile[currentFile].getPath().toString();
                uploadFile(new File(srcName), remoteDirectoryPath);
            }
        }
        for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
            if (allFile[currentFile].isDirectory()) {
                try {
                    this.ftpClient.changeWorkingDirectory(remoteDirectoryPath);// 改变工作路径
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 递归
                uploadDirectory(allFile[currentFile].getPath().toString(),
                        remoteDirectoryPath);
            }
        }
        return true;
    }

    /***
     * @下载文件夹
     * @param localDirectoryPath 本地地址
     * @param remoteDirectory 远程文件夹
     * */
    public boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {
        try {
            String fileName = new File(remoteDirectory).getName();
            localDirectoryPath = localDirectoryPath + fileName + "//";
            new File(localDirectoryPath).mkdirs();
            FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);
            for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
                if (!allFile[currentFile].isDirectory()) {
                    downloadFile(allFile[currentFile].getName(),localDirectoryPath, remoteDirectory);
                }
            }
            for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
                if (allFile[currentFile].isDirectory()) {
                    String strremoteDirectoryPath = remoteDirectory + "/"+ allFile[currentFile].getName();
                    downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            logger.info("下载文件夹失败");
            return false;
        }
        return true;
    }
    // FtpClient的Set 和 Get 函数
    public FTPClient getFtpClient() {
        return ftpClient;
    }
    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }


    /**
     * 初始化ftp服务器 （使用）
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println("connecting...ftp服务器:"+strIp+":"+intPort);
            try {
                ftpClient.connect(strIp, intPort); //连接ftp服务器
            } catch (IOException e) {
                e.printStackTrace();
            }
            ftpClient.login(user, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp服务器:"+strIp+":"+intPort);
            }
            System.out.println("connect successfu...ftp服务器:"+strIp+":"+intPort);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** * 下载文件（使用） *
     * @param pathname FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return */
    public Result downloadFile(String pathname, String filename, String localpath){
        OutputStream os=null;
        try {
            logger.info("开始下载文件");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            if(ftpFiles == null && ftpFiles.length == 0){
                return new Result(ResultStatusCode.FAIL,"该文件为空...");
            }
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            logger.info("下载文件成功");
            return new Result(ResultStatusCode.OK,"下载文件成功...");
        } catch (Exception e) {
            logger.info("下载文件失败");
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Result(ResultStatusCode.OK,"下载文件失败...");
    }

//    public static void main(String[] args){
//        FTPUtils ftp=new FTPUtils("36.112.65.110",21,"Administrator","Jhkj73630915");
//        ftp.ftpLogin();
        //上传文件夹
//        boolean uploadFlag = ftp.uploadDirectory("C:\\Users\\Administrator\\Desktop\\帐号下模型\\3-超高层办公大厦", "TomcatRoot/project/1226846563557576704/Rvt/"); //如果是admin/那么传的就是所有文件，如果只是/那么就是传文件夹
//        ftp.downLoadDirectory("C:\\Users\\Administrator\\Desktop\\帐号下模型", "/TomcatRoot/project/1226838571273555968/Rvt/");
//        System.out.println("uploadFlag : " + uploadFlag);
//        String localpath ="D:\\DownLoad\\a";
//        String lujing = "/TomcatRoot"+"/project/1228617653074137088/Rvt/";
//        String pathname = lujing.substring(0,lujing.lastIndexOf("/")+1);
//        String filename = lujing.substring(lujing.lastIndexOf("/")+1);
//        ftp.downloadFile(pathname,filename,localpath);
//        ftp.ftpLogOut();
//    }
}


