package <%= packageName %>.adapters.outbound;

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
public class CollectionService {
	Gson g = new Gson();
	
    private WebClient webClient;

		
	
	public List<Collections> getColletions(String id) throws SSLException {
		String retorno = webClient.get()
				.uri(uriBuilder -> uriBuilder
					    .path("/api/v1/collections")
					    .queryParam("offset", 0)
					    .queryParam("limit", 300)
					    .queryParam("asset_owner", id)
					    .build())
		.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		.header(HttpHeaders.USER_AGENT , "Java/17.0.2" )
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
	
	public void getColletion() throws SSLException {
		String retorno = webClient.get()
				.uri(uriBuilder -> uriBuilder
					    .path("/api/v1/collections")
					    .queryParam("offset", 0)
					    .queryParam("limit", 300)
					    .queryParam("asset_owner", "0x59C101A12F03893AFC8AfdD049c68196dE663A12")
					    .build())
		.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		.header(HttpHeaders.USER_AGENT , "Java/17.0.2" )
		.retrieve()
		.bodyToMono(String.class).block();
		JsonArray jsonObject = g.fromJson( retorno, JsonArray.class);
		System.out.println(jsonObject);
		
	}
}
