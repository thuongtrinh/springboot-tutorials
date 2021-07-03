package com.txt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.txt.wsdl.AddArticleResponse;
import com.txt.wsdl.ArticleInfo;
import com.txt.wsdl.DeleteArticleResponse;
import com.txt.wsdl.GetAllArticlesResponse;
import com.txt.wsdl.GetArticleByIdResponse;
import com.txt.wsdl.ServiceStatus;
import com.txt.wsdl.UpdateArticleResponse;

@SpringBootApplication
public class MySpringApplicationClient {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(MySpringApplicationClient.class, args);
	}

	@Bean
	CommandLineRunner lookup(ArticleClient articleClient) {
		System.out.println(articleClient.hashCode());
		String[] beans = appContext.getBeanDefinitionNames();
		for (String bean : beans) {
			System.out.println("Bean name: " + bean);
			Object object = appContext.getBean(bean);
			System.out.println("Bean object:" + object);
		}

		return args -> {
			System.out.println("--- Get Article by Id ---");
			GetArticleByIdResponse articleByIdResponse = articleClient.getArticleById(1);
			ArticleInfo articleInfo = articleByIdResponse.getArticleInfo();
			System.out.println(articleInfo.getArticleId() + ", " + articleInfo.getTitle() + ", " + articleInfo.getCategory());

			System.out.println("--- Get all Articles ---");
			GetAllArticlesResponse allArticlesResponse = articleClient.getAllArticles();
			allArticlesResponse.getArticleInfo().stream()
					.forEach(e -> System.out.println(e.getArticleId() + ", " + e.getTitle() + ", " + e.getCategory()));

			System.out.println("--- Add Article ---");
			String title = "Spring cloud";
			String category = "microservice";
			AddArticleResponse addArticleResponse = articleClient.addArticle(title, category);
			articleInfo = addArticleResponse.getArticleInfo();
			if (articleInfo != null) {
				System.out.println(articleInfo.getArticleId() + ", " + articleInfo.getTitle() + ", " + articleInfo.getCategory());
			}
			ServiceStatus serviceStatus = addArticleResponse.getServiceStatus();
			System.out.println("StatusCode: " + serviceStatus.getStatusCode() + ", Message: " + serviceStatus.getMessage());

			System.out.println("--- Update Article ---");
			articleInfo = new ArticleInfo();
			articleInfo.setArticleId(1);
			articleInfo.setTitle("Update:Java Concurrency");
			articleInfo.setCategory("Java");
			UpdateArticleResponse updateArticleResponse = articleClient.updateArticle(articleInfo);
			serviceStatus = updateArticleResponse.getServiceStatus();
			System.out.println("StatusCode: " + serviceStatus.getStatusCode() + ", Message: " + serviceStatus.getMessage());
			System.out.println("--- Delete Article ---");
			long articleId = 3;
			DeleteArticleResponse deleteArticleResponse = articleClient.deleteArticle(articleId);
			serviceStatus = deleteArticleResponse.getServiceStatus();
			System.out.println("StatusCode: " + serviceStatus.getStatusCode() + ", Message: " + serviceStatus.getMessage());
		};
	}
}