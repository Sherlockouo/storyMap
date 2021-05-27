package com.storymap.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/20 14:17
 */
@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Value("${files.path.picupload}")
    private String imagesPath;

    @Value("${files.path.viupload}")
    private String videoPath;

    @Value("${files.path.otherupload}")
    private String othersPath;


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        log.info("registry {}",registry);
        //将所有/images/** 访问都映射到classpath:/images/ 目录下
        registry.addResourceHandler("/files/images/**").addResourceLocations(imagesPath);
        registry.addResourceHandler("/files/videos/**").addResourceLocations(videoPath);
        registry.addResourceHandler("/files/others/**").addResourceLocations(othersPath);
//        registry.addResourceHandler("/files/others/**").addResourceLocations("classpath:/files/others/");
        log.info("shit! mvc");
        super.addResourceHandlers(registry);
    }

    /**
     * set the put filter
     */
    @Component
    class PutFilter extends HttpPutFormContentFilter {
    }

}
