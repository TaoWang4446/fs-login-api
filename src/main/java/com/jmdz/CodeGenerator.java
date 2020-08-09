package com.jmdz;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        // 模块名 product
        String moduleName = "";

        // 1.创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2. 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取项目目录
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        // 文档注释 作者名
        gc.setAuthor("Wangshengtao");
        // 是否打开资源管理器
        gc.setOpen(false);

        // 重新生成文件的时候是否覆盖
        gc.setFileOverride(true);
        // 去掉接口的首字母I
        gc.setServiceImplName("%sService");
        // 主键策略
        gc.setIdType(IdType.AUTO);
        // 定义生成的实体类中的日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        // 3、 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=FMIS_YanTaiFuShan0720");
        // mysql 驱动为5.x时的driverName
        // dsc.setDriverName("com.mysql.jdbc.Driver");
        // mysql 驱动为8.x时的driverName
        dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc.setUsername("sa");
        dsc.setPassword("123456");
        // 设置数据库类型(可选) 可以根据驱动自动推断
        dsc.setDbType(DbType.SQL_SERVER);

        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        //模块名 ：com.jmdz.fushan
        pc.setModuleName(moduleName);
        pc.setParent("com.jmdz.fushan");
        // 最终的包名称  com.jmdz.fushan.pad.controller,其他类似
        pc.setController("pad.controller");
        pc.setMapper("dao");
        pc.setService("pad.service");
        pc.setEntity("pad.model");

        mpg.setPackageInfo(pc);

        //5、 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置要映射的表名
        strategy.setInclude("f_Area");
        // 数据库表映射到实体类的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //代表前缀不生成
        strategy.setTablePrefix("f_");
        //链式编程
        strategy.setEntityLombokModel(true);
        // 数据库列映射到实体类的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //逻辑删除字段
        strategy.setLogicDeleteFieldName("deleted");
        //去掉布尔值的 is_ 前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);

        // 自动填充,策略
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);

        strategy.setTableFillList(tableFills);

        // 乐观锁
        strategy.setVersionFieldName("version");
        // restful api风格
        strategy.setRestControllerStyle(true);
        // URL中驼峰转连字符  addProduct add-product
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        // 执行代码生成器
        mpg.execute();
    }
}
