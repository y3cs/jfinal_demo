package com.demo.blog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.demo.util.MD5Tools;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.qiniu.util.Json;

/**
 * UserController <br>
 * 
 * @author LH
 * 
 */
public class UserController extends Controller {
	public static final Logger logger = Logger.getLogger(User.class);

	public void index() {
		// render("test.html");//全路径是/path/blog.html 如果是render("/blog.html")
		// 全路径表示/blog.html
	}

	/**
	 * 用户注册
	 */
	public void saveUser() {
		try {
			User user = new User();
			User users = getModel(User.class);
			// 用户名
			String username = users.getStr("username");
			// 密码
			String pwd = users.getStr("userpwd");

			// 权限组
			String permissionGroup = users.getStr("permissiongroup");
			// 判断用户名是否注册
			if (username.trim() != null) {
				// 如果用户没注册
				if (user.isRegister(username) == null) {
					if (pwd.trim() != null && username.trim() != null
							&& permissionGroup.trim() != null) {
						// 如果用户名和密码和权限组都不为空
						// 执行else
						if (user.addUser(users)) {
							renderJson("msg", "注册成功！");
						} else {
							renderJson("msg", "注册失败");
						}
					} else {
						renderJson("msg", "用户名或密码不全！");
					}
				} else {
					renderJson("msg", "该用户名已经存在！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 用户登录
	 */
	public void login() {
		try {
			User user = new User();
			User users = getModel(User.class);
			RoleUser ru = new RoleUser();
			// 用户名
			String username = users.getStr("username");
			// 密码
			String pwd = MD5Tools.MD5(users.getStr("userpwd"));
			if (username.trim() != null) {
				if (user.isRegister(username) == null) {
					renderJson("msg", "你还未注册用户！请你先去注册");
				} else {
					renderJson("msg", "请输入正确的账号或密码！");
				}
			}
			String uuid = null;
			Integer roleid = null;
			if (username.trim() != null && pwd.trim() != null) {
				if (user.login(username, pwd) != null) {
					List<User> list = user.findByUserId(username);
					if (list.size() > 0) {
						Cookie nameCookie = new Cookie("name", username);
						Cookie pwdCookie = new Cookie("pwd", pwd);
						// cookie的声明周期
						nameCookie.setMaxAge(24 * 60 * 60);
						pwdCookie.setMaxAge(24 * 60 * 60);
						if (list.size() > 0) {
							for (User user2 : list) {
								uuid = user2.getStr("userid");
								// 根据uuid查询对应的角色
								List<RoleUser> list1 = ru.findRoleByUser(uuid);
								if (list1.size() > 0) {
									for (RoleUser roleUser : list1) {
										roleid = roleUser.getInt("role_id");
									}
								}
							}
						}

						renderJson("msg", "登陆成功！" + roleid);
					}

				} else {
					renderJson("msg", "账号或密码输入错误！");
				}
			} else {
				renderJson("msg", "账号或密码输入错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据用户id删除用户信息
	 */
	public void deleteUserInfo() {
		try {
			User user = new User();
			User users = getModel(User.class);
			String userid = users.getStr("userid");
			if (userid.trim() != null && userid != "") {
				Integer size = 0;
				size = user.deleteByUserId(userid);
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
	 * 根据id修改用户信息
	 */
	public void editUserInfo() {
		try {
			User user = new User();
			User users = getModel(User.class);
			String userid = users.getStr("userid");
			String permission = users.getStr("permissiongroup");
			String shopname = users.getStr("shopname");
			String tel = users.getStr("tel");
			String address = users.getStr("address");
			String headimg = users.getStr("headimg");
			String email = users.getStr("email");
			if (permission.trim() != null && permission != "") {
				Integer size = 0;
				size = user.editUserInfo(userid, permission, shopname, tel,
						address, headimg, email);
				System.out.println("szie:" + size);
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
	 * 根据用户Id获取单个用户信息
	 */
	public void findInfoByUserId() {
		try {
			User user = new User();
			User users = getModel(User.class);
			// 获取用户id
			String userId = users.getStr("userid");
			if (userId.trim() != null && userId != "") {
				List<User> list = user.findInfoByUserId(userId);
				if (list.size() > 0) {
					renderJson("array", list);
				} else {
					renderJson("array", "没有数据");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页测试 查询所有用户信息
	 */
	public void findByUserInfo() {
		try {
			User user = new User();
			User users = getModel(User.class);
			Integer index = getParaToInt(0);
			Integer pageSize = getParaToInt(1);
			if (index == null || pageSize == null) {
				index = 1;
				pageSize = 10;
			}
			Page<Record> list = user.findUserInfo(index, pageSize);
			if (list != null) {
				renderJson("array", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * login <br>
	 * test
	 */
	// HttpServletResponse response = ;
	public void test() {
		System.out.println("--------------进入方法你就嗯哼一声------");
		User user = new User();
		User users = getModel(User.class);
		RoleUser ru = new RoleUser();
		RoleMenu rm = new RoleMenu();
		// 获取登陆的用户名和密码
		String name = users.getStr("username");
		String pwd = MD5Tools.MD5(users.getStr("userpwd"));
		System.out.println(name);
		System.out.println(pwd);
		String uuid = null;
		Integer roleid = null;
		Integer rmId = null;
		if (users != null) {
			if (user.login(name, pwd) != null) {
				Cookie nameCookie = new Cookie("name", name);
				Cookie pwdCookie = new Cookie("pwd", pwd);
				// cookie的声明周期
				nameCookie.setMaxAge(24 * 60 * 60);
				pwdCookie.setMaxAge(24 * 60 * 60);

				// 根据名字获取用户的id,然后根据id查询对应的角色
				List<User> list = user.findByUserId(name);
				if (list.size() > 0) {
					for (User user2 : list) {
						uuid = user2.getStr("userid");
						System.out.println(uuid);
						// 根据uuid查询对应的角色
						List<RoleUser> list1 = ru.findRoleByUser(uuid);
						if (list1.size() > 0) {
							for (RoleUser roleUser : list1) {
								roleid = roleUser.getInt("role_id");

								System.out.println("roleid:" + roleid);
							}
						}
					}
				}

				renderJson("msg", "登陆成功！" + roleid);

			} else {
				renderJson("msg", "登陆失败");
			}
		}
	}

}
