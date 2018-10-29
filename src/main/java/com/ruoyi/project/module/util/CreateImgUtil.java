package com.ruoyi.project.module.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ruoyi.framework.config.RuoYiConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

/**
 *
 * @ClassName: CreateImgUtil
 * @Description: 生成小程序二维码工具类
 * @author cheng
 * @date 2017年9月13日 上午10:34:59
 */
@SuppressWarnings("deprecation")
public class CreateImgUtil {

    /**
     * 私有化构造函数，防止创建本工具类的实例
     */
    private CreateImgUtil() {
    }

    public static String httpPostWithJSON(String url, String json,Integer userId)
            throws Exception {
        String result = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity se = new StringEntity(json);
        se.setContentType("application/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                "UTF-8"));
        httpPost.setEntity(se);
        // httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                InputStream instreams = resEntity.getContent();
                String uploadSysUrl = RuoYiConfig.getProfile();
                File saveFile = new File(uploadSysUrl+userId+".png");
                // 判断这个文件（saveFile）是否存在
                if (!saveFile.getParentFile().exists()) {
                    // 如果不存在就创建这个文件夹
                    saveFile.getParentFile().mkdirs();
                }
                saveToImgByInputStream(instreams, uploadSysUrl, userId+".png");
                result = userId+".png";
            }
        }
        httpPost.abort();
        return result;
    }

    /* @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){

        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath+imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
                try {
                    instreams.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return stateInt;
    }
}