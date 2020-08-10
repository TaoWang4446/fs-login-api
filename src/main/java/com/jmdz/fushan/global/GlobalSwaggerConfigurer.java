package com.jmdz.fushan.global;

import com.jmdz.fushan.model.config.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * GlobalSwaggerConfigurer
 *
 * @author LiCongLu
 * @date 2020-07-08 10:50
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class GlobalSwaggerConfigurer {


    /**
     * 描述
     */
    private static String description = "济南金民电子科技有限公司";

    /**
     * 版本号
     */
    private static String version = "v1.0.0";

    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("烟台福山接口文档")
                .description(description)
                .version(version)
                .build();

        return createDocket("pad-api", apiInfo, "com.jmdz.fushan");
    }

    /**
     * 生成一个Docket
     *
     * @param groupName   分组名
     * @param apiInfo     api信息
     * @param basePackage 基础包名
     * @return
     */
    private Docket createDocket(String groupName, ApiInfo apiInfo, String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(getParameters());
    }

    /**
     * 添加header参数
     *
     * @return
     */
    private List<Parameter> getParameters() {
        List<Parameter> parameters = new ArrayList<>();

        // token header
        String token = Constants.AUTHORIZATION;
        parameters.add(getParameterForHeader(token, token + "令牌", "string", false));

        return parameters;
    }

    /**
     * 生成参数
     *
     * @param name
     * @param description
     * @param type
     * @param required
     * @return
     */
    private Parameter getParameterForHeader(String name, String description, String type, boolean required) {
        ParameterBuilder builder = new ParameterBuilder();
        builder.name(name)
                .description(description)
                .modelRef(new ModelRef(type))
                .parameterType("header")
                .required(required)
                .build();
        return builder.build();
    }

}
