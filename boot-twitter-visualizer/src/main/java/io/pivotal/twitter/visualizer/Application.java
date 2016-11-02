package io.pivotal.twitter.visualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.cloudfoundry.CloudFoundryConnector;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EntityScan(basePackages = "io.pivotal.twitter.model")
@ImportResource("spring-context.xml")

public class Application {

    public static void main(String[] args) {
    	 
        if (new CloudFoundryConnector().isInMatchingCloud()) {
            System.setProperty("spring.profiles.active", "cloud");
            
        }else{
        	System.setProperty("spring.profiles.active", "dev");
        }

    	
        SpringApplication.run(Application.class, args);
    }
}
 