package com.github.youtube_analysis.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * Created by Vitaly Kurotkin on 08.10.17.
 */
public class Channal {
    private final StringProperty channelTitle;
    private final StringProperty channelId;
    private final LongProperty subscriberCount;
    private final LongProperty videoCount;
    private final LongProperty viewCount;
    private final ObjectProperty<LocalDate> createDate;


    public Channal(String channelTitle, String channelId, long subscriberCount, long videoCount, long viewCount, Calendar createDate) {
        this.channelTitle = new SimpleStringProperty(channelTitle);
        this.channelId = new SimpleStringProperty(channelId);
        this.subscriberCount = new SimpleLongProperty(subscriberCount);
        this.videoCount = new SimpleLongProperty(videoCount);
        this.viewCount = new SimpleLongProperty(viewCount);
        this.createDate = new SimpleObjectProperty<LocalDate>(createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public String getChannelTitle() {
        return channelTitle.get();
    }

    public StringProperty channelTitleProperty() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle.set(channelTitle);
    }

    public String getChannelId() {
        return channelId.get();
    }

    public StringProperty channelIdProperty() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId.set(channelId);
    }

    public long getSubscriberCount() {
        return subscriberCount.get();
    }

    public LongProperty subscriberCountProperty() {
        return subscriberCount;
    }

    public void setSubscriberCount(long subscriberCount) {
        this.subscriberCount.set(subscriberCount);
    }

    public long getVideoCount() {
        return videoCount.get();
    }

    public LongProperty videoCountProperty() {
        return videoCount;
    }

    public void setVideoCount(long videoCount) {
        this.videoCount.set(videoCount);
    }

    public long getViewCount() {
        return viewCount.get();
    }

    public LongProperty viewCountProperty() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount.set(viewCount);
    }

    public LocalDate getCreateDate() {
        return createDate.get();
    }

    public ObjectProperty<LocalDate> createDateProperty() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate.set(createDate);
    }
}
