package com.example.szymon.chesstimer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Szymon on 05.07.2017.
 */

public class TimerValues implements Parcelable {
    private int time1,time2,time3;

    @Override
    public String toString() {
        return "TimerValues{" +
                "time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                '}';
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public void setTime3(int time3) {
        this.time3 = time3;
    }
    public void setTimes(int time1, int time2, int time3) {
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
    }

    public int getTime1() {
        return time1;
    }

    public int getTime2() {
        return time2;
    }

    public int getTime3() {
        return time3;
    }

    public TimerValues(int time1, int time2, int time3) {
        this.time1 = time1;
        this.time2 = time2;

        this.time3 = time3;
    }


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
        time1 = 10;
        time2 = 10;
        time3 = 5;
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

}
