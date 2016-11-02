# boot-twitter-sentiment

Project: Pivotal CF natural language processing (NLP) of twitter tweets.

![GitHub Logo](/images/twitter.png)

boot-twitter-feeder and boot-twitter-visualizer spring boot applications.

[boot-twitter-feeder](/boot-twitter-feeder):
- queries twitter for tweets with a certain hashtag
- tweet sentiment analysis with [CoreNLP](http://nlp.stanford.edu/software/corenlp.shtml) library
- sends the tweet sentiment analysis result to a db and to rabbit
![GitHub Logo](/images/feeder-spring-integration.png)

[boot-twitter-visualizer](/boot-twitter-visualizer):
- receives the tweet sentiment analysis via rabbit and sends it in realtime via websocket to all connected browsers.
- visualizes the sentiment analysis result in realtime in a dashboard view
- visualizes the sentiment analysis result in a report view  

# Run it on PCF

- clone the repo
- cd boot-twitter-sentiment
- sh
[create_services_pcfdev.sh](/create_services_pcfdev.sh)
- mvn package
- change the #hashtag you want to query on twitter and your twitter credentials in [manifest.yml](/manifest.yml)

```
env:
  twitter_query: '#HillaryClinton'
  twitter_oauth_consumerKey: **ENTER YOUR** Consumer Key
  twitter_oauth_consumerSecret: **ENTER YOUR** Consumer Secret
  twitter_oauth_accessToken: ENTER **YOUR Access** Token
  twitter_oauth_accessTokenSecret: **ENTER YOUR** Access Token Secret
```
- cf push

# Run it local

- rabbitmq-server
- mysql.server start
- mysql -uroot
- create database twitter;
- cd spring-boot-visualizer
- mvn spring-boot:run
- cd spring-boot-feeder
- mvn spring-boot:run
