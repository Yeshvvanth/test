package com.qlick.palindromeassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;







/**
 * 
 * * <h1>Generate API Documentation!</h1>
 * The program generates a document based on the details specified.
 * @author yeshwanthreddygaddam
 *	
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT
						= new Contact(
						"Yeshwanth",
						"",
						"yeshwanth.reddy06@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO
						= new ApiInfo(
						"To check whether a message is palindrome or not",
						"Palindrome API",
						"1.0",
						"urn:tos",
						DEFAULT_CONTACT,
						"MIT",
						"https://opensource.org/licenses/MIT",
						new ArrayList<>());
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet(Arrays.asList("application/json"));

//	Docket Bean
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.useDefaultResponseMessages(false);
	}
	
	
	@Primary
	@Bean
	public LinkDiscoverers discoverers() {
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

	
	
	

}
