package com.lcy.base.common.util;

import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @Description Log日志工具类
 * @Author lcy
 * @Date 2020/9/15 16:07
 */
public class Log {

    /**
     * 类名
     */
    private static final String CLASS_NAME = Log.class.getName();

    /**
     * 空数组，方便调用log日志的spi
     */
    private static final Object[] EMPTY_ARRAY = new Object[]{};

    /**
     * 静态安全类
     */
    private static final ClassSecurityManager SECURITY_MANAGER = new ClassSecurityManager();

    /**
     * 打印info日志
     *
     * @param message 日志信息
     * @author lcy
     * @date 2020/9/15 16:41
     **/
    public static void info(String message){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isInfoEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,null);
        }
    }

    /**
     * 打印info日志，拼接参数
     *
     * @param message 日志信息
     * @param params  参数
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void info(String message,Object... params){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isInfoEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,params,null);
        }
    }

    /**
     * 打印info日志，打印Exception
     *
     * @param message   日志信息
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void info(String message,Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isInfoEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,throwable);
        }
    }

    /**
     * 打印info的Exception
     *
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void info(Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isInfoEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,"error:",EMPTY_ARRAY,throwable);
        }
    }

    /**
     * 打印warn日志
     *
     * @param message 日志信息
     * @author lcy
     * @date 2020/9/15 16:41
     **/
    public static void warn(String message){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isWarnEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,null);
        }
    }

    /**
     * 打印warn日志，拼接参数
     *
     * @param message 日志信息
     * @param params  参数
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void warn(String message,Object... params){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isWarnEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,params,null);
        }
    }

    /**
     * 打印warn日志，打印Exception
     *
     * @param message   日志信息
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void warn(String message,Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isWarnEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,throwable);
        }
    }

    /**
     * 打印warn的Exception
     *
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void warn(Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isWarnEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,"error:",EMPTY_ARRAY,throwable);
        }
    }

    /**
     * 打印debug日志
     *
     * @param message 日志信息
     * @author lcy
     * @date 2020/9/15 16:41
     **/
    public static void debug(String message){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isDebugEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,null);
        }
    }

    /**
     * 打印debug日志，拼接参数
     *
     * @param message 日志信息
     * @param params  参数
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void debug(String message,Object... params){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isInfoEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,params,null);
        }
    }

    /**
     * 打印debug日志，打印Exception
     *
     * @param message   日志信息
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void debug(String message,Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isDebugEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,throwable);
        }
    }

    /**
     * 打印debug的Exception
     *
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void debug(Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        if (logger.isDebugEnabled()) {
            logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,"error:",EMPTY_ARRAY,throwable);
        }
    }

    /**
     * 打印error日志
     *
     * @param message 日志信息
     * @author lcy
     * @date 2020/9/15 16:41
     **/
    public static void error(String message){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,null);
    }

    /**
     * 打印error日志，拼接参数
     *
     * @param message 日志信息
     * @param params  参数
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void error(String message,Object... params){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,params,null);
    }

    /**
     * 打印error日志，打印Exception
     *
     * @param message   日志信息
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void error(String message,Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,message,EMPTY_ARRAY,throwable);
    }

    /**
     * 打印error的Exception
     *
     * @param throwable 异常对象
     * @author lcy
     * @date 2020/9/15 16:42
     **/
    public static void error(Throwable throwable){
        LocationAwareLogger logger = (LocationAwareLogger)LoggerFactory.getLogger(getCallClassName());
        logger.log(null,CLASS_NAME,LocationAwareLogger.INFO_INT,"error:",EMPTY_ARRAY,throwable);
    }

    /**
     * 静态安全内部类
     *
     * @author lcy
     * @date 2020/9/15 16:29
     **/
    private static final class ClassSecurityManager extends SecurityManager {

        /**
         * 获取class方法，继承原来的方法
         */
        protected Class<?>[] getCallClassContext(){
            return super.getClassContext();
        }
    }

    /**
     * 获取调用方的class的name
     *
     * @return java.lang.String
     * @author lcy
     * @date 2020/9/15 16:30
     **/
    private static String getCallClassName(){
        //为什么要取下标3的数据，即第四个，因为从SECURITY_MANAGER对象的类图层级算，
        //0：SecurityManager，1：ClassSecurityManager，2：Log，3:调用方类
        return SECURITY_MANAGER.getCallClassContext()[3].getName();
    }
}
