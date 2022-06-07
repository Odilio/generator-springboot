package <%= packageName %>.adapters.config;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfiguration {

	@Bean
	public WebClient createWebClient() throws SSLException {
		final int size = 16 * 1024 * 1024;
	    final ExchangeStrategies strategies = ExchangeStrategies.builder()
	        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
	        .build();
	    return WebClient.builder()
        .baseUrl("<%= uri %>")
        .exchangeStrategies(strategies)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
	}
		
}
