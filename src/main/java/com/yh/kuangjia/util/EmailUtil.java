package com.yh.kuangjia.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

//@Component
@Service("EmailUtil")
public class EmailUtil {

    private static final String From = "243748670@qq.com";

    @Autowired
    private JavaMailSender mailSender; //框架自带的

    //意思是异步调用这个方法
    public void sendMail(String title, String content, String email) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(From); // 发送人的邮箱
            helper.setSubject(title); //标题
            helper.setTo(email); //发给谁  对方邮箱
            helper.setText(content,true); //内容,true支持html内容，false支持纯文本内容
            mailSender.send(message); //发送
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
