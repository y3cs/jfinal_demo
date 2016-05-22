package com.demo.blog;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
/**
 * Role<br>
 * 
 * role_id		id<br>
 * role_name		角色名称<br>
 * app_array	可操作权限数组<br>
 * createtime 	创建时间<br>
 * userid		用户id(待删除)<br>
 * stauts		状态<br>
 * @author LH
 *
 */
public class Role extends Model<Role>{
	public static final Role role = new Role();
	

	/**
	 * 根据当前的用户查询当前用户相对应的角色(待删除)
	 */
	public List<Role> findRoleByUserId(Integer id){
		try {
			if(id != null){
				return Role.role.find("select * from", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return null;
	}
	
}
