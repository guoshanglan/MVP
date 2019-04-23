package com.example.administrator.hdx_conference_mvp.bean;

/**
 * Created by Administrator on 2019/4/1.
 * 驾校题目的bean
 */

public class TranficBean {

    public String title;
    public String[]item;//选择题选项 （包含判断题和单选题）
    public String result;  //答案
    public String type;  //题目类型

    public TranficBean(String title, String[]item, String result, String type) {
        this.title = title;
        this.item = item;
        this.result = result;
        this.type = type;
    }

}
