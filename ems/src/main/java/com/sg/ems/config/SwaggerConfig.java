package com.sg.ems.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfig {

	public static final ApiInfo API_INFO_DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
	          "Contact Email", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	
	public static final HashSet<String> DEFAULT_PRODUCER_AND_CONSUMER = new HashSet<String>(Arrays.asList("application/json","application/xml"));

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(API_INFO_DEFAULT)
				.produces(DEFAULT_PRODUCER_AND_CONSUMER)
				.consumes(DEFAULT_PRODUCER_AND_CONSUMER);
	}

}
