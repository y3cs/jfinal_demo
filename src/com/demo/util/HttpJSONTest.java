package com.demo.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//import java.net.URLEncoder;

/**
  * @java后台获取json数据
   *工具类
  * @date:2013-11-11 下午6:12:26  
  */
public class HttpJSONTest {
	
	
	public static final String GET_URL="https://pay.weixin.qq.com/wiki/doc/api/index.html";
	 
//	public static final String POST_URL="https://pay.weixin.qq.com/wiki/doc/api/index.html";
	
	public static String readContentFromGet(){
	// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
	String getURL;
	try {
		getURL = GET_URL;
		System.out.println("getURL-----="+getURL);
		URL url =new URL(getURL);
		// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		HttpURLConnection connection =(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
		// 服务器
		connection.connect();
		// 取得输入流，并使用Reader读取
		BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码
		System.out.println("=============================");
		System.out.println("Contents of get request");
		System.out.println("=============================");
		String lines;
		while((lines= reader.readLine())!=null){
		System.out.println(lines);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "success";
	}
	
	
	
	

/**
* @param args
*/


public static String loadJSON(String url){
   StringBuilder json=new StringBuilder();
   try{
   URL oracle =new URL(url);
   URLConnection yc = oracle.openConnection();
   BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
   String inputLine =null;
   while ((inputLine=in.readLine())!=null){
   json.append(inputLine);
    }
   in.close();
   }catch(MalformedURLException e){
   }catch(IOException e){
   }
   return json.toString();
}



 /**
  * 发送HttpPost请求
  *
  * @param strURL
  *            服务地址
  * @param params
  *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
  * @return 成功:返回json字符串<br/>
  */
 public static String post(String strURL, String params) {

  try {
   URL url = new URL(strURL);// 创建连接
   HttpURLConnection connection = (HttpURLConnection) url
           .openConnection();
   connection.setDoOutput(true);
   connection.setDoInput(true);
   connection.setUseCaches(false);
   connection.setInstanceFollowRedirects(true);
   connection.setRequestMethod("POST"); // 设置请求方式
   connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
   connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
   connection.connect();
   OutputStreamWriter out = new OutputStreamWriter(
           connection.getOutputStream(), "UTF-8"); // utf-8编码
   out.append(params);
   out.flush();
   out.close();
   // 读取响应
   int length = (int) connection.getContentLength();// 获取长度
   InputStream is = connection.getInputStream();
   if (length != -1) {
    byte[] data = new byte[length];
    byte[] temp = new byte[512];
    int readLen = 0;
    int destPos = 0;
    while ((readLen = is.read(temp)) > 0) {
     System.arraycopy(temp, 0, data, destPos, readLen);
     destPos += readLen;
    }
    String result = new String(data, "UTF-8"); // utf-8编码

    return result;
   }
  } catch (IOException e) {
   // TODO Auto-generated catch block
//   e.printStackTrace();
  }
  return "error"; // 自定义错误信息
 }
}