package <%= packageName %>.adapters.outbound.integration;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.opensea.api.model.Collections;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class <%= entityName %>Integration {
	Gson g = new Gson();
	
    private WebClient webClient;

		
	
	public List<Collections> get<%= entityName %>s(String id) throws SSLException {
		String retorno = webClient.get()
				.uri(uriBuilder -> uriBuilder
					    .path("/api/v1/collections")
					    .build())
		.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		.retrieve()
		.bodyToMono(String.class).block();
		System.out.println(retorno);
		Collections[] collectionsArray = g.fromJson(retorno, Collections[].class);  
		JsonArray jsonObject = g.fromJson( retorno, JsonArray.class);

		String collection = g.toJson(jsonObject);
		System.out.println(collection);

		List<Collections> collect = Arrays.asList(collectionsArray) ;
		System.out.println(collect.get(0).banner_image_url);
		return collect;
	}
	
	public void get<%= entityName %>() throws SSLException {
		String retorno = webClient.get()
				.uri(uriBuilder -> uriBuilder
					    .path("/api/v1/collections")
					    .build())
		.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		.retrieve()
		.bodyToMono(String.class).block();
		JsonArray jsonObject = g.fromJson( retorno, JsonArray.class);
		System.out.println(jsonObject);
		
	}
}
