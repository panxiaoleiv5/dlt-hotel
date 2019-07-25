package com.handinglian.common.utils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UserDownZipUtils {
    public static String downZipManyFile(List<File> fileList, String zipFileName) throws IOException {
        BufferedInputStream br = null;//输入流
        ZipOutputStream out = null; // 压缩文件输出流
        int size;

        // 定义缓冲区
        byte[] buffer = new  byte[2048];
        if(fileList.size()>0){
            out = new ZipOutputStream(new FileOutputStream(zipFileName));
            for (int i = 0; i < fileList.size(); i++) {
                File f =fileList.get(i);
                out.putNextEntry(new ZipEntry(f.getName()));
                br = new BufferedInputStream(new FileInputStream(f));
                while((size=br.read(buffer))!=-1){
                    out.write(buffer,0,size);
                    out.flush();
                }
            }
            if(br!=null){br.close();}
            if(out!=null){out.close();}
        }
        return zipFileName;
    }
}
