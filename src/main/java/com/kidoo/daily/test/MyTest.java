package com.kidoo.daily.test;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class MyTest {

    public static void main(String args[]) {
        MyTest myTest = new MyTest();
        myTest.common(myTest.日切());
    }


    public String 日切() {
        JSONObject params = new JSONObject();
        params.put("渠道号", "TRM");
        params.put("渠道日期", "20160527");
        params.put("渠道流水号", "20160527104711tq");
        params.put("操作机构号", "1000");
        params.put("操作柜员号", "T01");
        params.put("交易码", "日终批处理");
        JSONObject params2 = new JSONObject();
        params2.put("跑批日期", "20160527");
        params.put("请求", params2);

        System.out.println("===>" + params.toString());
        return params.toString();
    }


    public void common(String json) {
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).build();
        HttpPost post = new HttpPost("http://192.168.10.42:31311/service/exec");
        post.setConfig(requestConfig);
        try {
            post.addHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Accept", "application/json");
            post.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
            HttpResponse res = closeableHttpClient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                System.out.println("响应 ===>>> " + result);
            } else {
                System.out.println("失败 ===>>> " + res.getStatusLine().getStatusCode());
            }
            post.abort();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void tcpRequest() throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1", 31301);

            JSONObject qJSONObject = new JSONObject();

            JSONObject wJSONObject = new JSONObject();
            wJSONObject.put("充值来源户ID", 14);
            wJSONObject.put("个人户ID", 13);
            wJSONObject.put("金额", 10000.0);
            qJSONObject.put("请求", wJSONObject);

            qJSONObject.put("渠道号", "TRM");
            qJSONObject.put("渠道日期", "20160421");
            qJSONObject.put("渠道流水号", "d7eafd51-60d4-4b5f-a5c7-69f3d180d12a");
            qJSONObject.put("操作机构号", 67);
            qJSONObject.put("操作柜员ID", 0);
            qJSONObject.put("交易码", "充值");
            String msg = qJSONObject.toString();
            System.out.println("msg ===>>>>" + msg);
            int len = msg.getBytes("utf-8").length;
            msg = "0000" + len + msg;
            System.out.println("after json format ===>>>>" + msg);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(msg.getBytes("utf-8"));
            outputStream.flush();

            InputStream inputstream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int n = inputstream.read(bytes);
            System.out.println("from server ===>>>" + new String(bytes, 0, n));

            outputStream.close();
            inputstream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
