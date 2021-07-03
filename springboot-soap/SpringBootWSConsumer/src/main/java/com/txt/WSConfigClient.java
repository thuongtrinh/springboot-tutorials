package com.txt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfigClient {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.txt.wsdl");
		return marshaller;
	}

	@Bean
	public ArticleClient articleClient(Jaxb2Marshaller marshaller) {
		ArticleClient client = new ArticleClient();
		client.setDefaultUri("http://localhost:8080/soapws/articles.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
