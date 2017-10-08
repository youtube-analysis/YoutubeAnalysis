package com.github.youtube_analysis.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Vitaly on 08.10.17.
 */
public class Settings {
    private BooleanProperty saveCach;
    private BooleanProperty showTime;
    private StringProperty urlOfCash;

    public Settings(String urlOfCash, boolean saveCach, boolean showTime){
        this.urlOfCash = new SimpleStringProperty(urlOfCash);
        this.saveCach = new SimpleBooleanProperty(saveCach);
        this.showTime = new SimpleBooleanProperty(showTime);
    }



    public boolean isSaveCach() {
        return saveCach.get();
    }

    public BooleanProperty saveCachProperty() {
        return saveCach;
    }

    public void setSaveCach(boolean saveCach) {
        this.saveCach.set(saveCach);
    }

    public boolean isShowTime() {
        return showTime.get();
    }

    public BooleanProperty showTimeProperty() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime.set(showTime);
    }

    public String getUrlOfCash() {
        return urlOfCash.get();
    }

    public StringProperty urlOfCashProperty() {
        return urlOfCash;
    }

    public void setUrlOfCash(String urlOfCash) {
        this.urlOfCash.set(urlOfCash);
    }
}
