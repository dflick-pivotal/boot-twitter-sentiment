# boot-twitter-sentiment

Project: Pivotal CF natural language processing (NLP) of twitter tweets.

![GitHub Logo](/images/twitter.png)

boot-twitter-feeder and boot-twitter-visualizer spring boot applications.

[boot-twitter-feeder](/boot-twitter-feeder):
- [queries twitter](https://dev.twitter.com/rest/public/search) ( ex. for tweets with a certain hashtag)
- tweet sentiment analysis with [CoreNLP](http://nlp.stanford.edu/software/corenlp.shtml) library
- sends the tweet sentiment analysis result to a db and to rabbit
- spring integration-graph
![GitHub Logo](/images/feeder-spring-integration.png)

[boot-twitter-visualizer](/boot-twitter-visualizer):
- receives the tweet sentiment analysis via rabbit and sends it in realtime via websocket to all connected browsers.
- visualizes the sentiment analysis result in realtime in a dashboard view
- visualizes the sentiment analysis result in a report view  

# Twitter Limitations
Please, be aware that the number of tweets allowed to gather per hour from twitter is per default limited.
After the limit is reached no more tweets are gathered for some time.

# Prerequisites

Please, use your own twitter credentials. You get them here: https://apps.twitter.com/app/new

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
```
export twitter_query='#greenday'
export twitter_oauth_consumerKey=**ENTER YOUR** Consumer Key
export twitter_oauth_consumerSecret=**ENTER YOUR** Consumer Secret
export twitter_oauth_accessToken=**ENTER YOUR** Access Token
export twitter_oauth_accessTokenSecret=**ENTER YOUR** AccessTokenSecret
```
- mvn spring-boot:run


# Concourse

This branch contains a complete [concourse](http://concourse.ci) pipeline. Please find the relevant pipeline in [pipeline.yml](/ci/pipeline.yml).
In the [ci](/ci) directory you'll also find an [example](/ci/params-example.yml) params file as well as one prepared for [pcfdev](/ci/params-pcfdev.yml). 

Please make a copy of [params-sample.yml](/ci/params-sample.yml) and rename it to `params.yml`. This will ensure your credentials do not get checked in, as params.yml is *not* being tracked by git.
Adjust `params.yml` to your needs, do not forget to fill in your twitter credentials and the `twitter_query` as the pipeline will not read it from the [manifest-ci.yml](/manifest-ci.yml).

You can then login to your target with `fly -t lite login` and apply your pipeline with `fly -t lite set-pipeline -p boot-twitter-sentiment -c ci/pipeline.yml --load-vars-from ci/params.yml`. You might need to unpause your pipeline, but you're ready to take off!
