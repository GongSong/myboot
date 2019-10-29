package com.yh.kuangjia.test;

import com.yh.kuangjia.util.QRCodeUtil.QrUtil.ZipHelper;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.apache.commons.collections.MapUtils.getLong;
import static org.apache.commons.collections.MapUtils.getString;

@RestController
@RequestMapping("api/qrcode")
@Api("小程序码")
public class DemoController {


    /**
     * 附件导出 （导出所有用户上传的文件格式 已压缩包形式导出 ）。
     * 1.创建一个临时存放文件的tempFile。
     * 2.在临时文件夹中创建用户文件夹用来存放下载好的文件（用户文件夹可以用时间戳或者uuid来命名）。
     * 3.把临时文件夹压缩成zip文件，存放到tempfile下面。
     * 4.根据流的形式把压缩文件读到放到浏览器下载 5.关闭流，删除临时文件中的用户文件夹和压缩好的用户文件夹。
     *
     * @param response
     * @param
     * @param request
     * @throws IOException
     */
    @RequestMapping("/fForm/enclosureExport")
    public void enclosureExport(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path ="D:\\code";

        //2 生成zip文件
        ZipHelper.zipCompress(path, path + ".zip");
        //3 下载
        String zipFileName = path + ".zip";
        String filename = "code" + ".zip";
        response.setCharacterEncoding("UTF-8");
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        InputStream in = new FileInputStream(zipFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.flush();
        //4 删除多余文件
        deleteDir(new File(path));
        in.close();//先关闭输入流才能删除
        deleteDir(new File(zipFileName));
        out.close();
    }

    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String s : children) {
                boolean success = deleteDir(new File(dir, s));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public byte[] createZip(String srcSource) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 将目标文件打包成zip导出
        File file = new File(srcSource);
        a(zip, file, "");
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public void a(ZipOutputStream zip, File file, String dir) throws Exception {
        // 如果当前的是文件夹，则进行进一步处理
        try {
            if (file.isDirectory()) {
                // 得到文件列表信息
                File[] files = file.listFiles();
                // 将文件夹添加到下一级打包目录
                zip.putNextEntry(new ZipEntry(dir + "/"));
                dir = dir.length() == 0 ? "" : dir + "/";
                // 循环将文件夹中的文件打包
                for (int i = 0; i < files.length; i++) {
                    a(zip, files[i], dir + files[i].getName());
                }
            } else {
                // 当前的是文件，打包处理文件输入流
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                ZipEntry entry = new ZipEntry(dir);
                zip.putNextEntry(entry);
                zip.write(FileUtils.readFileToByteArray(file));
                IOUtils.closeQuietly(bis);
            }
        } catch (Exception e) {
            // TODO: handle exception
            zip.flush();
            zip.close();
        }
    }


}
