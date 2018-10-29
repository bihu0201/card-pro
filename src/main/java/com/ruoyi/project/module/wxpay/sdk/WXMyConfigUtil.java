package com.ruoyi.project.module.wxpay.sdk;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXMyConfigUtil implements WXPayConfig {
    private byte[] certData;

    public WXMyConfigUtil() throws Exception {
       // String certPath = "classpath:cert/wxpay/apiclient_cert.pem";//从微信商户平台下载的安全证书存放的目录
        Resource resource = new ClassPathResource("cert/wxpay/apiclient_cert.pem");
     //   File file = new File(certPath);
        File file = resource.getFile();
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    @Override
    public String getAppID() {
        return "wx48e156ddf90b1936";
    }

    //parnerid
    @Override
    public String getMchID() {
        return "1496645762";
    }

    @Override
    public String getKey() {
        return "26dc318942685872cf79c5eb96c9bb13";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}