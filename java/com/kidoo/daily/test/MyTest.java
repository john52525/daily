package com.kidoo.daily.test;

import com.kidoo.daily.utils.HttpRequester;
import com.kidoo.daily.utils.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/16.
 */
public class MyTest {

    /*{
      "渠道号":"TRM",
      "渠道日期":"20160414",
      "渠道流水号":"d7eafd51-60d4-4b5f-a5c7-69f3d180d12a",
      "操作机构ID":1,
      "操作柜员ID":1,
      "交易码":"充值"
      "请求": {
          "充值来源户ID":108,
          "个人户ID":107,
          "金额":10000.0
      }
    }*/
    public static void main(String args[]) {
        HttpRequester httpRequester = new HttpRequester();
        Map qMap = new HashMap<String, String>();
        JSONObject qJSONObeject = new JSONObject();
        qJSONObeject.put("渠道号", "TRM");
        qJSONObeject.put("渠道日期", "20160414");
        qJSONObeject.put("渠道流水号", "d7eafd51-60d4-4b5f-a5c7-69f3d180d12a");
        qJSONObeject.put("操作机构ID", "1");
        qJSONObeject.put("操作柜员ID", "1");
        qJSONObeject.put("交易码", "充值");
        JSONObject eJSONObeject = new JSONObject();
        eJSONObeject.put("充值来源户ID", "108");
        eJSONObeject.put("个人户ID", "107");
        eJSONObeject.put("金额", "10000.0");
        qJSONObeject.put("请求", eJSONObeject);
        try {
            httpRequester.sendPost("http://127.0.0.1:31311", qMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
