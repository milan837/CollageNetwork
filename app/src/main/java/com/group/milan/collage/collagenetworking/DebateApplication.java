package com.group.milan.collage.collagenetworking;

import android.app.Application;

public class DebateApplication extends Application {
    private static DebateApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized DebateApplication newInstance() {
        return mInstance;
    }
}
