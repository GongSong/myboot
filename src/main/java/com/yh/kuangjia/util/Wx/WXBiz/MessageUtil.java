package com.yh.kuangjia.util.Wx.WXBiz;

public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";

    public static final String EVENT_SUB = "subscribe";
    public static final String EVENT_UNSUB = "unsubscribe";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";


    public static String initText(ReturnMan adapter, String content) throws Exception {
        String s = "<xml><ToUserName><![CDATA[toUserName]]></ToUserName><FromUserName><![CDATA[fromUserName]]></FromUserName><CreateTime>createTime</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[content]]></Content></xml>";
        String replace = s.replace("toUserName", adapter.getToUserName()).replace("fromUserName", adapter.getFromUserName()).replace("createTime", adapter.getCreateTime()).replace("content", content);
        return replace;
    }


    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("     测试\n");
        sb.append("     或者不关注，\n");
        sb.append("      【你我杂志刊】都在这里!\n");
        sb.append("     不离，\n");
        sb.append("      不弃！\n\n");
        sb.append("该公众号已实现以下功能：\n");
        sb.append("回复“天气”、“翻译” 将有该功能的介绍与使用，\n");
        sb.append("如您在使用该公众有任何宝贵意见，欢迎反馈！\n\n");
        sb.append("反馈邮箱：zhenqicai@sohu.com");
        return sb.toString();
    }
}
