package com.ruoyi.framework.config;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${ruoyi.profile}")
    private String mImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(mImagesPath.equals("") || mImagesPath.equals("${ruoyi.profile}")){
            String imagesPath = com.ruoyi.framework.config.WebAppConfig.class.getClassLoader().getResource("").getPath();
            if(imagesPath.indexOf(".jar")>0){
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            }else if(imagesPath.indexOf("classes")>0){
                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/images/";
            mImagesPath = imagesPath;
        }
        LoggerFactory.getLogger(com.ruoyi.framework.config.WebAppConfig.class).info("imagesPath="+mImagesPath);
        System.out.println("mImagesPath"+mImagesPath);
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + mImagesPath);
        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
    }
}