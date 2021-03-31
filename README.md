# Spring Boot app that consumes a REST API and publishes the data to Kafka.

## REST APIs
This app is prepared to consume data from both `https://quoters.apps.pcfone.io/api/random` and `https://jsonplaceholder.typicode.com`.
The first one will return a radon quote about Spring and the second will return a list of users.

Note that to use one or the other you have to comment out (or uncomment) the base URL in `application.properties`.

```
# rest.api.base.url=https://quoters.apps.pcfone.io/api/random
rest.api.base.url=https://jsonplaceholder.typicode.com
```
and in `SpringRestToKafkaApplication`
```java
		log.info("Get Spring Boot quote: {}",
		restService.getQuoteSync().getValue().getQuote());

		log.info("---- Get users synchronously");
		List<User> users = restService.getJsonPlaceholderUsers().collectList().block();
		users.forEach(user -> {
			log.info("Id: {}, Name: {}", user.getId(), user.getName());
		});
```





