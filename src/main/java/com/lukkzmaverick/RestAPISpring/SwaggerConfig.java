package com.lukkzmaverick.RestAPISpring;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiConfigDocs() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.lukkzmaverick.RestAPISpring.resources"))
				.paths(PathSelectors.any()).build();
	}
	
	private ApiInfo infodocs() {
		return new ApiInfo("Title - Rest API Spring", "Description", "1.0", "Terms", 
				new Contact("Lucas", "https://google.com", "lukkzmaverick@gmail.com"), "MIT", "url",
				new ArrayList<VendorExtension>());
	}
}
