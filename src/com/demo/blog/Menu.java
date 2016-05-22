package com.demo.blog;

import java.util.List;


import com.jfinal.plugin.activerecord.Model;

/**
 * Menu  	<br>
 * 
 * menu_id  <br>
 * menu_name 		 菜单名称<br>
 * menu_ca 			菜单控制器<br>
 * desc 			描述 <br>
 * parent_menuid  	父类菜单id<br>
 * menu_level  		菜单层次<br>
 * menu_type		默认为0不可删除<br>
 * menu_icon		菜单图标<br>
 * sort				排序<br>
 * createtime		创建时间<br>
 * 
 * @author LH
 *
 */

@SuppressWarnings("serial")
public class Menu extends Model<Menu> {
	public static final Menu menu = new Menu();

	/** 查询所有菜单*/
	public List<Menu> findAll(){
		try {
			return menu.find("select * from menu where 1=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
