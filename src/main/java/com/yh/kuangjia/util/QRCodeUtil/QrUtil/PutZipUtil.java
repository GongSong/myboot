package com.yh.kuangjia.util.QRCodeUtil.QrUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class PutZipUtil {
    private PutZipUtil(){}

    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath :压缩后存放路径
     * @param fileName :压缩后文件的名称
     * @return
     */
    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");
            sourceFile.mkdir(); // 新建目录
        }
        try {
            File zipFile = new File(zipFilePath + "/" + fileName +".zip");
            if(zipFile.exists()){
                System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");
            }else{
                File[] sourceFiles = sourceFile.listFiles();
                if(null == sourceFiles || sourceFiles.length<1){
                    System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                }else{
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
                    byte[] bufs = new byte[1024*10];
                    for(int i=0;i<sourceFiles.length;i++){
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024*10);
                        int read = 0;
                        while((read=bis.read(bufs, 0, 1024*10)) != -1){
                            zos.write(bufs,0,read);
                        }
                    }
                    flag = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally{
            //关闭流
            try {
                if(null != bis) bis.close();
                if(null != zos) zos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return flag;
    }
    /**
     * 解压缩 zip
     * @param srcPath 压缩包的路径
     * @param exPath  压缩地址
     * @throws IOException
     */
    public static void zipToFile(String srcPath,String exPath) throws IOException{
        int buffSize=204800;
        Charset charset=Charset.forName("GBK");
        ZipFile zipFile = null;
        System.out.println(srcPath);
        zipFile = new ZipFile(srcPath,charset);
        Enumeration<ZipEntry> enumeration=(Enumeration<ZipEntry>)zipFile.entries();
        while (enumeration.hasMoreElements()) {
            try {
                ZipEntry zipEntry=enumeration.nextElement();
                if(zipEntry.isDirectory()) {//文件夹
                    File file=new File(exPath+zipEntry.getName());
                    if (!file.exists()){
                        file.mkdir();
                    }
                }
                else {
                    FileOutputStream fileOutputStream = new FileOutputStream(exPath + zipEntry.getName());
                    InputStream inputStream=zipFile.getInputStream(zipEntry);
                    int count=0,tinybuff=buffSize;
                    if(inputStream.available()<tinybuff){
                        tinybuff=inputStream.available();//读取流中可读取大小
                    }
                    byte[] datas=new byte[tinybuff];
                    while ((count=inputStream.read(datas,0,tinybuff))!=-1){//遇到文件结尾返回-1 否则返回实际的读数
                        fileOutputStream.write(datas,0,count);
                        if(inputStream.available()<tinybuff){
                            tinybuff=inputStream.available();
                        }else tinybuff=buffSize;
                        datas=new byte[tinybuff];
                    }
                    fileOutputStream.flush();//刷新缓冲
                    fileOutputStream.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        zipFile.close();
    }
    public static void main(String args[]) throws IOException{
    	/*//文件对象
    	String file = "E:\\FFOutput";//也可以是文件夹路径
    	//待生成的zip包名
    	String zipName = new Date().getTime()+RandomSaltUtil.getRandomNumber(6);
    	//待生成的zip保存路径
    	String zipFilePath = "E:\\FFOutput";
    	//压缩
    	PutZipUtil.fileToZip(file , zipFilePath , zipName);*/
        String srcPath="E:\\FFOutput\\1533199844840437216.zip";
        String exPath="E:\\FFOutputs\\";
        PutZipUtil.zipToFile(srcPath,exPath);
    }
}