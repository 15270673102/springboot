package com.wangjiayu.springboot.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

/**
 * @author 技术部
 */
public class ChaoJiYing {

    public ChaoJiYing() {
    }

    public static final String MD5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String httpRequestData(String url, String param) throws IOException {
        HttpURLConnection con = null;
        StringBuffer buffer = new StringBuffer();
        URL u = new URL(url);
        con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        osw.write(param);
        osw.flush();
        osw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        String temp;
        while ((temp = br.readLine()) != null) {
            buffer.append(temp);
            buffer.append("\n");
        }

        return buffer.toString();
    }

    public static String GetScore(String username, String password) {
        String param = String.format("user=%s&pass=%s", username, password);

        String result;
        try {
            result = httpRequestData("http://upload.chaojiying.net/Upload/GetScore.php", param);
        } catch (IOException var5) {
            result = "未知问题";
        }

        return result;
    }

    public static String UserReg(String username, String password) {
        String param = String.format("user=%s&pass=%s", username, password);

        String result;
        try {
            result = httpRequestData("http://upload.chaojiying.net/Upload/UserReg.php", param);
        } catch (IOException var5) {
            result = "未知问题";
        }

        return result;
    }

    public static String UserPay(String username, String card) {
        String param = String.format("user=%s&card=%s", username, card);

        String result;
        try {
            result = httpRequestData("http://upload.chaojiying.net/Upload/UserPay.php", param);
        } catch (IOException var5) {
            result = "未知问题";
        }

        return result;
    }

    public static String ReportError(String username, String password, String softid, String id) {
        String param = String.format("user=%s&pass=%s&softid=%s&id=%s", username, password, softid, id);

        String result;
        try {
            result = httpRequestData("http://upload.chaojiying.net/Upload/ReportError.php", param);
        } catch (IOException var7) {
            result = "未知问题";
        }

        return result;
    }

    public static String httpPostImage(String param, byte[] data) throws IOException {
        long time = (new Date()).getTime();
        URL u = null;
        HttpURLConnection con = null;
        String boundary = "----------" + MD5(String.valueOf(time));
        String boundarybytesString = "\r\n--" + boundary + "\r\n";
        OutputStream out = null;
        u = new URL("http://upload.chaojiying.net/Upload/Processing.php");
        con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(60000);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(true);
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        out = con.getOutputStream();
        String[] var12;
        int var11 = (var12 = param.split("[&]")).length;

        String paramString;
        String temp;
        for (int var10 = 0; var10 < var11; ++var10) {
            paramString = var12[var10];
            out.write(boundarybytesString.getBytes("UTF-8"));
            temp = "Content-Disposition: form-data; name=\"" + paramString.split("[=]")[0] + "\"\r\n\r\n" + paramString.split("[=]")[1];
            out.write(temp.getBytes("UTF-8"));
        }

        out.write(boundarybytesString.getBytes("UTF-8"));
        paramString = "Content-Disposition: form-data; name=\"userfile\"; filename=\"chaojiying_java.gif\"\r\nContent-Type: application/octet-stream\r\n\r\n";
        out.write(paramString.getBytes("UTF-8"));
        out.write(data);
        String tailer = "\r\n--" + boundary + "--\r\n";
        out.write(tailer.getBytes("UTF-8"));
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

        while ((temp = br.readLine()) != null) {
            buffer.append(temp);
            buffer.append("\n");
        }

        return buffer.toString();
    }

    public static String PostPic(String username, String password, String softid, String codetype, String len_min, String filePath) {
        String result = "";
        String param = String.format("user=%s&pass=%s&softid=%s&codetype=%s&len_min=%s", username, password, softid, codetype, len_min);

        try {
            File f = new File(filePath);
            if (f != null) {
                int size = (int) f.length();
                byte[] data = new byte[size];
                FileInputStream fis = new FileInputStream(f);
                fis.read(data, 0, size);
                if (fis != null) {
                    fis.close();
                }

                if (data.length > 0) {
                    result = httpPostImage(param, data);
                }
            }
        } catch (Exception var12) {
            result = "未知问题";
        }

        return result;
    }

    public static String PostPic(String username, String password, String softid, String codetype, String len_min, byte[] byteArr) {
        String result = "";
        String param = String.format("user=%s&pass=%s&softid=%s&codetype=%s&len_min=%s", username, password, softid, codetype, len_min);

        try {
            result = httpPostImage(param, byteArr);
        } catch (Exception var9) {
            result = "未知问题";
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(ReportError("tylnn2869", "217824ying", "895799", "6029311501472200001"));
    }
}
