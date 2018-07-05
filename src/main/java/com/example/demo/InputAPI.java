package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InputAPI {

    @RequestMapping(
            path = "/input",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public Map<String, String> input(@RequestBody Map<Object, Object> json) throws IOException {
        try(RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("elasticsearch", 9200, "http")))) {

            IndexRequest indexRequest = new IndexRequest("incomingmessages", "doc")
                    .source(json);

            IndexResponse response = client.index(indexRequest);
            Map<String, String> output = new HashMap<>();
            output.put("id", response.getId());
            output.put("status", response.status().toString());
            return output;
        }



    }
}
