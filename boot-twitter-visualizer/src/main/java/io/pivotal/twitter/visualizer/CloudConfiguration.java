package io.pivotal.twitter.visualizer;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("cloud")
@Configuration
public class CloudConfiguration {

	@Bean
	Cloud getCloud()
	{
    CloudFactory cloudFactory = new CloudFactory();
    return cloudFactory.getCloud();
	}
}
