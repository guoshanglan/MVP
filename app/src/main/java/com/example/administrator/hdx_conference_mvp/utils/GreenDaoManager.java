package com.example.administrator.hdx_conference_mvp.utils;


import com.example.administrator.hdx_conference_mvp.base.MyAppLication;
import com.jyjt.ydyl.greendao.gen.DaoMaster;
import com.jyjt.ydyl.greendao.gen.DaoSession;

/**
 * Created by Administrator on 2018/4/4.
 *
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * @@@@ @@@@@@    @@     @@   @@   @@
 * @@@@ @@@        @@ @   @@   @@   @@
 * @@@@ @@         @@  @  @@   @@@@@@@
 * @@@@ @@@        @@   @ @@   @@   @@
 * @@@@ @@@@@@    @@     @@   @@   @@
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */

public class GreenDaoManager {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static  volatile GreenDaoManager mInstance;

    private GreenDaoManager(){
        if (mInstance==null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyAppLication.getContext(),"Myandroid.db");
            daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
            daoSession = daoMaster.newSession();
        }
    }

    //单例模式
    public static GreenDaoManager getInstance(){
        if (mInstance==null){
            synchronized (GreenDaoManager.class){
                if (mInstance ==null)
                    mInstance = new GreenDaoManager();
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return daoMaster;
    }


    public DaoSession getSession() {
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
