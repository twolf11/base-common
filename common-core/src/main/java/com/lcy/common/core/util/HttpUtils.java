package com.lcy.common.core.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.lcy.common.core.exception.BusinessException;
import com.lcy.common.core.web.entity.common.HttpParam;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Description http工具类
 * @Author lcy
 * @Date 2021/10/13 17:59
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * json
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * from表单
     */
    public static final MediaType FORM = MediaType.get("multipart/form-data; charset=utf-8");

    /**
     * http请求，参数必填
     * @param httpParam http请求参数
     * @return java.lang.String
     * @author lcy
     * @date 2021/11/16 10:17
     **/
    public static String doHttpRequest(HttpParam httpParam) throws IOException{
        //拼接url上的参数
        StringBuilder params = new StringBuilder();
        if (Tools.isNotEmpty(httpParam.getParams())) {
            params.append("?");
            httpParam.getParams().forEach((name,value) -> params.append(name).append("=").append(value).append("&"));
            params.setLength(params.length() - 1);
        }
        //拼接url参数
        String url = "http://" + httpParam.getHost() + ":" + httpParam.getPort() + httpParam.getUrl() + params;
        Request.Builder builder = new Request.Builder().url(url);

        if (Tools.isNotEmpty(httpParam.getHeaders())) {
            httpParam.getHeaders().forEach(builder :: header);
        }

        if (httpParam.getHttpMethod() == HttpMethod.GET) {
            builder.get();
        } else {
            //转换body数据
            String body = "{}";
            if (Tools.isNotEmpty(httpParam.getBody())) {
                body = JsonUtil.objectToJson(httpParam.getBody());
            }
            //封装实体和类型
            RequestBody requestBody = null;
            MultipartBody fileBody = null;
            if ("form".equals(httpParam.getBodyType())) {
                builder.header("Content-Type","multipart/form-data; charset=utf-8");

                //文件类型
                MultipartBody.Builder fileBuilder = new MultipartBody.Builder().setType(FORM);
                if (Tools.isNotEmpty(httpParam.getFileMap())) {
                    httpParam.getFileMap().forEach((key,file) -> {
                        fileBuilder.addFormDataPart(key,file.getName(),RequestBody.create(file,FORM));
                    });
                }
                if (Tools.isNotEmpty(httpParam.getBody())) {
                    requestBody = FormBody.create(body,FORM);
                    fileBuilder.addPart(requestBody);
                }
                fileBody = fileBuilder.build();
            } else {
                //设置请求头
                builder.header("Content-Type","application/json;charset=UTF-8");
                requestBody = RequestBody.create(body,JSON);
            }

            //判断不同类型的参数调用
            if (httpParam.getHttpMethod() == HttpMethod.POST) {
                if ("form".equals(httpParam.getBodyType()) && fileBody != null) {
                    builder.post(fileBody);
                } else {
                    builder.post(requestBody);
                }

            } else if (httpParam.getHttpMethod() == HttpMethod.PUT) {
                builder.put(requestBody);
            } else if (httpParam.getHttpMethod() == HttpMethod.DELETE) {
                if ("{}".equals(body)) {
                    builder.delete();
                } else {
                    builder.delete(requestBody);
                }
            } else {
                throw new BusinessException("请求类型不能为空");
            }
        }

        Request request = builder.build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.MINUTES)
                .writeTimeout(10,TimeUnit.MINUTES)
                .connectTimeout(10,TimeUnit.MINUTES)
                .build();
        String result = "";
        // 创建请求
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            result = Tools.isNotEmpty(response.body()) ? response.body().string() : "";
            //如果有unicode编码，进行转码
            if (result.contains("\\u")) {
                result = Tools.unicodeToUtf8(result);
            }
        } else {
            log.error("http请求返回状态异常");
        }
        return result;
    }

    /**
     * http请求，参数必填。返回对象
     * @param httpParam http请求参数
     * @param clazz     返回对象class
     * @return java.lang.String
     * @author lcy
     * @date 2021/11/16 10:17
     **/
    public static <T> T doHttpRequest(HttpParam httpParam,Class<T> clazz) throws IOException{
        String result = doHttpRequest(httpParam);
        return JsonUtil.jsonToObject(result,clazz);
    }

    /**
     * http请求，参数必填。返回对象并且捕获异常
     * @param httpParam http请求参数
     * @param clazz     返回对象class
     * @return java.lang.String
     * @author lcy
     * @date 2021/11/16 10:17
     **/
    public static <T> T doHttpRequestCatch(HttpParam httpParam,Class<T> clazz){
        String result = "";
        try {
            result = doHttpRequest(httpParam);
        } catch (IOException e) {
            log.error("do http request error. ",e);
        }
        return JsonUtil.jsonToObject(result,clazz);
    }

}