package com.txt.endpoint;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.txt.entity.Article;
import com.txt.entity.ArticleXml;
import com.txt.service.IArticleService;

@Component
@Path("/article")
public class ArticleEndPoint {

	private static final Logger logger = LoggerFactory.getLogger(ArticleEndPoint.class);

//	@Produces : determine data type return (response).
//	@Consumes : determine data type send request (request).

	@Autowired
	private IArticleService articleService;

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleDetails(){
		List<Article> list = articleService.getAllArticles();
		
		/*List<ArticleXml> lst = new ArrayList<>();
		list.forEach(a-> {
			ArticleXml xml = new ArticleXml();
			BeanUtils.copyProperties(a, xml);
			lst.add(xml);
		});*/
		
		logger.info("Get list article");
		return Response.ok(list).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleById(@PathParam("id") Integer id) {
		Article article = articleService.getArticleById(id);

		logger.info("Get article by id: " + id);
		return Response.ok(article).build();
	}

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {
		boolean isAdd = articleService.addArticle(article);
		if(!isAdd) {
			logger.info("Article already exits.");
			Response.status(Status.CONFLICT).build();
		}
		
		return Response.created(URI.create("/jersey/article/" + article.getArticleId())).build();
	}

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateArticle(Article article){
		articleService.updateArticle(article);
		return Response.ok(article).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticle(@PathParam("id") Integer id){
		articleService.deleteArticle(id);
		return Response.noContent().build();
	}

}
