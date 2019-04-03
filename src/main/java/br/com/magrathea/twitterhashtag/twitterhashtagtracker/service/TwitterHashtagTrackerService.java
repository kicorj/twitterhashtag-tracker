package br.com.magrathea.twitterhashtag.twitterhashtagtracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.magrathea.twitterhashtag.twitterhashtagtracker.domain.Tweet;
import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class TwitterHashtagTrackerService {
	
    @Value("${twitterhashtag.api.endpoint.tweet}")
    private String tweetEndpoint;
    @Value("${twitterhashtag.tracker.maxresults}")
    private int maxresults;
    @Value("${twitterhashtag.tracker.pagelimit}")
    private int pagelimit;
    
	public void capture(String term) {
		
//		int requestCount = 0;
//		int resultCount = 0;
//		int foundCount = 0;
		
		QueryResult result = null;
		
		try {
			
			Twitter twitter = TwitterFactory.getSingleton();
			twitter.getRateLimitStatus();
		    Query query = new Query(term);
		    query.setCount(maxresults);
		    query.setResultType(ResultType.mixed);
		    
		    int page = 1;
		    do {
//		    requestCount++;
			result = twitter.search(query);
//			resultCount += result.getTweets().size();
		    for (Status status : result.getTweets()) {
		    	for (HashtagEntity ht : status.getHashtagEntities()) {
		    		if (term.equalsIgnoreCase(ht.getText())) {
//		    			foundCount++;
		    			RestTemplate restTemplate = new RestTemplate();
		    		    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		    		    List<String> termList = new ArrayList<String>();
		    		    //store lowercase term
		    		    termList.add(term.toLowerCase());
		    		    Tweet t = new Tweet();
		    		    t.setAuthor(status.getUser().getScreenName());
		    		    t.setPublishDate(status.getCreatedAt());
		    		    t.setMessage(status.getText());
		    		    t.setHashtagsFound(termList);
		    		    t.setStatusId(status.getId());

		    		    HttpEntity<Tweet> request = new HttpEntity<Tweet>(t);
		    		    ResponseEntity<Tweet> response = restTemplate.postForEntity(tweetEndpoint, request, Tweet.class);

//		    		    System.out.println("Response Code: " + response.getStatusCode());
		    		}
		    	}
		    }
		    	page++;
		    	query = result.nextQuery();
		    } while (page <= pagelimit);
		    
		} catch (TwitterException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = null;

		}
		
//		System.out.println("\nTotal de requests: " + requestCount);
//		System.out.println("\nTotal de resultados: " + resultCount);
//		System.out.println("\nTotal de hastags encontradas: " + foundCount);
//		System.out.println("\nFinished all threads");
	}

}
