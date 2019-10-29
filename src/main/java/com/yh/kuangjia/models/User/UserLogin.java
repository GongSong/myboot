package com.yh.kuangjia.models.User;


import com.yh.kuangjia.core.annotation.ParamCheck;

public class UserLogin {
//    /**
//     * 手机号码
//     */
//    @ParamCheck(notNull = true, message = "手机号码不能为空")
//    private String vc_phone;
//    /**
//     * 登录密码
//     */
//    @ParamCheck(notNull = true, message = "登录密码不能为空")
//    private String c_login_pwd;
    /**
     * 微信登录凭证
     */

    @ParamCheck(notNull = true, message = "缺少登录凭证")
    private String code;


    /**
     * 邀请人id
     */
    private Integer n_user_id;

//    @ParamCheck(notNull = true, message = "缺少头像")
//    private String avatarUrl;
//
//    @ParamCheck(notNull = true, message = "缺少昵称")
//    private String nickName;
//
//    @ParamCheck(notNull = true, message = "缺少性别")
//    private Integer gender;

//    public String getAvatarUrl() {
//        return avatarUrl;
//    }
//
//    public void setAvatarUrl(String avatarUrl) {
//        this.avatarUrl = avatarUrl;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    public Integer getGender() {
//        return gender;
//    }
//
//    public void setGender(Integer gender) {
//        this.gender = gender;
//    }
//
//
//    public String getVc_phone() {
//        return vc_phone;
//    }
//
//    public void setVc_phone(String vc_phone) {
//        this.vc_phone = vc_phone;
//    }
//
//    public String getC_login_pwd() {
//        return c_login_pwd;
//    }
//
//    public void setC_login_pwd(String c_login_pwd) {
//        this.c_login_pwd = c_login_pwd;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getN_user_id() {
        return n_user_id;
    }

    public void setN_user_id(Integer n_user_id) {
        this.n_user_id = n_user_id;
    }
}
