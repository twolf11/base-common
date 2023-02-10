package com.lcy.common.core.web.entity.common;

import java.io.File;
import java.util.Map;

import org.springframework.http.HttpMethod;

/**
 * @Description http请求参数
 * @Author lcy
 * @Date 2021/11/16 14:15
 */
public class HttpParam {

    /**
     * host
     */
    private String host;

    /**
     * port
     */
    private int port;

    /**
     * url
     */
    private String url;

    /**
     * 请求类型
     */
    private HttpMethod httpMethod;

    /**
     * 请求头
     */
    private Map<String,String> headers;

    /**
     * url参数
     */
    private Map<String,String> params;

    /**
     * body参数
     */
    private Object body;

    /**
     * 文件参数参数
     */
    private Map<String,File> fileMap;

    /**
     * body的类型。 form：表示form表单  body：表示body-json数据，默认json
     */
    private String bodyType = "json";

    public HttpParam(String host,int port,String url,HttpMethod httpMethod){
        this.host = host;
        this.port = port;
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public String getHost(){
        return host;
    }

    public HttpParam setHost(String host){
        this.host = host;
        return this;
    }

    public int getPort(){
        return port;
    }

    public HttpParam setPort(int port){
        this.port = port;
        return this;
    }

    public String getUrl(){
        return url;
    }

    public HttpParam setUrl(String url){
        this.url = url;
        return this;
    }

    public HttpMethod getHttpMethod(){
        return httpMethod;
    }

    public HttpParam setHttpMethod(HttpMethod httpMethod){
        this.httpMethod = httpMethod;
        return this;
    }

    public Map<String,String> getHeaders(){
        return headers;
    }

    public HttpParam setHeaders(Map<String,String> headers){
        this.headers = headers;
        return this;
    }

    public Map<String,String> getParams(){
        return params;
    }

    public HttpParam setParams(Map<String,String> params){
        this.params = params;
        return this;
    }

    public Object getBody(){
        return body;
    }

    public HttpParam setBody(Object body){
        this.body = body;
        return this;
    }

    public Map<String,File> getFileMap(){
        return fileMap;
    }

    public HttpParam setFileMap(Map<String,File> fileMap){
        this.fileMap = fileMap;
        return this;
    }

    public String getBodyType(){
        return bodyType;
    }

    public HttpParam setBodyType(String bodyType){
        this.bodyType = bodyType;
        return this;
    }

    @Override public String toString(){
        return "HttpParam{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                ", httpMethod=" + httpMethod +
                ", headers=" + headers +
                ", params=" + params +
                ", body=" + body +
                ", fileMap=" + fileMap +
                ", bodyType='" + bodyType + '\'' +
                '}';
    }
}
