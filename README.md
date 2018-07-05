# Elasticsearch/Spring-Boot demo

## Requirements

```
$ docker --version
Docker version 18.03.1-ce, build 9ee9f40
```

```
$ docker-compose --version
docker-compose version 1.21.2, build a133471
```

## Run services

```
docker-compose up
```

## Input data to API

```
curl -D - -H "Content-Type: application/json" -X POST --data '{"foo" : "bar"}' localhost:8080/input
```

## Inspect data from Elasticsearch

```
curl -D - localhost:9200/incomingmessages/_search
```
