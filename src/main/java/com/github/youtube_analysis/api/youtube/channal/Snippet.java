package com.github.youtube_analysis.api.youtube.channal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;

/**
 * Created by Vitaly Kurotkin on 08.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    public String title;
    public Calendar publishedAt;
}
