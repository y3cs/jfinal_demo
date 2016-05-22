package com.demo.blog;

import java.util.List;

import com.jfinal.core.Controller;

public class RoleMenuController extends Controller {

	/**
	 * 根据roleid查询权限
	 */
	public void findMenuByRoleId() {
		try {
			System.out.println("----------进入方法你就嗯哼一声------------");
			RoleMenu rolemenu = new RoleMenu();
			RoleMenu rolemenus = getModel(RoleMenu.class);
			// 获取roleId
			Integer id = rolemenus.getInt("role_id");
			System.out.println(id);
			if (id != null) {
				List<RoleMenu> list = rolemenu.findMenuById(id);
				if (list.size() > 0) {
					renderJson("array", list);
				} else {
					renderJson("msg", "没有数据！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
