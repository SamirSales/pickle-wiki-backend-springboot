package io.github.samirsales.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@SuppressWarnings("unused")
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        List<ResponseMessage> defaultResponseMessageList = getDefaultResponseMessageList();
        List<Parameter> globalOperationParameterList = getGlobalOperationParameterList();

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("io.github.samirsales.controller"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(globalOperationParameterList)
            .apiInfo(apiInfo())
            .globalResponseMessage(RequestMethod.POST, defaultResponseMessageList)
            .globalResponseMessage(RequestMethod.PUT, defaultResponseMessageList)
            .globalResponseMessage(RequestMethod.GET, defaultResponseMessageList)
            .globalResponseMessage(RequestMethod.DELETE, defaultResponseMessageList)
            .globalResponseMessage(RequestMethod.PATCH, defaultResponseMessageList);
    }

    private List<ResponseMessage> getDefaultResponseMessageList(){
        return Arrays.asList(
            getResponseMessageByCodeAndMessage(401, "You are not authorized to view the resource."),
            getResponseMessageByCodeAndMessage(403, "Accessing the resource you were trying to reach is forbidden."),
            getResponseMessageByCodeAndMessage(404, "The resource you were trying to reach is not found."),
            getResponseMessageByCodeAndMessage(500, "There was an internal error.")
        );
    }

    private ResponseMessage getResponseMessageByCodeAndMessage(int code, String message){
        return new ResponseMessageBuilder().code(code).message(message).build();
    }

    private List<Parameter> getGlobalOperationParameterList(){
        return Lists.newArrayList(new ParameterBuilder()
                .name("Authorization")
                .description("Access Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        final String URL_PATH = "/doc/**";
        final String REDIRECT_URL = "/swagger-ui.html";
        registry.addRedirectViewController(URL_PATH, REDIRECT_URL).setKeepQueryParams(true);
    }

    private ApiInfo apiInfo() {
        final String TITLE = "Pickle Wiki - Backend Service";
        final String DESCRIPTION = "The Pickle Wiki is an open source encyclopedia project, written collaboratively and inspired by Wikipedia system.";
        final String VERSION = "1.0.0";
        final String TERM_SERVICE_URL = "";
        final String LICENSE = "MIT License";
        final String LICENSE_URL = "https://github.com/SamirSales/pickle-wiki-backend-springboot/blob/master/LICENSE";

        return new ApiInfo(TITLE, DESCRIPTION, VERSION, TERM_SERVICE_URL, getContact(), LICENSE, LICENSE_URL,
            Collections.emptyList());
    }

    private Contact getContact(){
        final String APP_NAME = "Pickle Wiki";
        final String REPOSITORY_URL = "https://github.com/SamirSales/pickle-wiki-backend-springboot";
        final String DEVELOPER_EMAIL = "samir.sribeiro@gmail.com";
        return new Contact(APP_NAME, REPOSITORY_URL, DEVELOPER_EMAIL);
    }
}
