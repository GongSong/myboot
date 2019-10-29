package com.yh.kuangjia.controller.Admin;


import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.core.BaseController;
import com.yh.kuangjia.core.annotation.IgnoreLogin;
import com.yh.kuangjia.util.ALiYun.OssUtil;
import com.yh.kuangjia.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/upload")
@Api("系统图片视屏等文件上传相关接口")
public class SysUploadController extends BaseController {

    @Autowired
    private OssUtil ossUtil;

    /**
     * 图片统一存放文件夹
     */
    private String _Prex = "BENYUAN";

    @IgnoreLogin
    @ApiOperation(value = "图片上传（管理后台用户）")
    @RequestMapping(value = "admin_image", method = RequestMethod.POST)
    public Result UploadAdminImage(MultipartFile file) {
        return ossUtil.fileUp(_Prex + "/ADMINIMAGE/" + DateUtil.GetDateInt() + "/" + OssUtil.getFileName(file.getOriginalFilename()), file);
    }

    @IgnoreLogin
    @ApiOperation(value = "课程图片上传")
    @RequestMapping(value = "course_image", method = RequestMethod.POST)
    public Result UploadCourseImage(MultipartFile file) {
        return ossUtil.fileUp(_Prex + "/COURSEIMAGE/" + DateUtil.GetDateInt() + "/" + OssUtil.getFileName(file.getOriginalFilename()), file);
    }

    @ApiOperation(value = "商品图片上传")
    @RequestMapping(value = "good_img", method = RequestMethod.POST)
    public Result UploadGoodImage(MultipartFile file) {
        Result result = ossUtil.fileUp(_Prex + "/GOODIMAGE/" + DateUtil.GetDateInt() + "/" + OssUtil.getFileName(file.getOriginalFilename()), file);
        return result;
    }

    @ApiOperation(value = "用户图片上传")
    @RequestMapping(value = "user_img", method = RequestMethod.POST)
    public Result UploadUserImg(MultipartFile file) {
        return ossUtil.fileUp(_Prex + "/USERIMG/" + DateUtil.GetDateInt() + "/" + OssUtil.getFileName(file.getOriginalFilename()), file);
    }

    @IgnoreLogin
    @ApiOperation(value = "获取图片访问地址")
    @RequestMapping(value = "getimageurl1", method = RequestMethod.POST)
    public String GetImageUrl(@RequestParam("key") String key) throws IOException {
        return ossUtil.generatePresignedUrl(key);
    }

    @IgnoreLogin
    @ApiOperation(value = "获取图片访问地址")
    @RequestMapping(value = "getimageurl2", method = RequestMethod.POST)
    public String GetImageUrl(@RequestParam("key") String key, @RequestParam("w") Integer w) throws IOException {
        return ossUtil.generatePresignedUrl(key, w);
    }

    @IgnoreLogin
    @ApiOperation(value = "获取图片访问地址")
    @RequestMapping(value = "getimageurl3", method = RequestMethod.POST)
    public String GetImageUrl(@RequestParam("key") String key, @RequestParam("w") Integer w, @RequestParam("h") Integer h) throws IOException {
        return ossUtil.generatePresignedUrl(key, w, h);
    }
}
