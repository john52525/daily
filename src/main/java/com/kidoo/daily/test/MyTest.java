package com.kidoo.daily.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

public class MyTest {

	public static void main(String args[]) {
		MyTest myTest = new MyTest();
		myTest.test01();
	}

	public void test01() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("timer task");
			}
		};
		Timer timer = new Timer();
		timer.schedule(timerTask, 0, 1000);
	}

	public static void httpRequest() {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://127.0.0.1:31311/service/exec");
		JSONObject params = new JSONObject();
		params.put("渠道号", "TRM");
		params.put("渠道日期", "20160414");
		params.put("渠道流水号", "d7eafd51-60d4-4b5f-a5c7-69f3d180d12a");
		params.put("操作机构ID", 89);
		params.put("操作柜员ID", 0);
		params.put("交易码", "充值");

		JSONObject params2 = new JSONObject();
		params2.put("充值来源户ID ", 23);
		params2.put("个人户ID", 22);
		params2.put("金额", 10000.0);

		params.put("请求", params2);

		System.out.println("===>" + params.toString());
		try {
			post.addHeader("Content-type", "application/json; charset=utf-8");
			post.setHeader("Accept", "application/json");
			post.setEntity(new StringEntity(params.toString(), Charset.forName("UTF-8")));
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				String result = EntityUtils.toString(res.getEntity());// 返回json格式：
				System.out.println("响应--->>>" + result);
			} else {
				System.out.println("失败--->>>" + res.getStatusLine().getStatusCode());
			}
			post.abort();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 发送 get请求
	 */
	public void get() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet("http://www.baidu.com/");
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				// 打印响应状态
				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度
					System.out.println("Response content length: " + entity.getContentLength());
					// 打印响应内容
					// System.out.println("Response content: " +
					// EntityUtils.toString(entity));
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			qJSONObject.put("操作机构ID", 67);
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
