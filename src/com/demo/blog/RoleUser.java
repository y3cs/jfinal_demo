package com.demo.blog;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

/**
 * RoleUser	<br>
 * role_user_id		id<br>
 * role_id			角色id<br>
 * userid			用户id<br>
 * username			用户名字<br>
 * createtime		创建时间<br>
 * @author LH
 *
 */
public class RoleUser extends Model<RoleUser>{

	private static final long serialVersionUID = 1L;
	private static final RoleUser ru = new RoleUser();
	/** 根据用户id查询用户角色*/
	public List<RoleUser> findRoleByUser(String id){
		try {
			if(id != null){
				return RoleUser.ru.find("select * from roleuser ru where ru.userid=?", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
