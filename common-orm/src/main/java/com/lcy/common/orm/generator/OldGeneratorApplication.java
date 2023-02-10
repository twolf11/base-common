package com.lcy.common.orm.generator;

/**
 * @Description 生成器main方法
 * @Author lcy
 * @Date 2021/04/13 09:00
 */
public class OldGeneratorApplication {

    ///**
    // * 数据库对象
    // */
    //@Data
    //@Builder(toBuilder = true)
    //public static class GenerateInfo {
    //
    //    /**
    //     * 项目名称
    //     */
    //    private String projectName;
    //
    //    /**
    //     * 数据库连接串
    //     */
    //    private String url;
    //
    //    /**
    //     * 用户名
    //     */
    //    private String username;
    //
    //    /**
    //     * 密码
    //     */
    //    private String password;
    //
    //    /**
    //     * 作者
    //     */
    //    private String author;
    //
    //    /**
    //     * 包路径
    //     */
    //    private String packagePath;
    //
    //    /**
    //     * 功能名称，如job任务
    //     */
    //    private String packageName;
    //}
    //
    //public static void main(String[] args){
    //    GenerateInfo generateInfo = GenerateInfo.builder()
    //            .projectName("/base-parent/base-orm/")
    //            .url("jdbc:mysql://127.0.0.1:3306/base?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai")
    //            .username("root")
    //            .password("root")
    //            .author("lcy")
    //            .packagePath("com.lcy.base")
    //            .packageName("base")
    //            .build();
    //    generate(generateInfo,"true");
    //}
    //
    ///**
    // * 代码生成方法
    // *
    // * @param generateInfo 数据库对象
    // * @param tableNames   表名，支持多个
    // * @author lcy
    // * @date 2021/5/14 11:52
    // **/
    //public static void generate(GenerateInfo generateInfo,String... tableNames){
    //    AutoGenerator mpg = new AutoGenerator();
    //
    //    // 全局配置
    //    GlobalConfig gc = new GlobalConfig();
    //    System.out.println(gc);
    //    String projectPath = System.getProperty("user.dir") + generateInfo.projectName;
    //    gc.setOutputDir(projectPath + "/src/main/java");
    //    // TODO 设置用户名
    //    gc.setAuthor("lcy");
    //    gc.setOpen(true);
    //    // service 命名方式
    //    gc.setServiceName("%sService");
    //    // service impl 命名方式
    //    gc.setServiceImplName("%sServiceImpl");
    //    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    //    gc.setMapperName("%sMapper");
    //    gc.setXmlName("%sMapper");
    //    gc.setFileOverride(true);
    //    //开启swaggerUI
    //    gc.setSwagger2(true);
    //    gc.setActiveRecord(true);
    //    // XML 二级缓存
    //    gc.setEnableCache(false);
    //    // XML ResultMap
    //    gc.setBaseResultMap(true);
    //    // XML columList
    //    gc.setBaseColumnList(true);
    //    //设置日期格式
    //    gc.setDateType(DateType.TIME_PACK);
    //    //设置id自动生成方式
    //    gc.setIdType(IdType.AUTO);
    //    mpg.setGlobalConfig(gc);
    //
    //    // TODO 数据源配置
    //    DataSourceConfig dsc = new DataSourceConfig();
    //    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    //    dsc.setUrl(generateInfo.getUrl());
    //    dsc.setUsername(generateInfo.getUsername());
    //    dsc.setPassword(generateInfo.getPassword());
    //    mpg.setDataSource(dsc);
    //
    //    // TODO 包配置
    //    PackageConfig pc = new PackageConfig();
    //    //pc.setModuleName(scanner("模块名"));
    //    if (Tools.isNotEmpty(generateInfo.getPackageName())) {
    //        generateInfo.setPackageName("." + generateInfo.getPackageName());
    //    }
    //    pc.setParent(generateInfo.getPackagePath());
    //    pc.setMapper("mapper" + generateInfo.getPackageName());
    //    pc.setEntity("model.domain" + generateInfo.getPackageName());
    //    pc.setService("service" + generateInfo.getPackageName());
    //    pc.setServiceImpl("service" + generateInfo.getPackageName() + ".impl");
    //    mpg.setPackageInfo(pc);
    //    // 自定义需要填充的字段
    //    //List<TableFill> tableFillList = new ArrayList<>();
    //    //如 每张表都有一个创建时间、修改时间
    //    //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
    //    //修改时，修改时间会修改，
    //    //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
    //    //使用公共字段填充功能，就可以实现，自动按场景更新了。
    //    //如下是配置
    //    //TableFill createField = new TableFill("CREATE_TIME",FieldFill.INSERT);
    //    //TableFill modifiedField = new TableFill("LAST_UPDATE_TIME",FieldFill.INSERT_UPDATE);
    //    //tableFillList.add(createField);
    //    //tableFillList.add(modifiedField);
    //
    //    // 自定义配置
    //    //InjectionConfig cfg = new InjectionConfig() {
    //    //
    //    //    @Override
    //    //    public void initMap(){
    //    //        // to do nothing
    //    //    }
    //    //};
    //    //List<FileOutConfig> focList = new ArrayList<>();
    //    //focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
    //    //
    //    //    @Override
    //    //    public String outputFile(TableInfo tableInfo){
    //    //        // 自定义输入文件名称
    //    //        return projectPath + "/src/main/resources/mapper/"
    //    //                + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
    //    //    }
    //    //});
    //    //cfg.setFileOutConfigList(focList);
    //    //mpg.setCfg(cfg);
    //    TemplateConfig tc = new TemplateConfig();
    //    // vm/com.turing.common.entity.java 模板路径配置，默认在vm目录下，.vm 后缀不用加
    //    //使用自定义模板生成代码
    //    tc.setController("/templates/controller.java");
    //    tc.setService("/templates/service.java");
    //    tc.setServiceImpl("/templates/serviceImpl.java");
    //    tc.setEntity("/templates/entity.java");
    //    tc.setMapper("/templates/mapper.java");
    //    //tc.setXml(null);
    //    mpg.setTemplate(tc);
    //
    //    // 策略配置
    //    StrategyConfig strategy = new StrategyConfig();
    //
    //    strategy.setNaming(NamingStrategy.underline_to_camel);
    //    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    //    strategy.setEntityLombokModel(true);
    //    // 设置逻辑删除键
    //    strategy.setlogicDeleteFieldName("del_flag");
    //    // 指定生成的bean的数据库表名
    //    strategy.setInclude(tableNames);
    //    //strategy.setSuperEntityColumns("id");
    //    // 驼峰转连字符
    //    strategy.setControllerMappingHyphenStyle(true);
    //    //设置集成的类
    //    strategy.setSuperEntityClass(BaseDo.class);
    //    mpg.setStrategy(strategy);
    //    // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
    //    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    //    mpg.execute();
    //}

}
