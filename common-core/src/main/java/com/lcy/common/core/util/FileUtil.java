package com.lcy.common.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 文件工具类
 * @Author lcy
 * @Date 2021/4/25 15:13
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * @Description 获取字符的接口
     * @Author lcy
     * @Date 2021/4/25 15:13
     */
    interface StringInterface {

        /**
         * 获取字符的方法
         * @param bytes 文件字节数组
         * @return java.lang.String
         * @author lcy
         * @date 2021/4/25 15:55
         **/
        String getString(byte[] bytes);
    }

    /**
     * 根据文件路径，并且指定字符编码通过二进制字节流读取文件
     * @param filePath 文件路径
     * @param charsets 字符编码
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:39
     **/
    public static String readByInputStream(String filePath,Charset charsets){
        return readByInputStream(new File(filePath),charsets);
    }

    /**
     * 根据文件路径通过二进制字节流读取文件
     * @param filePath 文件路径
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:39
     **/
    public static String readByInputStream(String filePath){
        return readByInputStream(new File(filePath));
    }

    /**
     * 指定字符集通过二进制字节流读取文件
     * @param file     文件
     * @param charsets 字符编码
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:39
     **/
    public static String readByInputStream(File file,Charset charsets){
        //判断文件
        if (file.isDirectory()) {
            log.error("文件类型错误,filePath:{} ",file.getPath());
            return "";
        }
        if (!file.exists()) {
            log.error("文件不存在,filePath:{} ",file.getPath());
            return "";
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return readByInputStream(fileInputStream,charsets);
        } catch (IOException e) {
            log.error("读取文件异常,filePath:{}  ",file.getPath(),e);
            return "";
        }
    }

    /**
     * 通过二进制字节流读取文件
     * @param file 文件
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:39
     **/
    public static String readByInputStream(File file){
        //判断文件
        if (file.isDirectory()) {
            log.error("文件类型错误,filePath:{} ",file.getPath());
            return "";
        }
        if (!file.exists()) {
            log.error("文件不存在,filePath:{} ",file.getPath());
            return "";
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return readByInputStream(fileInputStream);
        } catch (IOException e) {
            log.error("读取文件异常,filePath:{}  ",file.getPath(),e);
            return "";
        }
    }

    /**
     * 通过二进制字节流，指定字符编码读取指定输入流
     * @param inputStream 二进制流
     * @param charsets    字符编码
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:43
     **/
    public static String readByInputStream(InputStream inputStream,Charset charsets){
        return readByInputStream(inputStream,bytes -> new String(bytes,charsets));
    }

    /**
     * 通过二进制字节流，读取指定输入流
     * @param inputStream 二进制流
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:43
     **/
    public static String readByInputStream(InputStream inputStream){
        return readByInputStream(inputStream,String :: new);
    }

    /**
     * 通过二进制字节流，读取指定输入流
     * @param inputStream     二进制流
     * @param stringInterface 获取指定类型的String
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:43
     **/
    public static String readByInputStream(InputStream inputStream,StringInterface stringInterface){
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            //这是指定每次读取1024个字节
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = bufferedInputStream.read(bytes,0,read)) != -1) {
                //可以指定编码读取
                stringBuilder.append(stringInterface.getString(bytes));
            }
        } catch (IOException e) {
            log.error("读取流异常 ",e);
            return "";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭流异常 ",e);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 通过字符流，读取指定文件路径
     * @param filePath 文件路径
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:46
     **/
    public static String readByReader(String filePath){
        return readByReader(new File(filePath));
    }

    /**
     * 通过字符流，读取指定文件
     * @param file 文件
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:46
     **/
    public static String readByReader(File file){
        //判断文件
        if (file.isDirectory()) {
            log.error("文件类型错误,filePath:{} ",file.getPath());
            return "";
        }
        if (!file.exists()) {
            log.error("文件不存在,filePath:{} ",file.getPath());
            return "";
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return readByReader(fileInputStream);
        } catch (IOException e) {
            log.error("读取文件异常,filePath:{} ",file.getPath(),e);
            return "";
        }
    }

    /**
     * 通过字符流，读取指定文件流
     * @param inputStream 文件流
     * @return java.lang.String
     * @author lcy
     * @date 2021/4/25 15:46
     **/
    public static String readByReader(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            //每次读取一行数据，常用于文本和数字类型的文件
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(readLine);
            }
        } catch (IOException e) {
            log.error("读取流异常 ",e);
            return "";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("关闭流异常 ",e);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 写入文件
     * @param filePath    文件路径
     * @param writeString 需要写入字符串
     * @param append      是否往后追加，false为覆盖
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:01
     **/
    public static boolean writeByInputStream(String filePath,String writeString,boolean append){
        return writeByInputStream(new File(filePath),writeString,append);
    }

    /**
     * 写入文件如果原来的文件已经存在则写入
     * @param filePath    文件路径
     * @param writeString 需要写入字符串
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:02
     **/
    public static boolean writeByInputStream(String filePath,String writeString){
        return writeByInputStream(new File(filePath),writeString,false);
    }

    /**
     * 写入文件
     * @param file        文件
     * @param writeString 字符串
     * @param append      是否追加
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:10
     **/
    public static boolean writeByInputStream(File file,String writeString,boolean append){
        //判断文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("文件不存在，创建文件失败！filePath:{}",file.getPath(),e);
            }
        }
        //构造方法后面跟着一个Boolean类型的参数，表示是否追加写入。默认为不追加，即重写
        try (FileOutputStream fileOutputStream = new FileOutputStream(file,append);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            bufferedOutputStream.write(writeString.getBytes());
            return true;
        } catch (IOException e) {
            log.error("写入文件异常，filePath{} ",file.getPath(),e);
            return false;
        }
    }

    /**
     * 写入流
     * @param outputStream 写入流
     * @param writeString  字符串
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:10
     **/
    public static boolean writeByInputStream(OutputStream outputStream,String writeString){
        //构造方法后面跟着一个Boolean类型的参数，表示是否追加写入。默认为不追加，即重写
        try {
            outputStream.write(writeString.getBytes());
            outputStream.flush();
            return true;
        } catch (IOException e) {
            log.error("写入流异常 ",e);
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("关闭流异常 ",e);
                }
            }
        }
    }

    /**
     * 写入文件
     * @param filePath    文件路径
     * @param writeString 需要写入字符串
     * @param append      是否往后追加，false为覆盖
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:01
     **/
    public static boolean writeByWriter(String filePath,String writeString,boolean append){
        return writeByWriter(new File(filePath),writeString,append);
    }

    /**
     * 写入文件如果原来的文件已经存在则写入
     * @param filePath    文件路径
     * @param writeString 需要写入字符串
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:02
     **/
    public static boolean writeByWriter(String filePath,String writeString){
        return writeByWriter(new File(filePath),writeString,false);
    }

    /**
     * 写入文件
     * @param file        文件
     * @param writeString 字符串
     * @param append      是否追加
     * @return boolean
     * @author lcy
     * @date 2021/4/25 16:10
     **/
    public static boolean writeByWriter(File file,String writeString,boolean append){
        //判断文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("文件不存在，创建文件失败！filePath:{}",file.getPath(),e);
            }
        }
        //构造方法后面跟着一个Boolean类型的参数，表示是否追加写入。默认为不追加，即重写
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)
        ) {
            bufferedWriter.write(writeString);
            return true;
        } catch (IOException e) {
            log.error("写入文件异常,filePath:{} ",file.getPath(),e);
            return false;
        }
    }

}
