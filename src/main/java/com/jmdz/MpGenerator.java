package com.jmdz;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @Author：Wangshengtao
 * @Description: mybatis-plus 代码生成器
 * @Date: 2020-08-10 09:04
 */
public class MpGenerator {

    private static final String DB_URL = "jdbc:sqlserver://10.168.1.180:1433;DatabaseName=FMIS_YanTaiFuShan0720";
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";

    //生成的模块名称
    private static String modulesName = "/pad";

    /**
     * <p>
     * cert
     * app_certificate
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //开启 BaseResultMap
        gc.setBaseResultMap(true);
        // 主键自增
        gc.setIdType(IdType.AUTO);
        //开启 baseColumnList
        gc.setBaseColumnList(true);
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        //获取项目目录
        String projectPath = System.getProperty("user.dir");
        // 生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        //开发人员
        gc.setAuthor("Wangshengtao");
        // 是否打开输出目录
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        // 设置数据库类型(可选) 可以根据驱动自动推断
        dsc.setDbType(DbType.SQL_SERVER);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.jmdz.fushan.pad");
        pc.setEntity("model");
        pc.setXml("mapper");
        String models = scanner("模块名");
        //父包模块名
        pc.setModuleName(models);
        mpg.setPackageInfo(pc);

        setTemplateMapper(mpg, models);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        //代表前缀不生成
        strategy.setTablePrefix("f_");
        // 设置父类
        /*strategy.setSuperControllerClass("com.jmdz.fushan.base.BaseController");
        strategy.setSuperEntityClass("com.jmdz.fushan.pad.model.UserData");
        strategy.setSuperServiceClass("com.jmdz.fushan.base.BaseService");
        strategy.setSuperServiceImplClass("com.jmdz.fushan.base.BaseSvervice");*/


        strategy.setInclude(scanner("表名"));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityColumnConstant(true);
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
    /*mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    TemplateConfig tc = new TemplateConfig();
    tc.setController("/wtemplates/controller.java");
    tc.setService("/wtemplates/service.java");
    tc.setServiceImpl("/wtemplates/serviceImpl.java");
    tc.setEntity("/wtemplates/entity.java");
    tc.setMapper("/wtemplates/mapper.java");
    tc.setXml("/wtemplates/mapper.xml");
    mpg.setTemplate(tc);*/
        mpg.execute();
    }

    private static void setTemplateMapper(AutoGenerator mpg, String models) {
        String modulesUp = models.substring(0, 1).toUpperCase() + models.substring(1);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("modulesName", modulesName);
                map.put("modulesApi", models);
                map.put("modules", modulesUp);
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);
    }
}
