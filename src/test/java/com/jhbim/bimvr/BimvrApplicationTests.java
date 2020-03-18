package com.jhbim.bimvr;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)

@SpringBootTest
public class BimvrApplicationTests {



    @Test
    public void contextLoads() throws IOException {

//        FTPClient ftpClient = new FTPClient();
//        ftpClient.connect("36.112.65.110", 21);
//        ftpClient.login("Administrator", "Jhkj73630915");
//        ftpClient.changeWorkingDirectory("/");
//        ftpClient.rmd("/12/");
//        if (ftpClient != null && ftpClient.isConnected()) {
//            ftpClient.logout();
//            ftpClient.disconnect();
//            System.out.println("FTP服务器关闭连接！");
//        }
//        aa();
    }

    public void aa(){
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("36.112.65.110", 21);
            ftpClient.login("Administrator", "Jhkj73630915");
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("FTP server refused connection");
                ftpClient.disconnect();
            }
            // 切换到根目录
            ftpClient.changeWorkingDirectory("/TomcatRoot/project/");
            int aa=7;
            String path = aa+"/999/week2/GD/UPLOAD";
            String[] pah = path.split("/");
            // 分层创建目录
            for (String pa : pah) {
                System.out.println(pa);
                ftpClient.makeDirectory(pa);
                // 切到到对应目录
                ftpClient.changeWorkingDirectory(pa);
            }





            String path1 = aa+"/999/week1/GD/UPLOAD";
            String[] pah1 = path1.split("/");
            // 分层创建目录
            for (String pa1 : pah1) {
                System.out.println(pa1);
                ftpClient.makeDirectory(pa1);
                // 切到到对应目录
                ftpClient.changeWorkingDirectory(pa1);
            }
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("FTP服务器关闭连接！");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
