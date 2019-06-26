package com.food.delivery.app.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
	
	Contact contact = new Contact(
			"Food-Boot", 
			"https://food-boot.github.io/Food-Delivery-Web-App-BackEnd-ScpringBoot", 
			"shehandvid@gmail.com");
	
	List<VendorExtension> vendorExtensions = new ArrayList<>();
	
	ApiInfo apiInfo = new ApiInfo(
			"Food Delivery Web App - RESTful web services documentation",
			"This pages documents all the available RESTful Web Service endpoints", 
			"1.0.0", 
			"https://github.com/food-boot/Food-Delivery-Web-App-BackEnd-ScpringBoot",
			contact, 
			"Apache-2.0", 
			"https://opensource.org/licenses/Apache-2.0", 
			vendorExtensions);

	@Bean
	public Docket apiDocket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.protocols(new HashSet<>(Arrays.asList("HTTP","HTTPS")))
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.food.delivery.app.ws"))
				.paths(PathSelectors.any())
				.build();
		
		return docket;
	}
}
