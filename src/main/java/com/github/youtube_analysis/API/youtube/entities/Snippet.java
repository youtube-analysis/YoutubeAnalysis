package com.github.youtube_analysis.API.youtube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Vitaly Kurotkin on 06.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    public String channelTitle;
    public String channelId;
}
