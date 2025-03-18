#### Introduction

The project is a basic scaffold written by a private person. It contains some commonly used tool classes, including hutool dependencies, and also introduces common system plug-in frameworks such as mybatis-plus, springdoc, and redis, which are conducive to the rapid development of the project;
1. Encapsulates the core module of the basic springboot project core
2. Encapsulates the common operations of mybatis-plus, including paging and batch insertion of basic services
3. Encapsulates the common operation tool class of redis

In addition, if you need to introduce graceful-response, you only need to introduce the dependency of graceful-response while introducing the core core, as follows:
Introduce graceful-response dependency:
```
<dependency>
<groupId>com.feiniaojin</groupId>
<artifactId>graceful-response</artifactId>
</dependency>
```
After introducing the graceful-response dependency, the graceful-response configuration file is changed to `graceful-response.response-style=1`, which is completely adapted to the parent dependency

#### Software architecture

1. springboot 3.x version
2. mybatis-plus 3.5.x version
3. graceful-response 5.x-boot3 version

#### Instructions

Pull the project and build it locally. If there is a private warehouse, push the private warehouse directly. The direct dependencies of the microservice project are as follows:
Introduce parent dependencies:
```
<parent>
<artifactId>base-common</artifactId>
<groupId>com.twolf.common</groupId>
<version>1.0.6</version>
</parent>
```
Introduce required dependencies as needed, taking the core package as an example:
```
<dependency>
<groupId>com.twolf.common</groupId>
<artifactId>common-core</artifactId>
</dependency>
```