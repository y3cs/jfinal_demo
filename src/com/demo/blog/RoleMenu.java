package com.demo.blog;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * RoleMenu<br>
 * 
 * role_menu_id		id<br>
 * role_id			角色id<br>
 * menu_id			菜单id<br>
 * menu_url			菜单url<br>
 * createtime		创建时间<br>
 * @author LH
 * 
 */
public class RoleMenu extends Model<RoleMenu> {
	
	private static final long serialVersionUID = 1L;
	public static final RoleMenu rm = new RoleMenu();

	/**
	 * 根据角色id查询菜单
	*/
	public List<RoleMenu> findMenuById(Integer id){
		try {
			return RoleMenu.rm.find("select * from rolemenu rm where rm.role_id=?", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
