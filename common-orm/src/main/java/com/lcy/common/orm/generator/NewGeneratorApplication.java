package com.lcy.common.orm.generator;

import java.util.Collections;
import java.util.Map;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lcy.common.core.util.Tools;
import com.lcy.common.core.web.controller.BaseController;
import com.lcy.common.orm.mapper.BatchBaseMapper;
import com.lcy.common.orm.model.domain.BaseDo;
import lombok.Builder;
import lombok.Data;

/**
 * @Description 生成器main方法
 * @Author lcy
 * @Date 2021/04/13 09:00
 */
@SuppressWarnings("unchecked")
public class NewGeneratorApplication {

    public static void main(String[] args){
        homeGenerator();
    }

    /**
     * 本地配置
     * @author lcy
     * @date 2022/2/22 15:43
     **/
    public static void localGenerator(){
        GenerateInfo generateInfo = GenerateInfo.builder()
                .projectName("payment")
                .ip("127.0.0.1")
                .port("3306")
                .database("payment")
                .username("root")
                .password("root")
                .author("lcy")
                .packagePath("com.lcy.base")
                .packageName("base")
                .entitySuperClass(BaseDo.class)
                .mapperSuperClass(BatchBaseMapper.class)
                .controllerSuperClass(BaseController.class)
                .enableLombok(true)
                .build();
        generate(generateInfo,"payment");
    }

    /**
     * 常用配置
     * @author lcy
     * @date 2022/2/22 15:42
     **/
    public static void homeGenerator(){
        GenerateInfo generateInfo = GenerateInfo.builder()
                .projectName("common-orm")
                .ip("192.168.1.22")
                .port("30102")
                .database("paas_coupon")
                .username("root")
                .password("mysql1087..")
                .author("lcy")
                .packagePath("com.lcy.common")
                .packageName("base")
                .entitySuperClass(BaseDo.class)
                .mapperSuperClass(BatchBaseMapper.class)
                .controllerSuperClass(BaseController.class)
                .enableLombok(true)
                //.paramMap(Collections.singletonMap("generateKeyXml","true"))
                .build();
        generate(generateInfo,"coupon");
    }

    /**
     * 生成代码
     * @param generateInfo 参数
     * @param tableNames   表名
     * @author lcy
     * @date 2022/1/24 18:08
     **/
    public static void generate(GenerateInfo generateInfo,String... tableNames){
        FastAutoGenerator.create(
                "jdbc:mysql://" + generateInfo.ip + ":" + generateInfo.port + "/" + generateInfo.database + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai"
                ,generateInfo.username,generateInfo.password)
                .globalConfig(builder -> {
                    builder.author(generateInfo.getAuthor()) // 设置作者
                            // 开启 swagger 模式
                            //.enableSwagger()
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/" + generateInfo.getProjectName() + "/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(generateInfo.packagePath) // 设置父包名
                            .moduleName(generateInfo.packageName);// 设置父包模块名
                    // 设置mapperXml生成路径
                    //.pathInfo(Collections.singletonMap(OutputFile.mapperXml,generateInfo.getProjectName() + "xml"));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tableNames)
                            //实体类配置
                            .entityBuilder()
                            .enableActiveRecord()
                            //.disableSerialVersionUID()
                            .superClass(generateInfo.entitySuperClass == null ? Model.class : generateInfo.entitySuperClass)
                            //.enableChainModel()
                            //.enableLombok()
                            //.addSuperEntityColumns("id","del_flag","create_by","create_time","update_by","update_time","version")
                            //.idType(IdType.AUTO)
                            //.formatFileName("%sEntity")
                            //mapper配置
                            .mapperBuilder()
                            .superClass(generateInfo.getMapperSuperClass())
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            //service
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .controllerBuilder();
                    if (generateInfo.controllerSuperClass != null) {
                        builder.controllerBuilder().superClass(generateInfo.controllerSuperClass);
                    }
                    if (generateInfo.enableLombok) {
                        builder.entityBuilder().enableLombok();
                    }
                    builder.build();
                    // 设置过滤表前缀
                    //.addTablePrefix("t_", "c_");
                })
                .injectionConfig(builder -> builder.customMap(Tools.isEmpty(generateInfo.paramMap) ? Collections.EMPTY_MAP : generateInfo.paramMap).build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 数据库对象
     */
    @Data
    @Builder(toBuilder = true)
    public static class GenerateInfo {

        /**
         * 项目名称
         */
        private String projectName;

        /**
         * 数据库ip
         */
        private String ip;

        /**
         * 数据库port
         */
        private String port;

        /**
         * 数据库名称
         */
        private String database;

        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * 作者
         */
        private String author;

        /**
         * 包路径
         */
        private String packagePath;

        /**
         * 功能名称，如job任务
         */
        private String packageName;

        /**
         * entity继承的父类
         */
        private Class<?> entitySuperClass;

        /**
         * mapper继承的父类
         */
        private Class<?> mapperSuperClass;

        /**
         * controller继承的父类
         */
        private Class<?> controllerSuperClass;

        /**
         * controller继承的父类
         */
        private boolean enableLombok;

        /**
         * 模板传递的参数
         */
        private Map<String,Object> paramMap;
    }

}
