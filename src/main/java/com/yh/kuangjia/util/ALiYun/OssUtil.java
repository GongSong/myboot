package com.yh.kuangjia.util.ALiYun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.yh.kuangjia.base.Result;
import com.yh.kuangjia.util.HttpUtil;
import com.yh.kuangjia.util.UUIDUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

@Component
@Service("OssUtil")
public class OssUtil {

    private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = ALiYunConfig.accessKeyId;
    private static String accessKeySecret = ALiYunConfig.accessKeySecret;
    private static String bucketName = ALiYunConfig.bucketName;
    public static String bucketNameUrl = "https://" + ALiYunConfig.bucketName + ".oss-cn-hangzhou.aliyuncs.com/";

    private OSSClient getOssClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 生成文件名
     *
     * @return
     */
    public static String getFileName(String originalFilename) {
        return UUIDUtil.getUuid() + getFileSuffixName(originalFilename);
    }

    /**
     * 获取文件后缀名
     *
     * @return
     */
    public static String getFileSuffixName(String originalFilename) {
        return "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

    /**
     * 获取URL地址（原图）
     *
     * @param key
     * @return
     */
    public String generatePresignedUrl(String key) {
        return bucketNameUrl + key;
    }

    /**
     * 获取URL地址(限定宽度)
     *
     * @param key
     * @return
     */
    public String generatePresignedUrl(String key, Integer width) {
        return generatePresignedUrl(key) + "?x-oss-process=image/resize,w_" + width;
    }

    /**
     * 获取URL地址(限定长款)
     *
     * @param key
     * @return
     */
    public String generatePresignedUrl(String key, Integer width, Integer height) {
        return generatePresignedUrl(key) + "?x-oss-process=image/resize,m_lfit,h_" + height + ",w_" + width;
    }

    /**
     * 上传文件
     *
     * @param key
     * @param file
     * @return
     */
    public Result fileUp(String key, MultipartFile file) {
        //if (file.isEmpty()) {
        try {
            byte[] data = file.getBytes();
            getOssClient().putObject(bucketName, key, new ByteArrayInputStream(data));
            getOssClient().shutdown();
            return Result.success(new OssModel(key, generatePresignedUrl(key)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getOssClient() != null) {
                getOssClient().shutdown();
            }
        }
        // }
        return new Result(1, "文件为空");
    }

    /**
     * 上传文件
     *
     * @param key
     * @param file
     * @return
     */
    public String fileUpReturnUrl(String key, MultipartFile file) {
        try {
            byte[] data = file.getBytes();
            getOssClient().putObject(bucketName, key, new ByteArrayInputStream(data));
            getOssClient().shutdown();
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 100);
            return getOssClient().generatePresignedUrl(bucketName, key, expiration).toString().replace(getOssClient().generatePresignedUrl(bucketName, key, expiration).toString().substring(0,4),"https");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getOssClient() != null) {
                getOssClient().shutdown();
            }
        }
        return "";
    }

    /**
     * 上传文件
     *
     * @param key
     * @param fileUrl
     * @return
     */
    public Result fileUp(String key, String fileUrl) {
        //if (file.isEmpty()) {
        try {
            byte[] data = HttpUtil.getBytes(fileUrl);
            getOssClient().putObject(bucketName, key, new ByteArrayInputStream(data));
            getOssClient().shutdown();
            return Result.success(new OssModel(key, generatePresignedUrl(key)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getOssClient() != null) {
                getOssClient().shutdown();
            }
        }
        // }
        return new Result(1, "文件为空");
    }
    /**
     * 删除文件
     *
     * @param key
     */
    public void delFile(String key) {
        getOssClient().deleteObject(bucketName, key);
        getOssClient().shutdown();
    }

    /**
     * 删除文件 多个文件
     *
     * @param keys
     */
    public void delFiles(List<String> keys) {
        // 删除文件。
        DeleteObjectsResult deleteObjectsResult = getOssClient().deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭OSSClient。
        getOssClient().shutdown();
    }

    /**
     * 验证是否存在
     *
     * @param objName
     * @return
     */
    public boolean isHave(String objName) {
        return getOssClient().doesObjectExist(bucketName, objName);
    }


    /**
     * 上传时文件的contentTypes是否为图片
     *
     * @param filename
     * @return
     */
    public static boolean isImg(String filename) {

        String filenameExtension = filename.substring(filename.lastIndexOf(".") + 1);

        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return true;
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return true;
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return true;
        }
        return false;
    }
}
