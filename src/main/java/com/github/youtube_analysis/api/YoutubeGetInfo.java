package com.github.youtube_analysis.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.youtube_analysis.Utils.APIKey;
import com.github.youtube_analysis.api.youtube.channal.InfoResponce;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Vitaly on 08.10.17.
 */
public class YoutubeGetInfo {
    private String key;

    public YoutubeGetInfo(){
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
        key = APIKey.INSTANCE.getKey();
    }

    public InfoResponce getChannalInfo(String id) throws ExecutionException, InterruptedException {
        if (id.equals("")) return null;
        return gethannalInfoAsyncResponseInJson(id, key).getBody();
    }

    private HttpResponse<InfoResponce> gethannalInfoAsyncResponseInJson(String id, String key) throws ExecutionException, InterruptedException {
        Future<HttpResponse<InfoResponce>> future = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                .queryString("part", "snippet,contentDetails,statistics")
                .queryString("id", id)
                .queryString("key", key)
                .asObjectAsync(InfoResponce.class);
        return future.get();
    }
}
