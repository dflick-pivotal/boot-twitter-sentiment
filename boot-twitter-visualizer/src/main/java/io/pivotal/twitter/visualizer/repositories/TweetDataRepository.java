package io.pivotal.twitter.visualizer.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import io.pivotal.twitter.model.TweetData;

//import feign.RequestLine;
 

	@RepositoryRestResource(collectionResourceRel = "tweet_data", path = "tweet_data")
	public interface TweetDataRepository extends PagingAndSortingRepository<TweetData, Long> {

//	    @RequestLine("GET /TweetData?count={size}&page={page}&sorting.Sentiment={sort}")
//	    Page<TweetData> findAll(@Named("page") Integer page, @Named("size") Integer size);
	    Page<TweetData> findAll(Pageable pageable);

	    
	    @RestResource(path = "tweetContains", rel = "tweetContains")
		Page<TweetData> findByTweetContainsIgnoreCase(@Param("q") String tweet, Pageable pageable);

		@RestResource(path = "sentiment", rel = "sentiment")
		Page<TweetData> findBySentiment(@Param("q") int sentiment, Pageable pageable);
		
		@RestResource(path = "top", rel = "top")
		List<TweetData> findFirst20ByOrderById();
	}
	
