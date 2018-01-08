cf cs p-mysql 512mb db
cf cs p-rabbitmq standard rabbit
cf cups sentimentr -p '{"url":"http://text-processing.com/api/sentiment/"}'
