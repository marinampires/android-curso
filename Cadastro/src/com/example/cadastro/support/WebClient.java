package com.example.cadastro.support;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {

	public String post(String json, String url){
		String jsonResposta = "";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new StringEntity(json));
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
			
			HttpResponse response = httpClient.execute(post);
			jsonResposta = EntityUtils.toString(response.getEntity());
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return jsonResposta;
	}
}
