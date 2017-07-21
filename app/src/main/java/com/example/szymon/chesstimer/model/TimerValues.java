package com.example.szymon.chesstimer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Szymon on 05.07.2017.
 */

public class TimerValues implements Parcelable {
    private int firstPlayerTime, secondPlayerTime, addon;

    @Override
    public String toString() {
        return "TimerValues{" +
                "firstPlayerTime=" + firstPlayerTime +
                ", secondPlayerTime=" + secondPlayerTime +
                ", addon=" + addon +
                '}';
    }

    public void setFirstPlayerTime(int firstPlayerTime) {
        this.firstPlayerTime = firstPlayerTime;
    }

    public void setSecondPlayerTime(int secondPlayerTime) {
        this.secondPlayerTime = secondPlayerTime;
    }

    public void setAddon(int addon) {
        this.addon = addon;
    }
    public void setTimes(int time1, int time2, int time3) {
        this.firstPlayerTime = time1;
        this.secondPlayerTime = time2;
        this.addon = time3;
    }

    public int getFirstPlayerTime() {
        return firstPlayerTime;
    }

    public int getSecondPlayerTime() {
        return secondPlayerTime;
    }

    public int getAddon() {
        return addon;
    }

    public TimerValues(int firstPlayerTime, int secondPlayerTime, int addon) {
        this.firstPlayerTime = firstPlayerTime;
        this.secondPlayerTime = secondPlayerTime;

        this.addon = addon;
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
    }

    public TimerValues() {
        firstPlayerTime = 10;
        secondPlayerTime = 10;
        addon = 5;
    }

        protected TimerValues(Parcel in) {
            this.firstPlayerTime = in.readInt();
            this.secondPlayerTime = in.readInt();
            this.addon = in.readInt();
        }

    public static final Parcelable.Creator<TimerValues> CREATOR = new Parcelable.Creator<TimerValues>() {
        @Override
        public TimerValues createFromParcel(Parcel source) {
            return new TimerValues(source);
        }

        @Override
        public TimerValues[] newArray(int size) {
            return new TimerValues[size];
        }
    };

}
