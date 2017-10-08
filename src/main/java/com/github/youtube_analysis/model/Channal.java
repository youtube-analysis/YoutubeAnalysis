package com.github.youtube_analysis.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Vitaly Kurotkin on 08.10.17.
 */
public class Channal {
    private final StringProperty channelTitle;
    private final StringProperty channelId;

    public Channal(String channelTitle, String channelId) {
        this.channelTitle = new SimpleStringProperty(channelTitle);
        this.channelId = new SimpleStringProperty(channelId);
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
}
