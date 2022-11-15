import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;


/**
 * 说明
 * 修改时
 * 类名要与文件名保持一致
 * class文件存放位置与路径保持一致。
 * 请把编译后的class文件，放在对应的目录中才能生效
 * 注意 同一路径下java名不能相同。
 *
 * @author Administrator
 */
public class Test {

    public static void main(String[] args) {
        Test restfulDemo = new Test();
        restfulDemo.doAction();
    }

    /**
     * restful接口调用案例
     * 以getModeDataPageList为例
     */
    public void doAction() {
        // 响应类,
        CloseableHttpResponse response;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //restful接口url
        HttpPost httpPost = new HttpPost("http://ip:port/api/cube/restful/interface/getModeDataPageList/rpakeywordList");

        //当前日期
        String currentDate = getCurrentDate();
        //当前时间
        String currentTime = getCurrentTime();
        //获取时间戳
        String currentTimeTamp = getTimestamp();

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> paramDatajson = new HashMap<>();
        //header
        Map<String, String> header = new HashMap<>();

        //系统标识
        String systemid = "授权用户名";
        //密码
        String d_password = "授权密码";
        //封装header里的参数
        header.put("systemid", systemid);
        header.put("currentDateTime", currentTimeTamp);

        String md5Source = systemid + d_password + currentTimeTamp;
        String md5OfStr = getMD5Str(md5Source).toLowerCase();
        //Md5是：系统标识+密码+时间戳 并且md5加密的结果
        header.put("Md5", md5OfStr);
        paramDatajson.put("header", header);

        //封装pageinfo
        JSONObject pageInfo = new JSONObject();
        pageInfo.put("pageNo", 1);
        pageInfo.put("pageSize", 10);
        paramDatajson.put("pageInfo", pageInfo);

        //封装mainTable参数
        JSONObject mainTable = new JSONObject();
        mainTable.put("id", "1");
        paramDatajson.put("mainTable", mainTable);

        //封装operationinfo参数
        JSONObject operationinfo = new JSONObject();
        operationinfo.put("operator", "15273");
        paramDatajson.put("operationinfo", operationinfo);

        System.out.println("===请求参数datajson===" + paramDatajson);
        params.put("datajson", paramDatajson);
        //装填参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), JSONObject.toJSONString(entry.getValue())));
        }
        try {
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            response = httpClient.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                //返回信息
                String resulString = EntityUtils.toString(response.getEntity());

                //todo 这里处理返回信息

                System.out.println("成功" + resulString);
            } else {
                System.out.println("获取数据失败，请查看日志" + currentDate + " " + currentTime);
            }
        } catch (Exception e) {
            System.out.println("请求失败" + currentDate + " " + currentTime + "====errormsg:" + e.getMessage());
        }


    }

    public String getMD5Str(String plainText) {
        //定义一个字节数组
        byte[] secretBytes;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString();
    }

    public static String getCurrentTime() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(11, 13) + ":" + (timestamp.toString()).substring(14, 16) + ":"
                + (timestamp.toString()).substring(17, 19);
    }

    public static String getCurrentDate() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(0, 4) + "-" + (timestamp.toString()).substring(5, 7) + "-"
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
     * 获取时间戳   格式如：19990101235959
     *
     * @return 时间戳
     */
    public static String getTimestamp() {
        return getCurDateTime().replace("-", "").replace(":", "").replace(" ", "");
    }

    public static int getIntValue(String v, int def) {
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return def;
        }
    }


    public static String null2String(Object s) {
        return s == null ? "" : s.toString();

    }


}