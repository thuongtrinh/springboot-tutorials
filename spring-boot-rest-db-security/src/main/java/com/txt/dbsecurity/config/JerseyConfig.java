package com.txt.dbsecurity.config;

import javax.ws.rs.ApplicationPath;

import com.txt.dbsecurity.endpoint.ArticleEndPoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ArticleEndPoint.class);
	}

}
