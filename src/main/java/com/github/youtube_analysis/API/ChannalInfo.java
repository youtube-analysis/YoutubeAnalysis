package com.github.youtube_analysis.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.youtube_analysis.API.youtube.entities.ActivityResponce;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Vitaly Kurotkin on 06.10.2017.
 */
public class ChannalInfo {
    public ChannalInfo(){
        Unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public ActivityResponce getResult(String searchText, String key) throws ExecutionException, InterruptedException {
        if (searchText.equals("")) return null;
        return getSearchHttpAsyncResponseInJson(searchText, key).getBody();
    }

    private HttpResponse<ActivityResponce> getSearchHttpAsyncResponseInJson(String searchText, String key) throws ExecutionException, InterruptedException {
        Future<HttpResponse<ActivityResponce>> future = Unirest.get("https://www.googleapis.com/youtube/v3/search")
                .queryString("type", "video")
                .queryString("q", searchText)
                .queryString("maxResults", "10")
                .queryString("part", "snippet")
                .queryString("key", key)
                .asObjectAsync(ActivityResponce.class);

        return future.get();
    }
}
