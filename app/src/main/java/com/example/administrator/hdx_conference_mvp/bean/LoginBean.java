package com.example.administrator.hdx_conference_mvp.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27.
 * 登录返回的解析json格式数据
 */

public class LoginBean {

    /**
     * code : 0
     * msg :
     * result : {"activityid":7403694287700,"userid":"1872761476769","username":"郭上hiabc","userlogo":"http://cdn.huodongxing.com/logo/user/201707/2411/1872761476769/182819817989233.jpg","company":"","city":"深圳","industry":"","position":"","desc":"个好吧好吧才给你观后感发个方法","wechat":"","qq":"","email":"13502095709@163.com","phone":"13502095709","isfollow":false,"interesttags":["金融","投资","旅行","设计","读书"],"tids":["9828055046373"],"id":"8722805580432147","token":"aa830421cc3b4f38b7795d4e62f59e5b"}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * activityid : 7403694287700
         * userid : 1872761476769
         * username : 郭上hiabc
         * userlogo : http://cdn.huodongxing.com/logo/user/201707/2411/1872761476769/182819817989233.jpg
         * company :
         * city : 深圳
         * industry :
         * position :
         * desc : 个好吧好吧才给你观后感发个方法
         * wechat :
         * qq :
         * email : 13502095709@163.com
         * phone : 13502095709
         * isfollow : false
         * interesttags : ["金融","投资","旅行","设计","读书"]
         * tids : ["9828055046373"]
         * id : 8722805580432147
         * token : aa830421cc3b4f38b7795d4e62f59e5b
         */

        private long activityid;
        private String userid;
        private String username;
        private String userlogo;
        private String company;
        private String city;
        private String industry;
        private String position;
        private String desc;
        private String wechat;
        private String qq;
        private String email;
        private String phone;
        private boolean isfollow;
        private String id;
        private String token;
        private List<String> interesttags;
        private List<String> tids;

        public long getActivityid() {
            return activityid;
        }

        public void setActivityid(long activityid) {
            this.activityid = activityid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserlogo() {
            return userlogo;
        }

        public void setUserlogo(String userlogo) {
            this.userlogo = userlogo;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isIsfollow() {
            return isfollow;
        }

        public void setIsfollow(boolean isfollow) {
            this.isfollow = isfollow;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<String> getInteresttags() {
            return interesttags;
        }

        public void setInteresttags(List<String> interesttags) {
            this.interesttags = interesttags;
        }

        public List<String> getTids() {
            return tids;
        }

        public void setTids(List<String> tids) {
            this.tids = tids;
        }
    }
}
