package com.yh.kuangjia.controller.Admin;

import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.util.ALiYun.OssUtil;
import com.yh.kuangjia.util.DateUtil;
import com.yh.kuangjia.util.UeditorConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/ueditor")
@Api("系统文本编辑器相关接口")
public class UeditorController extends BaseController {
    @Autowired
    private OssUtil ossUtil;
    /**
     * 图片统一存放文件夹
     */
    private String _Prex = "BENYUAN";

    @IgnoreLogin
    @ApiOperation(value = "文本编辑器需要")
    @RequestMapping(value = "upload")
    public Object upload(@RequestParam String action, HttpServletRequest request, HttpServletResponse response) {
        if (action.equals("uploadimage"))//图片上传
        {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("upfile");
            String url = ossUtil.fileUpReturnUrl(_Prex + "/ADMINIMAGE/" + DateUtil.GetDateInt() + "/" + OssUtil.getFileName(file.getOriginalFilename()), file);
            return "{\"state\":\"SUCCESS\",\"url\":\"" + url + "\"}";

        } else if (action.equals("config"))//图片上传
        {
            String callback = request.getParameter("callback");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,x_requested_with");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json");
            response.setContentType("application/json");
            if (StringUtils.isEmpty(callback))
                return UeditorConfig.UEDITOR_CONFIG;
            else {
                return callback+"("+UeditorConfig.UEDITOR_CONFIG+")";
            }
        } else {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("upfile");
            String url = ossUtil.fileUpReturnUrl(_Prex + "/ADMINIMAGE/" + DateUtil.GetDateInt() + "/" + OssUtil.getFileName(file.getOriginalFilename()), file);
            return "{\"state\":\"SUCCESS\",\"url\":\"" + url + "\"}";
        }
    }
}
