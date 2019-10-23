package com.yh.kuangjia.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class RequestParamsUtil {


    /**
     * 获取所有参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> GetRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果出现不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        if(params.isEmpty()){
            try {
                BufferedReader reader = request.getReader();
                String input = null;
                StringBuffer requestBody = new StringBuffer();
                while ((input = reader.readLine()) != null) {
                    requestBody.append(input);
                }
                String json = requestBody.toString();
                if(!json.isEmpty()){
                    params = JSON.parseObject(json,new TypeReference<Map<String, String>>(){});
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return params;
    }
}
