package com.ruoyi.system;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.util.Collections;

/**
 * @Author DU425
 * @Date 2023/4/9 17:06
 * @Version 1.0
 * @Description
 */
public class MyBatisApplication {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/ry-cloud",
            "root",
            "root");


    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig(builder ->
                        builder.author("Du425")
                                .fileOverride()
                                .enableSwagger()
                                .dateType(DateType.TIME_PACK)
                                .commentDate("yyyy-MM-dd")
                                .outputDir("D://java-code/RuoYi-Cloud/ruoyi-modules/ruoyi-system/src/main/java/com/ruoyi/system"))
                // 包配置
                .packageConfig(builder ->
                        builder.parent("com.ruoyi.system")
                                .service("service")
                                .serviceImpl("service.impl")
                                .mapper("mapper")
                                .controller("controller")
                                .entity("domain")
                                .other("other")
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://java-code/RuoYi-Cloud/ruoyi-modules/ruoyi-system/src/main/resources/mapper/system")))
                // 策略配置
                .strategyConfig(builder -> builder.addInclude("sys_order_type"))


                .execute();
    }
}
