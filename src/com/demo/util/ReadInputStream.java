package com.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadInputStream{
	// 获取输入流
	public static InputStream  getInputStreamGet(URL url) throws IOException{
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Charset", "UTF-8");
		return conn.getInputStream();
	}
	
	// 获取输入流
	public static InputStream  getInputStreamPost(URL url) throws IOException{
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Charset", "UTF-8");
		return conn.getInputStream();
	}
	
	// 解析输入流为字符串
	public static String readInputStream(InputStream inputStream) throws IOException {  
	    byte[] buffer = new byte[1024];  
	    int len = 0;  
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	    while((len = inputStream.read(buffer)) != -1) {  
	        bos.write(buffer, 0, len);  
	    }  
	    bos.close(); 
	    inputStream.close();
	    return new String(bos.toByteArray(),"UTF-8");
	}
	
	/**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
 
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
 
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
 
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
	}
	
	/**
     * 解析企业微信发来的请求（获取加密内容）（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static String parseString(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
 
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        
        // 转化成字符串形式
        String st = document.asXML();
 
        // 释放资源
        inputStream.close();
        inputStream = null;
        return st;
	}
	
	/**
     * 解析微信发来的input请求（XML）
     *
     * @param request
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
 
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
 
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
	}
} 