package com.jhbim.bimvr;


import com.jhbim.bimvr.controller.pc.alipay.AlipayPagePayController;
import com.jhbim.bimvr.utils.ExcelToHtml;
import com.jhbim.bimvr.utils.SMSConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.net.www.http.HttpClient;


import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)

@SpringBootTest
public class BimvrApplicationTests {

    @Test
    public void contextLoads() {

    }
}
