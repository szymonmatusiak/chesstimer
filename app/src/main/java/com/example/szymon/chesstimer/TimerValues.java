package com.example.szymon.chesstimer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Szymon on 05.07.2017.
 */

public class TimerValues implements Parcelable {
    public TimerValues(int time1, int time2, int time3) {
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
    }

    private int time1,time2,time3;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.time1);
        dest.writeInt(this.time2);
        dest.writeInt(this.time3);
    }

    public TimerValues() {
    }

        protected TimerValues(Parcel in) {
            this.time1 = in.readInt();
            this.time2 = in.readInt();
            this.time3 = in.readInt();
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

    @Override
    public String toString() {
        return "TimerValues{" +
                "time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                '}';
    }
}
