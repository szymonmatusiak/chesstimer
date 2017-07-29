package com.example.szymon.chesstimer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.szymon.chesstimer.settings.SettingsActivity;

/**
 * Created by Szymon on 05.07.2017.
 */

public class TimerValues implements Parcelable {
    public static final Creator<TimerValues> CREATOR = new Creator<TimerValues>() {
        @Override
        public TimerValues createFromParcel(Parcel source) {
            return new TimerValues(source);
        }

        @Override
        public TimerValues[] newArray(int size) {
            return new TimerValues[size];
        }
    };
    private static final int DEFAULT_STARTING_TIME_10_MINUTES = 10;
    private static final int DEFAULT_ADDON_5SEKONDS = 5;
    private int firstPlayerTime, secondPlayerTime, addon;
    @SettingsActivity.Delay
    private int delay;

    public TimerValues() {
        firstPlayerTime = DEFAULT_STARTING_TIME_10_MINUTES;
        secondPlayerTime = DEFAULT_STARTING_TIME_10_MINUTES;
        addon = DEFAULT_ADDON_5SEKONDS;
        delay = SettingsActivity.Delay.FISCHER_DELAY;
    }

    @SuppressWarnings("ResourceType")
    protected TimerValues(Parcel in) {
        this.firstPlayerTime = in.readInt();
        this.secondPlayerTime = in.readInt();
        this.addon = in.readInt();
        this.delay = in.readInt();
    }

    public void setTimes(final int firstPlayerTime, final int secondPlayerTime, final int addon) {
        this.firstPlayerTime = firstPlayerTime;
        this.secondPlayerTime = secondPlayerTime;
        this.addon = addon;
    }

    @Override
    public String toString() {
        return "TimerValues{" +
                "firstPlayerTime=" + firstPlayerTime +
                ", secondPlayerTime=" + secondPlayerTime +
                ", addon=" + addon +
                ", delay=" + delay +
                '}';
    }

    public int getFirstPlayerTime() {
        return firstPlayerTime;
    }

    public void setFirstPlayerTime(final int firstPlayerTime) {
        this.firstPlayerTime = firstPlayerTime;
    }

    public int getSecondPlayerTime() {
        return secondPlayerTime;
    }

    public void setSecondPlayerTime(final int secondPlayerTime) {
        this.secondPlayerTime = secondPlayerTime;
    }

    public int getAddon() {
        return addon;
    }

    public void setAddon(final int addon) {
        this.addon = addon;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(final int delay) {
        this.delay = delay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.firstPlayerTime);
        dest.writeInt(this.secondPlayerTime);
        dest.writeInt(this.addon);
        dest.writeInt(this.delay);
    }
}
