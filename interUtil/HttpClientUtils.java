package com.easemob.easeui.interUtil;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.List;


public class HttpClientUtils {

	public static String get(String baseUrl,List<BasicNameValuePair> params ) {

		// 对参数编码
		String param = URLEncodedUtils.format(params, "UTF-8");
		// 将URL与参数拼接  发送请求
		HttpGet getMethod = new HttpGet(baseUrl + "?" + param);
		//创建默认客户端的实例
		HttpClient httpClient = new DefaultHttpClient();
		try {
			//执行get请求返回 响应对象
			HttpResponse response = httpClient.execute(getMethod); // 发起GET请求
			//获取响应状态码并判断
			if(response.getStatusLine().getStatusCode() == 200){
				System.out.println("连接成功");
				return EntityUtils.toString(response.getEntity(),"utf-8");

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public static String post(String baseUrl,List<BasicNameValuePair> params){
		try {

			HttpPost postMethod = new HttpPost(baseUrl);
			//设置参数的类型
			postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); //将参数填入POST Entity中 

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse response = httpClient.execute(postMethod); //执行POST方法 

			if(response.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(response.getEntity(),"utf-8");

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

}
