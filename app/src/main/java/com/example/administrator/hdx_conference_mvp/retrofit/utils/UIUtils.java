package com.example.administrator.hdx_conference_mvp.retrofit.utils;

import android.content.Context;
import android.content.res.Resources;

import com.example.administrator.hdx_conference_mvp.base.MyAppLication;

/**
 * Created by xiyuan on 2018/9/26.
 */

public class UIUtils {
    public static Context getContext() {
        return MyAppLication.getInstance();
    }

    public static Resources getResource() {
        return getContext().getResources();
    }


    public static int getColor(int colorId) {
        return getResource().getColor(colorId);
    }

}
