package com.test.pruebaback.configuracion;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final Gson gson;

    @Autowired
    public AppConfig(Gson gson) {
        this.gson = gson;
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter(gson);
        converters.add(messageConverter);
    }
}
