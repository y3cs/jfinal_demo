package com.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


/**
 * 工具类
 * @author Administrator
 *
 */
public class Tools {
	//通过控制台获取AppId,SecretId,SecretKey
	public static final int APP_ID = 10009908;
	public static final String SECRET_ID = "AKID27irii5RnqouHJHKAuuY0t74CoV6sAhi";
	public static final String SECRET_KEY = "FD2PQ7laTaXs8fkVpSpWyvePBJ5QHcas";
	/**
	 * 保存上传的文件
	 * @param path 保存文件的路径
	 * @param file 要保存的文件
	 * @param folder 保存文件的文件夹名称
	 * @return
	 * @throws IOException
	 */
	public String savefile(String savepath,File file,String folder,String domain) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(savepath);
		IOUtils.copy(fis, fos);
		fos.flush();
		fos.close();
		fis.close();
		String repath = savepath.substring(savepath.indexOf(folder));//截取字符串从file开始的后半部分，包含file在内
		//repath = new Tools().readproperties("url") + repath;//hunqing用这句
		repath = "http://localhost:8080/" +repath;//hunqing2用这句
		return repath;
	}
	/**
	 * 上传图片到腾讯云
	 * @return
	 */
/*	public String cloudfile(String remoatpath,String filename,String savepath){
		CosCloud cos = new CosCloud(APP_ID, SECRET_ID, SECRET_KEY);
		String bucketName = "yijiang";
		String result;
		try {
			//result = cos.createFolder(bucketName, remoatpath);
			result = cos.uploadFile(bucketName, remoatpath+filename , savepath);
			System.out.println("result:"+result);
	        JSONObject jsonresult = new JSONObject(result);
	        JSONObject data = jsonresult.getJSONObject("data");
	        String accessurl = data.getString("access_url");
	        return accessurl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	/**
	 * 把一个数组中的数据组合成一个字符串，之间用逗号隔开
	 * @param o
	 * @return
	 */
	public String getstr(Object[] o){
		String str = "";
		for(int i=0;i<o.length;i++){
			if(i!=o.length-1){
				str = str + o[i].toString() + ",";
			}else{
				str = str + o[i].toString();//如果是最后一个数据，后面不加逗号
			}
		}
		return str;
	}
	/**
	 * 方法一,验证手机号，手机号正确返回true，错误返回false
	 * @param mobiles
	 * @return
	 */
	public boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 计算文件上传时间和现在时间之间的差值
	 * @param date 文件上传时间
	 * @return {5，分钟前}{6，小时前}{10，2015.5}
	 */
	public Object[] gettimeunit(Date date){
		Date now = new Date();
		long l=now.getTime()-date.getTime();
		long day=l/(24*60*60*1000);
		if(day==0){//差值小于1天
			long hour=(l/(60*60*1000)-day*24);
			if(hour==0){//如果差值小于1小时
				long min=((l/(60*1000))-day*24*60-hour*60);
				return new Object[]{String.valueOf(min),"分钟前"};
			}else{
				return new Object[]{String.valueOf(hour),"小时前"};
			}
		}else{
			Integer year = date.getYear() + 1900;
			Integer month = date.getMonth() + 1;
			Integer day2 = date.getDate();
			return new Object[]{day2.toString(),year.toString()+"."+month.toString()};
		}
	}
	
	
	
	
	/**
	 * 判断文件后缀
	 * @param filename
	 * @return
	 */
	public boolean checkfiletype(String filename){
		String type = filename.substring(filename.lastIndexOf(".")+1);//截取文件名的后缀
		if(type.equals("ppt")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 读取qiniu.properties配置文件
	 * @param str 要读取的参数名称
	 * @return 返回参数内容
	 */
	public String readproperties(String str){
		Properties pro = new Properties();
		InputStream in = Tools.class.getClassLoader().getResourceAsStream("qiniu.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String headpath = pro.getProperty(str);//读取配置文件
		return headpath;
	}
	/**
	 * 生成一个由时间和随机数组成的字符串
	 * @return
	 */
	public String getdatenum(){
		String num = "";
		for(int i=0;i<10;i++){
			num += String.valueOf((int)(Math.random()*10));//得到10位随机数
		}
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dt.format(new Date());//获取当前时间字符串
		String datenum = date + num;
		return datenum;
	}
	/**
	 * 生成一个有数字，字母，时间组成的订单流水号
	 * @return
	 */
	public String reqnum(){
		String num = "";
		for(int i=0;i<10;i++){
			num += String.valueOf((int)(Math.random()*10));//得到10位随机数
		}
		String letter = "";
		String chars = "abcdefghijklmnopqrstuvwxyz";
		for(int m=0;m<5;m++){
			letter += chars.charAt((int)(Math.random() * 26));
		}
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dt.format(new Date());//获取当前时间字符串
		String str = num + date + letter;
		return str;
	}
	/**
	 * 获得一个当前月份的时间字符串
	 * @return
	 */
	public String getmonth(){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM");
		String str = dt.format(new Date());
		return str;
	}
	/**
	 * 查询PPT的预览图片，返回第一张路径
	 * @return
	 */
	public String findpptfirstimg(String imgpath){
		String str="";
		File folder = new File(imgpath);
		File[] file = folder.listFiles();//得到文件夹下的所有图片文件
		for(int i=0;i<file.length;i++){
			String s = file[i].toString();
			String path = s.substring(s.indexOf("file"));//截取字符串从file开始的后半部分，包含file在内
			path = path.replaceAll("\\\\", "/");
			path = "http://www.scwywh.com/" + path;
			if(i==0){
				str = path;
			}else{
				str = str + ";" + path;
			}
		}
		String[] arr = str.split(";");
		Arrays.sort(arr);
		return arr[0];
	}
	/**
	 * 传进一张幻灯片图片，处理名字
	 * @param imgname
	 * @return
	 */
	public String newimgname(String imgname){
		String str = imgname.replaceAll("幻灯片", "");
		str = str.substring(0, str.indexOf("."));
		Integer n = Integer.valueOf(str);
		String m = "";
	    if(n<10){
	    	m = "1000" + n;
	    }else if(n<100&&n>9){
	    	m = "100" + n;
	    }else if(n<1000&&n>99){
	    	m = "10" + n;
	    }
		return m;
	}
	/**
	 * 去掉一个字符串中的特殊符号
	 * @return
	 */
	public String delspecialsign(String str){
		if(str.indexOf("?")!=-1){
			str = str.replaceAll("[?]", "");
		}
		if(str.indexOf("*")!=-1){
			str = str.replaceAll("[*]", "");
		}
		if(str.indexOf("\\")!=-1){
			str = str.replaceAll("\\\\", "");//去掉\
		}
		if(str.indexOf("/")!=-1){
			str = str.replaceAll("/", "");
		}
		if(str.indexOf(":")!=-1){
			str = str.replaceAll(":", "");
		}
		if(str.indexOf("\"")!=-1){
			str = str.replaceAll("\"", "");
		}
		if(str.indexOf("<")!=-1){
			str = str.replaceAll("<", "");
		}
		if(str.indexOf(">")!=-1){
			str = str.replaceAll(">", "");
		}
		if(str.indexOf("|")!=-1){
			str = str.replaceAll("[|]", "");
		}
		if(str.indexOf(",")!=-1){
			str = str.replaceAll(",", "");
		}
		if(str.indexOf("，")!=-1){
			str = str.replaceAll("，", "");
		}
		str = str.replaceAll(" ", "");
		return str;
	}
	/**
	 * 获得网站首页的模块名称
	 * @param id
	 * @return
	 */
	public String gettypename(Integer id){
		if(id==1){
			return "轮播通栏";
		}else if(id==2){
			return "二屏广告";
		}else if(id==3){
			return "最新客片";
		}else if(id==4){
			return "最新样片";
		}else if(id==5){
			return "广告位";
		}else if(id==6){
			return "旗下品牌";
		}else{
			return "品牌联盟";
		}
	}
	/**
	 * 传入一个url，判断这是哪个页面，返回页面名称
	 * @param url
	 * @return
	 */
	public String urltopage(String url){
		String page = "";
		System.out.println("reporturl:"+url);
		if(url.equals("/uindex.action")){
			page = "index.jsp";
		}else if(url.equals("/photoshow.action")){
			page = "photoshow.jsp";
		}else if(url.equals("/showdetail.action")){
			page = "showdetail.jsp";
		}else if(url.equals("/shownextdetail.action")){
			page = "showdetail.jsp";
		}else if(url.equals("/aboutone.action")){
			page = "about.jsp";
		}else if(url.equals("/abouttwo.action")){
			page = "service.jsp";
		}else if(url.equals("/contact.action")){
			page = "contact.jsp";
		}else if(url.equals("/koubei.action")){
			page = "koubei.jsp";
		}else if(url.equals("/huodong.action")){
			page = "huodong.jsp";
		}else if(url.equals("/showhuodong.action")){
			page = "showhuodong.jsp";
		}else if(url.equals("/shownexthuodong.action")){
			page = "showhuodong.jsp";
		}
		return page;
	}
	/**
	 * 传入时间字符串，日期加一天，返回新的时间字符串
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String adddate(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(str);
			Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(date); 
		    calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
		    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
			String time = sdf.format(date);
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
