package com.jhbim.bimvr.controller;

import com.jhbim.bimvr.dao.entity.pojo.Project;
import com.jhbim.bimvr.dao.entity.pojo.Rvt;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserProject;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.ProjectMapper;
import com.jhbim.bimvr.dao.mapper.RvtMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.dao.mapper.UserProjectMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.FTPUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Vipuser")
public class VipUserController {
    @Value("${host}")
    private volatile String HOST;

    @Value("${prot}")
    private volatile Integer PROT;

    @Value("${username}")
    private volatile String USERNAME;

    @Value("${password}")
    private volatile String PASSWROD;
    @Resource
    UserMapper userMapper;
    @Resource
    UserProjectMapper userProjectMapper;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    RvtMapper rvtMapper;

    @RequestMapping("/selectvip")
    public Result selectvip(String phone){
        return new Result(ResultStatusCode.OK,userMapper.selectByPrimaryKey(phone));
    }

    /**
     * 根据手机号查询该用户的项目
     * @param phone
     * @return
     */
//    @RequestMapping("/userproject")
//    public Result userproject(String phone){
//        User user = userMapper.selectByPrimaryKey(phone);
//        List<UserProject> userProjectList=userProjectMapper.selectuserid(user.getUserId());
//        List<Project> projectList=new ArrayList<>();
//        for (UserProject userProject:userProjectList) {
//            Project project=projectMapper.selectByPrimaryKey(userProject.getProjectId());
//            projectList.add(project);
//        }
//        return new Result(ResultStatusCode.OK,projectList);
//    }

    /**
     * 上传文件夹
     * @param id  项目id
     * @param address  上传文件夹绝对路径
     * @return
     */
//    @RequestMapping("/uploadfolder")
//    public Result uploadfolder(String id,String address){
//
//        System.out.println(id+address+"----");
//        if(id.isEmpty() || address.isEmpty()){
//            return new Result(ResultStatusCode.BAD_REQUEST);
//        }
//        Project project = projectMapper.selectByPrimaryKey(id);
//        //FTP文件夹路径
//        String Ftpaddress = project.getRvtaddress()+"/";
//        FTPUtils ftp=new FTPUtils(HOST,PROT,USERNAME,PASSWROD);
//        ftp.ftpLogin();
//        //上传文件夹
//        boolean uploadFlag = ftp.uploadDirectory(address, Ftpaddress); //如果是admin/那么传的就是所有文件，如果只是/那么就是传文件夹
//        ftp.ftpLogOut();
//        return new Result(ResultStatusCode.OK,"上传成功...");
//    }

    /**
     * 根据项目id查询
     * @param id
     * @return
     */
//    @RequestMapping("/findByprojectid")
//    public Result findByprojectid(String id){
//
//        Rvt rvt = rvtMapper.findByprojectid(id);
//        if(rvt == null){
//            return new Result(ResultStatusCode.FAIL,"该项目没有可下载的模型...");
//        }
//        return new Result(ResultStatusCode.OK,rvt);
//    }

    /**
     * 下载文件
     * @param id 项目id
     * @return
     */
//    @RequestMapping("/download")
//    public Result download(String id){
//        Project project = projectMapper.selectByPrimaryKey(id);
//        Rvt rvt = rvtMapper.findByprojectid(id);
//        if(rvt == null){
//            return new Result(ResultStatusCode.FAIL,"该项目没有可下载的模型...");
//        }
//        String lujing = "/TomcatRoot"+rvt.getUrl();
//        String pathname = lujing.substring(0,lujing.lastIndexOf("/")+1);
//        String filename = lujing.substring(lujing.lastIndexOf("/")+1);
//        File file = new File("D:/DownLoad/"+project.getProjectName());
//        if(!file.exists()){
//            file.mkdirs();
//        }
//        String localpath = file.getAbsolutePath();
//        FTPUtils ftp=new FTPUtils(HOST,PROT,USERNAME,PASSWROD);
//        ftp.ftpLogin();
//        ftp.downloadFile(pathname, filename, localpath);
//        ftp.ftpLogOut();
//        return new Result(ResultStatusCode.OK,"下载成功...");
//    }

//    public Result findByprojectid(MultipartFile[] file){
//        String id= "1226838571273555968";
//        if(id.isEmpty()){
//            return new Result(ResultStatusCode.BAD_REQUEST);
//        }
//        System.out.println(id+"---");
//        Project project = projectMapper.selectByPrimaryKey(id);
//        String address ="C:\\ftp\\project\\1185409284796715009\\1\\price";
//        System.out.println(project.getRvtaddress());
//        System.out.println(address);
//        boolean bool = update(address,file);
//        System.out.println(bool);
//        String fileRealName=null;
//        if(bool){
//            System.out.println(file.length+"----");
//            for (MultipartFile files : file) {
//                fileRealName = files.getOriginalFilename();// 获得原始文件名;
//            }
//            String dz = address+"/"+fileRealName;
//            try {
//                boolean b = unzip(dz,address);
//                if(b){
//                    File files = new File(dz);
//                    if ((files.exists() && files.isFile())) {
//                        files.delete();
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return new Result(ResultStatusCode.OK,"上传成功...");
//    }
//public static boolean update( String address, MultipartFile[] files){
//    if (files != null && files.length >0) {
//        String filePath=null;
//        for (MultipartFile file : files) {
//            String fileRealName = file.getOriginalFilename();// 获得原始文件名;
//            filePath = address + "/"+ fileRealName;
//            File dest = new File(filePath);
//            try {
//
//                file.transferTo(dest);
//            } catch (IllegalStateException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    return true;
//}
//    private static File buildFile(String fileName, boolean isDirectory) {
//        File target = new File(fileName);
//        if (isDirectory) {
//            target.mkdirs();
//        } else {
//            if (!target.getParentFile().exists()) {
//                target.getParentFile().mkdirs();
//                target = new File(target.getAbsolutePath());
//            }
//        }
//        return target;
//    }
//    /**
//     *
//     * @param zipFilePath  压缩文件
//     * @param targetPath 解压路径
//     * @throws IOException
//     */
//    public static boolean unzip(String zipFilePath, String targetPath) throws IOException {
//        OutputStream os = null;
//        InputStream is = null;
//        ZipFile zipFile = null;
//        try {
//            zipFile = new ZipFile(zipFilePath, Charset.forName("GBK"));
//            String directoryPath = "";
//            if (null == targetPath || "".equals(targetPath)) {
//                directoryPath = zipFilePath.substring(0, zipFilePath
//                        .lastIndexOf("."));
//            } else {
//                directoryPath = targetPath;
//            }
//            Enumeration<?> entryEnum = zipFile.entries();
//            if (null != entryEnum) {
//                ZipEntry zipEntry = null;
//                while (entryEnum.hasMoreElements()) {
//                    zipEntry = (ZipEntry) entryEnum.nextElement();
//                    if (zipEntry.getSize() > 0) {
//                        // 文件
//                        File targetFile = VipUserController.buildFile(directoryPath
//                                + File.separator + zipEntry.getName(), false);
//                        os = new BufferedOutputStream(new FileOutputStream(targetFile));
//                        is = zipFile.getInputStream(zipEntry);
//                        byte[] buffer = new byte[4096];
//                        int readLen = 0;
//                        while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
//                            os.write(buffer, 0, readLen);
//                            os.flush();
//                        }
//                        is.close();
//                        os.close();
//                    }
//                    if (zipEntry.isDirectory()) {
//                        String pathTemp =  directoryPath + File.separator
//                                + zipEntry.getName();
//                        File file = new File(pathTemp);
//                        file.mkdirs();
//                        System.out.println(pathTemp);
////                        continue;
//                    }
//
//                }
//            }
//        } catch (IOException ex) {
//            throw ex;
//        } finally {
//            if(null != zipFile){
//                zipFile.close();
//                zipFile = null;
//            }
//            if (null != is) {
//                is.close();
//            }
//            if (null != os) {
//                os.close();
//            }
//        }
//        return true;
//    }
}
