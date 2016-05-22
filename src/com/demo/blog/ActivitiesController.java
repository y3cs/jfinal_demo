package com.demo.blog;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.demo.bean.JsonInfo;
import com.demo.bean.Use;
import com.demo.util.WeiXinTools;
import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;



public class ActivitiesController extends Controller {
	
	public static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 创建集赞活动
	 */
	public void saveActiv() {
		try {
			Activities activities = new Activities();
			Activities acs = getModel(Activities.class);
			if (activities != null) {
				if (activities.addActiv(acs)) {
					renderJson("msg", "添加成功！");
				} else {
					renderJson("msg", "添加失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据单个Id查询微助力单个活动信息
	 */
	public void findByActivId() {
		try {
			Activities activities = new Activities();
			Activities acs = getModel(Activities.class);
			Integer activId = acs.getInt("activid");
			System.out.println("id:" + activId);
			if (activId != null) {
				List<Activities> list = activities.findByActivId(activId);
				if (list.size() > 0) {
					renderJson("array", list);
				} else {
					renderJson("array", "没有数据！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 编辑活动信息
	 */
	public void editActivInfo() {
		try {
			Activities activities = new Activities();
			Activities acs = getModel(Activities.class);
			// 获取参数
			Integer id = acs.getInt("activid");
			String title = acs.getStr("activtitle");
			String smallPic = acs.getStr("smallpic");
			String name = acs.getStr("name");
			String address = acs.getStr("address");
			Timestamp endTime = acs.getTimestamp("endtime");
			String body = acs.getStr("activbody");
			if (id != null) {
				// 调用修改接口
				Integer size = 0;
				size = activities.editActivInfo(id, title, smallPic, name,
						address, endTime,body);
				if (size > 0) {
					renderJson("msg", "修改成功！");
				} else {
					renderJson("msg", "修改失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除活动
	 */
	public void deleteActiv() {
		try {
			Activities activities = new Activities();
			Activities acs = getModel(Activities.class);
			// 获取参数
			Integer id = acs.getInt("activid");
			if (id != null) {
				Integer size = 0;
				size = activities.deleteActiv(id);
				if (size > 0) {
					renderJson("msg", "删除成功！");
				} else {
					renderJson("msg", "删除失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据活动类型
	 */
	public void findActivByType() {
		try {
			System.out.println("-------------进入方法你就嗯哼一声--------------");
			Activities activities = new Activities();
			Activities acs = getModel(Activities.class);
			// 获取参数
			Integer type = acs.getInt("activtype");
			System.out.println(type);
			Integer index = getParaToInt(0);
			Integer pageSize = getParaToInt(1);
			if (index == null || pageSize == null) {
				index = 1;
				pageSize = 10;
			}
			if (type != null) {
			Page<Record> list = activities.findActivByType(index, pageSize,type);
				if (list != null) {
					renderJson("array", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**-------------------------获取微信的openid    test------------------------------*/
	/**
	 * test
	 */
	/** 微信测试*/
	public void wechat(){
		System.out.println("进入方法..................");
	    String code = getPara("code");
		System.out.println("code:" + code);
		//1.先获取下单用户的微信信息
				// 读取配置文件
				String APPID= this.readproperties("appid");// 获取appid key对应的值
				System.out.println("appid:"+APPID);
		        String SECRET= this.readproperties("appsecret");// 获取appid key对应的值
		        System.out.println("secret:"+SECRET);
				try {
					// 获取微信公众号对应用户编号openid，accesstoken
					URL url1 = new URL("https://api.weixin.qq.com/sns/oauth2/" +
							"access_token?appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code");
					// 打印url1
					System.out.println("url1:" + url1);
					String use = com.demo.util.ReadInputStream.readInputStream(com.demo.util.ReadInputStream.getInputStreamGet(url1));
					System.out.println("use:" + use);
					Gson gson = new Gson();
					Use uses = gson.fromJson(use, Use.class);
					String openid = uses.getOpenid();
					System.out.println("openid:" + openid);
					String accesstoken = uses.getAccess_token();
					System.out.println("access_token:" + accesstoken);
					URL url2 = new URL("https://api.weixin.qq.com/sns/userinfo?access_token="+accesstoken+"&openid="+openid+"&lang=zh_CN");
					System.out.println("url2:" + url2);
					String info = com.demo.util.ReadInputStream.readInputStream(com.demo.util.ReadInputStream.getInputStreamGet(url2));
					System.out.println("jsoninfo:" + info);
					JsonInfo infos = gson.fromJson(info, JsonInfo.class);
					String nickname = infos.getNickname();
					System.out.println("昵称：" + nickname);
					String headimgurl = infos.getHeadimgurl();
					System.out.println("头像路径：" + headimgurl);
				
					System.out.println("用户信息处理成功");
					
					map.put("openid", openid);
					System.out.println(map.get("openid"));
					System.out.println("test map....");
				
				} catch (MalformedURLException e) {
					e.printStackTrace();
					renderText("重新输入");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	}
	
	/**
	 * 授权返回用户信息
	 */
	public void accreditScope() {
		System.out.println("------------进入方法");
		String code = getPara("code");
		System.out.println("code:" + code);
		String result =WeiXinTools.getTokenOpendid(code);
		String[] arr = result.split(",");
		String openid=arr[0];
		String accessToken=arr[1];
		String resultMess = WeiXinTools.getUserInfo(accessToken, openid);
		try {
			String str1=new String(resultMess.getBytes(),"UTF-8");
			System.out.println("str1:" + str1);
			JSONObject json = JSONObject.fromObject(str1);
			String nickname=json.getString("nickname");//微信昵称
			System.out.println(nickname);
			System.out.println(json.getString("headimgurl"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 读取qiniu.properties配置文件
	 * @param str 要读取的参数名称
	 * @return 返回参数内容
	 */
	public String readproperties(String str){
		Properties pro = new Properties();
		InputStream in = ActivitiesController.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String headpath = pro.getProperty(str);//读取配置文件
		return headpath;
	}
	
}
