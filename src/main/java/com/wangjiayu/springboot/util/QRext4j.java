package com.wangjiayu.springboot.util;

import org.iherus.codegen.qrcode.SimpleQrcodeGenerator;

import java.io.IOException;

public class QRext4j {

    public static void main(String[] args) throws IOException {
        String content = "https://baike.baidu.com/item/%E5%97%B7%E5%A4%A7%E5%96%B5/19817560?fr=aladdin";
        new SimpleQrcodeGenerator().generate(content).toFile("F:\\AodaCat_default.png");
    }

}
