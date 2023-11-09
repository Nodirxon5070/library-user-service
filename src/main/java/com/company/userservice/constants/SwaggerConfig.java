package com.company.userservice.constants;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedByUserAPI(){
        String[] array = new String[]{"/user/create","/user/get/{id}","/user/update/{id}","/user/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("OrdersBooks")
                .pathsToMatch(array)
                .build();
    }
    @Bean
    public GroupedOpenApi groupedByCardAPI(){
        String[] array = new String[]{"/card/create","/card/get/{id}","/card/update/{id}","/card/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("OrdersBooks")
                .pathsToMatch(array)
                .build();
    }


}
