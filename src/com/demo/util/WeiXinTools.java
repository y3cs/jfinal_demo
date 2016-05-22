package com.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

import net.sf.json.JSONObject;


public class WeiXinTools {

	public static String accessToken = null;
	public static String appid ="wx2dab0ef3ad37c83d";
	public static String secret ="48bcaae0bfd7f5eff7e090a5d2cfcab7";
	/*public static String appid ="wxeb81cab5fde12d5a";
	public static String secret ="44ed1b780999abda71b461b72cb28bad";*/
    private String code;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 授权返回用户信息
	 */
	public String accreditScope() {
		String result =WeiXinTools.getTokenOpendid(code);
		String[] arr = result.split(",");
		String openid=arr[0];
		String accessToken=arr[1];
		String resultMess = getUserInfo(accessToken, openid);
		try {
			String str1=new String(resultMess.getBytes(),"UTF-8");
			JSONObject json = JSONObject.fromObject(str1);
			String nickname=json.getString("nickname");//微信昵称
			System.out.println(nickname);
			System.out.println(json.getString("headimgurl"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
        return "";
	}
	
	/**
	 * 获取ticket
	 */
	public static String getTicket() {
		if (accessToken == null || accessToken == "") {
			accessToken = getAccessToken();
		}
		String ton = accessToken;
		String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + ton + "&type=jsapi";
		String resultString = HttpJSONTest.loadJSON(urlStr);
		String tickets = resultString.substring(resultString.indexOf("ticket") + 9, resultString.indexOf("expires_in") - 3);
		return tickets;
	}

	/**
	 * 获取accessToken
	 */
	public static String getAccessToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		String jsonResult = HttpJSONTest.loadJSON(url);
		int ss = jsonResult.indexOf("access_token") + 15;
		int bb = jsonResult.indexOf("expires_in") - 3;
		accessToken = jsonResult.substring(ss, bb);
		return jsonResult.substring(ss, bb);
	}

	/**
	 * 获取微信用户在公众平台的openId
	 */
	public static String getOpendid(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code=" + code + "&grant_type=authorization_code";
		String jsonResult = HttpJSONTest.loadJSON(url);
		String ss = jsonResult.substring(jsonResult.indexOf("openid") + 9, jsonResult.indexOf("scope") - 3);
		return ss;
	}

	/**
	 * 授权方式获取用户信息
	 */
	public static String getUserInfo(String accessToken, String openId) {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
		String jsonResult = HttpJSONTest.loadJSON(url);
		return jsonResult;
	}
	/**
	 * 获取关注用户信息
	 */
	public static String getAttentionUserInfo(String openId) {
//		String str = new String(jsonResult.getBytes("gbk"), "UTF-8");//抽时间测试此方法可否解决乱码
		try {
			if (accessToken == null || accessToken == "") {
				accessToken = getAccessToken();
			}
			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
			URL urlstr=new URL(url);
			String userinfo = ReadInputStream.readInputStream(ReadInputStream.getInputStreamGet(urlstr));
			return userinfo;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * 获取微信授权的openId acctoken
	 */
	public static String getTokenOpendid(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code=" + code + "&grant_type=authorization_code";
		String jsonResult = HttpJSONTest.loadJSON(url);
		String openid = jsonResult.substring(jsonResult.indexOf("openid") + 9, jsonResult.indexOf("scope") - 3);
		String token = jsonResult.substring(jsonResult.indexOf("access_token") + 15, jsonResult.indexOf("expires_in") - 3);
		return openid+","+token;
	}
	
	
	 /**
	  * 创建Menu
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException    设定文件
	 * @return int    返回类型
	 * @throws
	  */
	    public static String createMenu() {
	      String menu = "{\"button\": [{\"name\": \"菜单\", \"sub_button\": [{\"type\": \"view\", \"name\": \"搜索\", \"url\": \"http://www.soso.com/\"}]}]}";
	        String access_token = getAccessToken();
	        //此处改为自己想要的结构体，替换即可
	        String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
	        System.out.println("action="+action);
	        try {
	           URL url = new URL(action);
	           HttpURLConnection http =   (HttpURLConnection) url.openConnection();    
	           http.setRequestMethod("POST");        
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
	           http.setDoOutput(true);        
	           http.setDoInput(true);
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
	           http.connect();
	           OutputStream os= http.getOutputStream();    
	           os.write(menu.getBytes("UTF-8"));//传入参数    
	           os.flush();
	           os.close();
	           InputStream is =http.getInputStream();
	           int size =is.available();
	           byte[] jsonBytes =new byte[size];
	           is.read(jsonBytes);
	           String message=new String(jsonBytes,"UTF-8");
	           return "返回信息"+message;
	           } catch (MalformedURLException e) {
	               e.printStackTrace();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }    
	        return "createMenu 失败";
	   }

	
	
	/**
	 * Sha1 生成签名
	 * 分享链接签名
	 * str=jsapi_ticket timestamp  nonceStr  url 签名参数 按字典排序
	 * */
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

	
	
}
