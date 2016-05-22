package com.demo.blog;

import java.util.List;


import com.demo.util.MD5Tools;
import com.demo.util.jfinalUtil;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class User extends Model<User> {
	public static final User u = new User();
	public static final Logger logger = Logger.getLogger(User.class);

	/**
	 * 用户注册
	 */
	public boolean addUser(User user) {
		// 生成uuid
		String uuid = jfinalUtil.createUUID();
		List<User> uList = null;
		try {
			uList = User.u.find("select * from user where username='"
					+ user.get("username") + "'");
			if (uList == null || uList.isEmpty()) {
				Record users = new Record();
				users.set("userid", uuid)
						.set("username", user.getStr("username"))
						.set("userpwd", MD5Tools.MD5(user.getStr("userpwd")))
						.set("permissiongroup", user.getStr("permissiongroup"))
						.set("email", user.getStr("email"))
						.set("createtime", user.getTimestamp("createtime"))
						.set("state", user.getInt("state"))
						.set("shopname", user.getStr("shopname"))
						.set("tel", user.getStr("tel"))
						.set("address", user.getStr("address"))
						.set("headimg", user.getStr("headimg"))
						.set("roleid", user.getInt("roleid"));
				Db.save("user", users);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 用户登录
	 */
	public User login(String name, String pwd) {
		try {
			return super.findFirst(
					"select * from user u where u.username=? and u.userpwd=?",
					name, pwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断是否注册
	 */
	public User isRegister(String name) {
		try {
			return super.findFirst("select * from user u where u.username=?",
					name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 根据用户姓名查询
	 */
	public List<User> findByUserId(String username) {
		try {
			if (username != null) {
				return super.find("select * from user u where u.username=?",
						username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 根据用户id修改用户信息
	 */
	public Integer editUserInfo(String userId, String permissiongroup,
			String shopname, String tel, String address, String headimg,
			String email) {
		try {
			return Db
					.update("update user u set u.email=?,u.permissiongroup=?,u.shopname=?,u.tel=?,u.address=?,u.headimg=? where u.userid=?",
							email, permissiongroup, shopname, tel, address,
							headimg,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据id删除用户信息
	 */
	public Integer deleteByUserId(String userId) {
		try {
			return Db.update("delete from user where userid=?", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户id查询用户信息
	 */
	public List<User> findInfoByUserId(String userId){
		try {
			return super.find("select * from user u where u.userid=?", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * page test
	 */
	@SuppressWarnings("unchecked")
	public Page<Record> findUserInfo(Integer index, Integer size){
		try {
			 Page<Record> page =  Db.paginate(index, size, "select * ","from user u where 1=?",1);
			 return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
