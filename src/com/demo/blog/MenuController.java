package com.demo.blog;

import java.util.List;

import com.jfinal.core.Controller;

/**
 * MenuController <br>
 * 
 * @author LH
 * 
 */
public class MenuController extends Controller {

	/** 查询所有菜单 */
	public void findAllMenu() {
		try {
			Menu menu = new Menu();
			Menu menus = new Menu();
			if (menus != null) {
				List<Menu> list = menu.findAll();
				if (list.size() > 0 && !list.isEmpty()) {
					renderJson("array", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
