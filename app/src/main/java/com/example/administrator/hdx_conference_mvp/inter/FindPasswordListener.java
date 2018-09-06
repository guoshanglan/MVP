package com.example.administrator.hdx_conference_mvp.inter;

import com.example.administrator.hdx_conference_mvp.bean.FindPasswordBean;

/**
 * Created by Administrator on 2018/9/5.
 */

public interface FindPasswordListener {
    void findSuccess(FindPasswordBean findPasswordBean);

    void findSuccessWx(String str);

    void findFailed(String str);

}
