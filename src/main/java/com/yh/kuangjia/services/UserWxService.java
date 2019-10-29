package com.yh.kuangjia.services;


import com.yh.kuangjia.base.Result;

public interface UserWxService {

    String GetOpenID(Integer userID);

    String getAccessToken();

    Result ticket(String referer);

    String checkIsFocus(Integer user_id);
}
