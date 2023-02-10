package com.lcy.common.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.lcy.common.core.exception.BusinessException;

/**
 * @Description sftp操作服务器工具类
 * @Author lcy
 * @Date 2022/2/14 15:11
 */
@SuppressWarnings("unchecked")
public class SftpUtil {

    private static final Logger log = LoggerFactory.getLogger(SftpUtil.class);

    /**
     * 连接sftp服务器
     * @param host     主机
     * @param port     端口
     * @param username 服务器用户名
     * @param password 密码
     * @return com.jcraft.jsch.ChannelSftp
     * @author lcy
     * @date 2022/2/14 15:35
     **/
    public static ChannelSftp connect(String host,int port,String username,String password){
        try {
            JSch jsch = new JSch();
            log.info("sftp Session created..");
            //获取session
            Session sshSession = jsch.getSession(username,host,port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking","no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            //连接ssh获取管道
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("sftp connect to {} success ",host);
            return (ChannelSftp)channel;
        } catch (JSchException e) {
            log.error("sftp连接失败 ",e);
            return null;
        }
    }

    /**
     * 关闭sftp连接
     * @param channelSftp sftp连接对象
     * @author lcy
     * @date 2022/2/14 17:01
     **/
    public static void closeSftp(ChannelSftp channelSftp){
        if (channelSftp != null) {
            if (channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            try {
                if (channelSftp.getSession() != null && channelSftp.getSession().isConnected()) {
                    channelSftp.getSession().disconnect();
                }
            } catch (JSchException e) {
                log.error("close channel sftp error. ",e);
                throw new BusinessException("关闭sftp连接失败");
            }
            channelSftp.quit();
        }
    }

    /**
     * 上传本地文件到ftp
     * @param directory 上传到文件服务器的文件路径（目标服务器路径）
     * @param localFile 本地文件路径
     * @param sftp      sftp对象
     * @author lcy
     * @date 2022/2/14 15:41
     **/
    public static void upload(String directory,String localFile,ChannelSftp sftp){
        try {
            sftp.cd(directory);
            File file = new File(localFile);
            sftp.put(new FileInputStream(file),file.getName());
            log.info("upload file success.filePath={} ",localFile);
        } catch (Exception e) {
            log.error("upload file fail.filePath={} ",localFile,e);
        } finally {
            closeSftp(sftp);
        }
    }

    /**
     * 上传文件到sftp
     * @param directory       上传到文件服务器的文件路径（目标服务器路径）
     * @param fileInputStream 文件流
     * @param fileName        文件名称
     * @param sftp            sftp连接对象
     * @author lcy
     * @date 2022/2/14 15:39
     **/
    public static void upload(String directory,FileInputStream fileInputStream,String fileName,ChannelSftp sftp){
        try {
            sftp.cd(directory);
            sftp.put(fileInputStream,fileName);
            log.info("upload file success.filename={} ",fileName);
        } catch (SftpException e) {
            log.error("upload file fail.filename={} ",fileName,e);
        } finally {
            closeSftp(sftp);
        }
    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param fileName  下载的文件名
     * @param localPath 存在本地的路径
     * @param sftp      sftp连接对象
     * @return java.io.File
     * @author lcy
     * @date 2022/2/14 15:49
     **/
    public static File download(String directory,String fileName,String localPath,ChannelSftp sftp){
        try {
            sftp.cd(directory);
            File file = new File(localPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            sftp.get(fileName,new FileOutputStream(file));
            return file;
        } catch (Exception e) {
            log.error("download sftp file fail,directory={},fileName={} ",directory,fileName,e);
            return null;
        } finally {
            closeSftp(sftp);
        }
    }

    /**
     * 批量下载文件
     * @param directory 需要下载的远程服务器目录
     * @param localPath 存在本地的目录
     * @param isDeep    是否递归--遇到目录继续往下遍历
     * @param sftp      sftp连接对象
     * @return java.io.File
     * @author lcy
     * @date 2022/2/14 15:49
     **/
    public static List<File> batchDownload(String directory,String localPath,boolean isDeep,ChannelSftp sftp){
        //最终文件路径
        List<File> files = new ArrayList<>();
        //栈存储临时路径
        Stack<String> pathStack = new Stack<>();
        pathStack.push(directory);
        Stack<String> localPathStack = new Stack<>();
        localPathStack.push(localPath);
        try {
            while (Tools.isNotEmpty(pathStack)) {
                String path = pathStack.pop();
                String localDirPath = localPathStack.pop();
                Vector<ChannelSftp.LsEntry> vector = sftp.ls(path);
                File dirFile = new File(localDirPath);
                //目录不存在则创建
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                sftp.cd(path);
                vector.forEach(lsEntry -> {
                    String filename = lsEntry.getFilename();
                    SftpATTRS attrs = lsEntry.getAttrs();
                    //是否递归
                    if (attrs.isDir()) {
                        if (isDeep && !".".equals(filename) && !"..".equals(filename)) {
                            //存储新目录
                            String newPath = path + "/" + filename;
                            pathStack.push(newPath);
                            String newLocalPath = localDirPath + "/" + filename;
                            localPathStack.push(newLocalPath);
                        }
                    } else {
                        File file = new File(dirFile,filename);
                        try {
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            sftp.get(filename,new FileOutputStream(file));
                            files.add(file);
                        } catch (IOException | SftpException e) {
                            log.error("",e);
                        }
                    }
                });
            }
        } catch (SftpException e) {
            log.error("batch download sftp file fail,directory={} ",directory,e);
        } finally {
            closeSftp(sftp);
        }

        return files;
    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param fileName  下载的文件名
     * @param sftp      sftp连接对象
     * @return java.io.File
     * @author lcy
     * @date 2022/2/14 15:49
     **/
    public static FileInputStream download(String directory,String fileName,ChannelSftp sftp){
        try {
            sftp.cd(directory);
            return (FileInputStream)sftp.get(fileName);
        } catch (Exception e) {
            log.error("download sftp file fail,directory={},fileName={} ",directory,fileName,e);
            return null;
        } finally {
            closeSftp(sftp);
        }
    }

    /**
     * 删除sftp服务器上的文件
     * @param directory 文件目录
     * @param fileName  文件名称
     * @param sftp      sftp连接对象
     * @author lcy
     * @date 2022/2/14 15:51
     **/
    public static void delete(String directory,String fileName,ChannelSftp sftp){
        try {
            sftp.cd(directory);
            sftp.rm(fileName);
        } catch (Exception e) {
            log.error("delete sftp file fail,directory={},fileName={} ",directory,fileName,e);
        } finally {
            closeSftp(sftp);
        }
    }
}

