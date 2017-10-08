package com.github.youtube_analysis.api.youtube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;

/**
 * Created by Vitaly Kurotkin on 06.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    public String channelTitle;
    public String channelId;
}
