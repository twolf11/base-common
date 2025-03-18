# base-common

#### 介绍

项目是私人写的一个基础的脚手架，里面有一些平时常用的工具类，包括hutool依赖，还引入系统常见的插件框架mybatis-plus、springdoc、redis等，利于项目的快速开发

1. 封装了基础的springboot项目核心模块core
2. 封装了mybatis-plus的常用操作，包括基础service的分页，批量插入等
3. 封装了redis的常用操作工具类

另外如果需要引入graceful-response，在引入core核心的同时，需要引入graceful-response的依赖即可，如下：
引入graceful-response依赖：
```
        <dependency>
            <groupId>com.feiniaojin</groupId>
            <artifactId>graceful-response</artifactId>
        </dependency>
```
引入graceful-response依赖后，graceful-response的配置文件改成`graceful-response.response-style=1`,则完全适配父依赖

#### 软件架构

1. springboot 3.x版本
2. mybatis-plus 3.5.x版本
3. graceful-response 5.x-boot3版本

#### 使用说明

拉取项目，在本地构建，如果有私有仓库则直接推送私有仓库。微服务的项目直接依赖如下：
引入父依赖：
```
    <parent>
        <artifactId>base-common</artifactId>
        <groupId>com.twolf.common</groupId>
        <version>1.0.6</version>
    </parent>
```
按需引入所需依赖，以核心包为例子：
```
        <dependency>
            <groupId>com.twolf.common</groupId>
            <artifactId>common-core</artifactId>
        </dependency>
```
