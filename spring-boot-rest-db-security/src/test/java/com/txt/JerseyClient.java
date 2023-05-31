package com.txt;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.txt.entity.Article;

public class JerseyClient {

	private static String url = "http://localhost:8080/jersey/article/";

	public void getAllArticle(){
		// Create authentication user-pass
		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("thuongtx", "a123456");
		clientConfig.register( feature) ;
		clientConfig.register(JacksonFeature.class);

		//Create client
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget base = client.target(url);
		WebTarget target = base.path("all");

		//Get
		List<Article> list = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Article>>() {});

		list.stream().forEach(article -> System.out
				.println(article.getArticleId() + ", " + article.getTitle() + ", " + article.getCategory()));
		client.close();
	}

	public void getArticleById(int articleId) {
		// Create authentication user-pass
		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("thuongtx", "a123456");
		clientConfig.register( feature) ;
		clientConfig.register(JacksonFeature.class);

		//Create client
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget base = client.target(url);
		WebTarget target = base.path("{id}").resolveTemplate("id", articleId);

		//Get
		Article article = target.request(MediaType.APPLICATION_JSON).get(Article.class);

		System.out.println(article.getArticleId() + ", " + article.getTitle() + ", " + article.getCategory());

		client.close();
	}

	public void addArticle(Article article) {
		// Create authentication user-pass
		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("thuongtx", "a123456");
		clientConfig.register( feature) ;
		clientConfig.register(JacksonFeature.class);

		//Create client
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget base = client.target(url);
		WebTarget target = base.path("add");

		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(article));
		System.out.println("Response Http Status: " + response.getStatus());
		System.out.println(response.getLocation());

//		Response Http Status: 201
//		http://localhost:8080/jersey/article/21
		client.close();
	}

	public void updateArticle(Article article) {
		// Create authentication user-pass
		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("thuongtx", "a123456");
		clientConfig.register( feature) ;
		clientConfig.register(JacksonFeature.class);

		//Create client
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget base = client.target(url);
		WebTarget target = base.path("update");

		Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(article));
		System.out.println("Response Http Status: " + response.getStatus());
		System.out.println(response.getLocation());
		Article resArticle = response.readEntity(Article.class);
		System.out.println(resArticle.getArticleId() + ", " + resArticle.getTitle() + ", " + resArticle.getCategory());

		client.close();
	}

	public void deleteArticle(int articleId) {
		// Create authentication user-pass
		ClientConfig clientConfig = new ClientConfig();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("thuongtx", "a123456");
		clientConfig.register( feature) ;
		clientConfig.register(JacksonFeature.class);

		//Create client
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget base = client.target(url);
		WebTarget target = base.path("{id}").resolveTemplate("id", articleId);
		
		Response response = target.request(MediaType.APPLICATION_JSON).delete();
		
		System.out.println("Response Http Status: " + response.getStatus());
		if (response.getStatus() == 204) {
			System.out.println("Data deleted successfully.");
		}

		client.close();
	}

	public static void main(String [] args) {
		JerseyClient jerseyClient = new JerseyClient();
		jerseyClient.getAllArticle();
//		jerseyClient.getArticleById(2);

		//Add new
//		Article article = new Article();
//		article.setCategory("Angular");
//		article.setTitle("Pipe");
//		jerseyClient.addArticle(article);

		//Update
//		Article articleUpd = new Article();
//		articleUpd.setArticleId(14);
//		articleUpd.setCategory("CateUpdate");
//		articleUpd.setTitle("TitleUpdate");
//		jerseyClient.updateArticle(articleUpd);

		//Delete
//		jerseyClient.deleteArticle(19);
	}
}
