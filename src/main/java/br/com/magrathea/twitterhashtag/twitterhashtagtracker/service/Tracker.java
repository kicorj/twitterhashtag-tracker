package br.com.magrathea.twitterhashtag.twitterhashtagtracker.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.magrathea.twitterhashtag.twitterhashtagtracker.domain.Hashtag;

@Service
@Component
public class Tracker {
	
	@Autowired
	private TwitterHashtagTrackerService twitterHashtagTrackerService;
	
	@Value("${twitterhashtag.api.endpoint.hashtag}")
    private String hashtagEndpoint;
	
	@PostConstruct
	@Scheduled(fixedDelay = 600000)
    public void run() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String jsonResponse = restTemplate.getForObject(hashtagEndpoint,
		          String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		//JSON from String to Object
		List<Hashtag> hashtags = mapper.readValue(jsonResponse, new TypeReference<List<Hashtag>>(){});
		
		for (Hashtag hastag : hashtags) {
//			System.out.println("#### start: " + new Date());
	        twitterHashtagTrackerService.capture(hastag.getHashtag());
		}
    }

}
