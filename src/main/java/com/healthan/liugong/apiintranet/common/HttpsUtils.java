package com.healthan.liugong.apiintranet.common;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class HttpsUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpsUtils.class);


	/**
	 * 模拟请求
	 *
	 * @param url		资源地址
	 * @param map	参数列表
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String sendPost(String url, Map<String,String> map) throws ClientProtocolException, IOException {
		String body = "";

		//创建自定义的httpclient对象
		CloseableHttpClient client = SSLUtils.getCloseableHttpClient();
//		CloseableHttpClient client = HttpClients.createDefault();

		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		//装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if(map!=null){
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		//设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

		System.out.println("请求地址："+url);
		System.out.println("请求参数："+nvps.toString());

		//设置header信息
		//指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		//获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, "UTF-8");
		}
		EntityUtils.consume(entity);
		//释放链接
		response.close();
		return body;
	}

	/**
	 * get请求
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String url, Map<String,String> map) throws Exception{
		String body = "";

		CloseableHttpClient httpclient=SSLUtils.getCloseableHttpClient();
		try {
			//装填参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if(map!=null){
				for (Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			//转化为键值对
			String params = EntityUtils.toString(new UrlEncodedFormEntity(nvps,"UTF-8"));

			String senurl=params==null?"":url+"?"+params;

			HttpGet httpget = new HttpGet(senurl);

			CloseableHttpResponse response = httpclient.execute(httpget);

			try {
				HttpEntity entity = response.getEntity();
				body=EntityUtils.toString(entity);
			}finally {
				response.close();
			}
		}catch (Exception e){
			logger.error(e.getMessage());
		}finally {
			httpclient.close();
		}
		return body;
	}


}
