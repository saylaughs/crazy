package com.saylaughs.crazysaylaugh.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

public class ImgUtil {

    public Object imgUpLoad(MultipartFile partFile, HttpSession session){
        System.out.println(partFile+"空的");
        if (!partFile.isEmpty()){
            String fileName=partFile.getOriginalFilename();   //文件名
            String fixSuf= FilenameUtils.getExtension(fileName);
            String path="E:/saylaugh/src/main/webapp/img/"+fileName;
            int size=500000;
            if(partFile.getSize()>size){
                return JSON.toJSON("文件过大");
            }
            else if(fixSuf.equalsIgnoreCase("jpg")||fixSuf.equalsIgnoreCase("png")||
                    fixSuf.equalsIgnoreCase("gif")||fixSuf.equalsIgnoreCase("jpeg")){
                File file=new File(path,fileName);
                if (!new File(path).exists()){
                    new File(path).mkdir();
                }

                try{
                    partFile.transferTo(file);
                }catch(Exception e){
                    e.printStackTrace();
                    return JSON.toJSON("上传失败");
                }
            }
            else{
                return JSON.toJSON("图片格式不正确");
            }
        }
        return JSON.toJSON("上传成功");
    }



}
