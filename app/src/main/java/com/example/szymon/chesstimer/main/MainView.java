package com.example.szymon.chesstimer.main;

import com.example.szymon.chesstimer.base.IBaseView;

/**
 * Created by Szymon on 05.07.2017.
 */

public interface MainView extends IBaseView {
    void setTimeToFirst();
    void setTimeToSecond();
    void reset();

    void openSettings();
}
