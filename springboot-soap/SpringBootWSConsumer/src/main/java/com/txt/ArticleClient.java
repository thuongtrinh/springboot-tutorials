package com.txt;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.txt.wsdl.AddArticleRequest;
import com.txt.wsdl.AddArticleResponse;
import com.txt.wsdl.ArticleInfo;
import com.txt.wsdl.DeleteArticleRequest;
import com.txt.wsdl.DeleteArticleResponse;
import com.txt.wsdl.GetAllArticlesRequest;
import com.txt.wsdl.GetAllArticlesResponse;
import com.txt.wsdl.GetArticleByIdRequest;
import com.txt.wsdl.GetArticleByIdResponse;
import com.txt.wsdl.UpdateArticleRequest;
import com.txt.wsdl.UpdateArticleResponse;

public class ArticleClient extends WebServiceGatewaySupport {
	public GetArticleByIdResponse getArticleById(long articleId) {
		GetArticleByIdRequest request = new GetArticleByIdRequest();
		request.setArticleId(articleId);
		GetArticleByIdResponse response = (GetArticleByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/getArticleByIdRequest"));
		return response;
	}

	public GetAllArticlesResponse getAllArticles() {
		GetAllArticlesRequest request = new GetAllArticlesRequest();
		GetAllArticlesResponse response = (GetAllArticlesResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/getAllArticlesRequest"));
		return response;
	}

	public AddArticleResponse addArticle(String title, String category) {
		AddArticleRequest request = new AddArticleRequest();
		request.setTitle(title);
		request.setCategory(category);
		AddArticleResponse response = (AddArticleResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://localhost:8080/soapws/addArticleRequest"));
		return response;
	}

	public UpdateArticleResponse updateArticle(ArticleInfo articleInfo) {
		UpdateArticleRequest request = new UpdateArticleRequest();
		request.setArticleInfo(articleInfo);
		UpdateArticleResponse response = (UpdateArticleResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://localhost:8080/soapws/updateArticleRequest"));
		return response;
	}

	public DeleteArticleResponse deleteArticle(long articleId) {
		DeleteArticleRequest request = new DeleteArticleRequest();
		request.setArticleId(articleId);
		DeleteArticleResponse response = (DeleteArticleResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://localhost:8080/soapws/deleteArticleRequest"));
		return response;
	}
}
