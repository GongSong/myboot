package com.yh.kuangjia;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.yh.kuangjia.util.NameExchangeUtil;
import com.yh.kuangjia.util.ResourceUtil;

import java.util.*;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class MybatisPlusGenerator {

    /**
     * <p>
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

    public static void main(String[] args) {
        String[] split = scanner("表名，多个英文逗号分割").split(",");
        List<String> strings = Arrays.asList(split);
        strings.forEach(o->{
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();
            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/src/main/java");
            gc.setAuthor("任性");
            gc.setOpen(false);
            gc.setEntityName(NameExchangeUtil.UnderlineToHump(o));
            gc.setMapperName(NameExchangeUtil.UnderlineToHump(o) + "Mapper");
            gc.setControllerName(NameExchangeUtil.UnderlineToHump(o) + "Controller");
            gc.setServiceName(NameExchangeUtil.UnderlineToHump(o) + "Service");
            gc.setServiceImplName(NameExchangeUtil.UnderlineToHump(o) + "ServiceImpl");
            // gc.setSwagger2(true); 实体属性 Swagger2 注解
            mpg.setGlobalConfig(gc);
            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl(ResourceUtil.getValue("spring.datasource.url"));
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUsername(ResourceUtil.getValue("spring.datasource.username"));
            dsc.setPassword(ResourceUtil.getValue("spring.datasource.password"));
            dsc.setTypeConvert(new MySqlTypeConvert() {
                @Override
                public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                    //将数据库中datetime转换成date
                    if (fieldType.toLowerCase().contains("datetime")) {
                        return DbColumnType.DATE;
                    }
                    return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
                }
            });
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            pc.setParent("com.yh.kuangjia");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl";
            // 如果模板引擎是 velocity
            //String templatePath = "/templates/mapper.xml.vm";

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig();
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);

            //指定自定义模板路径, 位置：/resources/templates/entity2.java(或者是.vm)
            //注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
//        TemplateConfig templateConfig = new TemplateConfig()
//                .setEntity("templates/entity2.java");
//
//        //AutoGenerator mpg = new AutoGenerator();
//        //配置自定义模板
//        mpg.setTemplate(templateConfig);

            /**
             * 包配置
             */
            mpg.setPackageInfo(new PackageConfig()
                    .setParent("com.yh.kuangjia")// 自定义包路径
                    .setController("controller")// 这里是控制器包名，默认 web
                    .setEntity("entity")
                    .setMapper("dao")
                    .setService("services")
                    .setServiceImpl("services.Impl")
            );


            /**
             * 注入自定义配置
             */
            // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
            InjectionConfig abc = new InjectionConfig() {
                @Override
                public void initMap() {
                    Map<String, Object> map = new HashMap<>();
                    map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                    this.setMap(map);
                }
            };

            // 策略配置

            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.no_change);  //设置为陀峰命名
            strategy.setColumnNaming(NamingStrategy.no_change);
            strategy.setEntityBuilderModel(true);
            strategy.setCapitalMode(true);
            strategy.setSuperEntityClass("com.yh.kuangjia.base.BaseEntity");
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            // 公共父类
            strategy.setSuperControllerClass("com.yh.kuangjia.core.BaseController");
            // 写于父类中的公共字段
            strategy.setSuperEntityColumns("id");
            strategy.setInclude(o);
            strategy.setControllerMappingHyphenStyle(false);//驼峰转连字符
            //自动将数据库中表名为 user_info 格式的专为 UserInfo 命名
            strategy.setTablePrefix(pc.getModuleName() + "_");
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();
        });
    }

}
