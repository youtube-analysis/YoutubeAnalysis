package com.github.youtube_analysis.api.youtube.channal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Vitaly Kurotkin on 08.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResponce {
    public List<Activity> items;
}
