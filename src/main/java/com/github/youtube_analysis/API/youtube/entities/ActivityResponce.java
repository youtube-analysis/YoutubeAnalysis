package com.github.youtube_analysis.API.youtube.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Vitaly Kurotkin on 06.10.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResponce {
    public List<Activity> items;
}
