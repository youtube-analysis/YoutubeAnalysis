package com.github.youtube_analysis.api.youtube.channal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Vitaly Kurotkin on 08.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {
    public Snippet snippet;
    public Statistics statistics;
}
