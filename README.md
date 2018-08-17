# news-trends-rest-api

The project implements the RESTful API that makes the news (that was saved by RSS listener) available to the outside world.

## Prerequisites

You need to have ```sbt``` and ```docker-compose``` installed on your machine.

## How to run

First go to the RSS listener (either ```news-trends-broadcast``` or ```news-trends-co-partition``` project) and issue the following command:

```
sbt assembly
```

It will create the assembly jar. After that start containers using docker-compose:

```
docker-compose up
```

The command tells docker-compose to start containers listed in ```docker-compose.yml``` file. Those are: rss listener and mongodb database.

Next go to the ```news-trends-rest-api``` project's (i.e. this project's) root directory and do exactly the same:

```
sbt assembly
docker-compose up
```
It will start the container for the RESTful API.
