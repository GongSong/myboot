package com.yh.kuangjia.util.ALiYun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.util.ALiYun.Params.ParamsBase;
import com.yh.kuangjia.util.ALiYun.Params.ParamsPass;
import com.yh.kuangjia.util.ALiYun.Params.ParamsRefundSuccess;
import com.yh.kuangjia.util.ALiYun.Params.ParamsRegister;
import com.yh.kuangjia.util.CacheUtil;
import com.yh.kuangjia.util.EhCacheSpaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("SmsUtil")
public class SmsUtil {
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    private String accessKeyId = ALiYunConfig.accessKeyId;
    private String accessKeySecret = ALiYunConfig.accessKeySecret;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheUtil cacheUtil;

    IAcsClient acsClient() {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
        return new DefaultAcsClient(profile);
    }

    /**
     * 注册短信发送
     *
     * @param params
     * @return
     */
    public Result sendRegisterSms(ParamsRegister params) {
        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(params.getPhone());
            request.setSignName("梵歌108");
            request.setTemplateCode("SMS_159370058");
            request.setTemplateParam("{\"code\":\"" + params.getCode() + "\"}");
            SendSmsResponse sendSmsResponse = acsClient().getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                cacheUtil.put(EhCacheSpaces.SmsCodeCache, params.getPhone(), params.getCode());
                String content = "验证码" + params.getCode() + "，您正在尝试变更重要信息，请妥善保管账户信息。";
                return Result.success(content);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return Result.failure();
    }

    /**
     * 审核通过结果短信发送
     *
     * @param params
     * @return
     */
    public Result sendReviewPassedSms(ParamsPass params) {
        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(params.getPhone());
            request.setSignName("找眼镜");
            request.setTemplateCode("SMS_155356520");
            request.setTemplateParam("{\"code\":\"" + params.getPwd() + "\"}");
            SendSmsResponse sendSmsResponse = acsClient().getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                String content = "您提交的资料已经审核通过，初始登录密码：" + params.getPwd() + ",请妥善保管！";
                return Result.success(content);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return Result.failure();
    }

    /**
     * 审核不通过结果短信发送
     *
     * @param params
     * @return
     */
    public Result sendReviewNotPassedSms(ParamsBase params) {
        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(params.getPhone());
            request.setSignName("找眼镜");
            request.setTemplateCode("SMS_155356527");
            SendSmsResponse sendSmsResponse = acsClient().getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                String content = "您提交的资料已经被客服小姐姐拒绝了，不要气馁哦！";
                return Result.success(content);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return Result.failure();
    }


    /**
     * 买家退款成功短信通知
     *
     * @param params
     * @return
     */
    public Result sendSuccessSms(ParamsRefundSuccess params) {
        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(params.getPhone());
            request.setSignName("找眼镜");
            request.setTemplateCode("SMS_157684465");
            request.setTemplateParam("{\"code\":\"" + params.getOrder_id() + "\",\"money\":\"" + params.getMoney() + "\"}");
            SendSmsResponse sendSmsResponse = acsClient().getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                cacheUtil.put(EhCacheSpaces.SmsCodeCache, params.getPhone(), params.getOrder_id());
                //String content = "验证码" + params.getCode() + "，您正在进行身份验证，打死不要告诉别人哦！";
                String content = "您的订单"+params.getOrder_id()+"已经退款成功,退款金额："+params.getMoney()+"元,请及时查收。";
                return Result.success(content);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return Result.failure();
    }

    /**
     * 创建退款申请短信通知
     *
     * @param params
     * @return
     */
    public Result sendRefundSms(ParamsRegister params) {
        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(params.getPhone());
            request.setSignName("找眼镜");
            request.setTemplateCode("SMS_157689461");
            request.setTemplateParam("{\"code\":\"" + params.getCode() + "\"}");
            SendSmsResponse sendSmsResponse = acsClient().getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                cacheUtil.put(EhCacheSpaces.SmsCodeCache, params.getPhone(), params.getCode());
                //String content = "验证码" + params.getCode() + "，您正在进行身份验证，打死不要告诉别人哦！";
                String content = "您的订单"+params.getCode()+"发起了退款申请，请及时处理。";
                return Result.success(content);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return Result.failure();
    }

}
