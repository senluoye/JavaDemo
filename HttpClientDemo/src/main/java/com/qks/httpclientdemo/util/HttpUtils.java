package com.qks.httpclientdemo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName HttpUtils
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-15 15:54
 */
public class HttpUtils {

    private static final String URGE_EXPERT_URI = "http://localhost:8080/api/test";

    /**
     * 发送专家催评信息
     *
     * @param expertCodes 专家工号列表
     */
    public static void sendUrgeMessage(List<Long> expertCodes) {
        Map<String, Object> params = new HashMap<>();
        // DataJson
        Map<String, Object> paramDataJson = new HashMap<>();

        String currentDate = getCurrentDate();
        String currentTime = getCurrentTime();
        String currentTimeTamp = getTimestamp();

        /**
         * Header 部分
         */
        Map<String, String> header = new HashMap<>();
        // 系统标识
        String systemId = "授权用户名";
        // 密码
        String password = "授权密码";
        // header
        header.put("systemid", systemId);
        header.put("currentDateTime", currentTimeTamp);
        String md5Source = systemId + password + currentTimeTamp;
        String md5OfStr = getMD5Str(md5Source).toLowerCase();
        // Md5是：系统标识 + 密码 + 时间戳 并且md5加密的结果
        header.put("Md5", md5OfStr);
        paramDataJson.put("header", header);

        /**
         * Data 部分
         */
        List<JSONObject> data = new ArrayList<>();
        JSONObject dataTemp = new JSONObject();
        // 封装 operationinfo 参数
        JSONObject operationinfo = new JSONObject();
        operationinfo.put("operationDate", 1);
        operationinfo.put("operator", 1);
        operationinfo.put("operationTime", 1);
        dataTemp.put("operationinfo", operationinfo);
        // 封装 mainTable 参数
        JSONObject mainTable = new JSONObject();
        mainTable.put("wpcbtxnr", "1");
        mainTable.put("wpcbtxsj", "1");
        mainTable.put("zjgh", "1");
        dataTemp.put("mainTable", mainTable);
        data.add(dataTemp);
        paramDataJson.put("data", data);
        params.put("datajson", paramDataJson);

        // 发送请求
        try (CloseableHttpResponse response = send(params)) {
            if (response != null && response.getEntity() != null) {
                // 返回信息
                String resulString = EntityUtils.toString(response.getEntity());
                System.out.println("返回信息: " + resulString);
            } else {
                System.out.println("获取数据失败，请查看日志" + getCurDateTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送http请求
     *
     * @param params
     * @return CloseableHttpResponse
     * @throws IOException io异常
     */
    public static CloseableHttpResponse send(Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(URGE_EXPERT_URI);

        // 装填参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), JSONObject.toJSONString(entry.getValue())));
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            return httpClient.execute(httpPost);
        } catch (Exception e) {
            System.out.println("请求失败: " + e.getMessage());
        }

        return null;
    }

    /**
     * 获取 MD5 加密后的字符串
     *
     * @param plainText
     * @return
     */
    public static String getMD5Str(String plainText) {
        // 定义一个字节数组
        byte[] secretBytes;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对字符串进行加密
            md.update(plainText.getBytes());
            // 获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        // 将加密后的数据转换为16进制数字
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        Date nowDate = new Date();
        long datetime = nowDate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(11, 13) + ":"
                + (timestamp.toString()).substring(14, 16) + ":"
                + (timestamp.toString()).substring(17, 19);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Date nowDate = new Date();
        long datetime = nowDate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(0, 4) + "-"
                + (timestamp.toString()).substring(5, 7) + "-"
                + (timestamp.toString()).substring(8, 10);
    }

    /**
     * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
     *
     * @return 当前日期时间
     */
    public static String getCurDateTime() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(0, 19);
    }

    /**
     * 获取时间戳 格式如：19990101235959
     *
     * @return 时间戳
     */
    public static String getTimestamp() {
        return getCurDateTime().replace("-", "").replace(":", "").replace(" ", "");
    }

}
