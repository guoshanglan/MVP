package com.example.administrator.hdx_conference_mvp.inter;

import com.example.administrator.hdx_conference_mvp.bean.LoginBean;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/21.
 * GitHub:https://github.com/CNHTT
 */

public interface LoginListener {
    void loginSuccess(LoginBean loginBean);

    void loginSuccessWx(String str);

    void loginFailed(String str);
}
