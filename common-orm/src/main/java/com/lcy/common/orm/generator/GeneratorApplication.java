package com.lcy.common.orm.generator;

import java.util.Collections;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.lcy.common.core.util.Tools;
import com.lcy.common.orm.mapper.BatchBaseMapper;
import lombok.Builder;
import lombok.Data;

/**
 * 生成器main方法
 * @Author lcy
 * @Date 2021/04/13 09:00
 */
@SuppressWarnings("unchecked")
public class GeneratorApplication {

    public static void main(String[] args){
        GenerateInfo generateInfo = GenerateInfo.builder()
                .projectName("common-orm")
                .ip("192.168.1.22")
                .port("3306")
                .database("wendao_auth")
                .username("root")
                .password("root")
                .author("lcy")
                .packagePath("com.lcy")
                .packageName("pay")
                .entitySuperClass(Model.class)
                //.superEntityColumns(new String[]{"id","is_delete","create_by","create_time","update_by","update_time","version"})
                .mapperSuperClass(BatchBaseMapper.class)
                .enableLombok(true)
                .build();
        generate(generateInfo,"pay_record");
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
                            .enableSpringdoc()
                            .outputDir(System.getProperty("user.dir") + (StrUtil.isEmpty(generateInfo.getProjectName()) ? "" : "/" + generateInfo.getProjectName()) + "/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(generateInfo.packagePath) // 设置父包名
                            .moduleName(generateInfo.packageName);// 设置父包模块名
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tableNames)
                            //实体类配置
                            .entityBuilder()
                            .enableActiveRecord()
                            .disableSerialVersionUID()
                            .enableLombok()
                            //mapper配置
                            .mapperBuilder()
                            .superClass(generateInfo.getMapperSuperClass())
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            //service
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .controllerBuilder();
                    if (generateInfo.entitySuperClass != null) {
                        builder.entityBuilder().superClass(generateInfo.entitySuperClass);
                    }
                    if (generateInfo.superEntityColumns != null && generateInfo.superEntityColumns.length > 0) {
                        builder.entityBuilder().addSuperEntityColumns(generateInfo.superEntityColumns);
                    }
                    if (generateInfo.serviceSuperClass != null) {
                        builder.serviceBuilder().superServiceClass(generateInfo.serviceSuperClass);
                    }

                    if (generateInfo.serviceImplSuperClass != null) {
                        builder.serviceBuilder().superServiceImplClass(generateInfo.serviceImplSuperClass);
                    }
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
                //使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .injectionConfig(builder -> builder.customMap(Tools.isEmpty(generateInfo.paramMap) ? Collections.EMPTY_MAP : generateInfo.paramMap).build())
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
         * entity继承的父类的字段
         */
        private String[] superEntityColumns;

        /**
         * mapper继承的父类
         */
        private Class<?> mapperSuperClass;

        /**
         * service继承的父类
         */
        private Class<?> serviceSuperClass;

        /**
         * serviceImpl继承的父类
         */
        private Class<?> serviceImplSuperClass;

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
