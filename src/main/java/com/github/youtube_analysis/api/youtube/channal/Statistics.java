package com.github.youtube_analysis.api.youtube.channal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Vitaly Kurotkin on 08.10.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistics {
    public String subscriberCount;
    public String videoCount;
    public String viewCount;
}
