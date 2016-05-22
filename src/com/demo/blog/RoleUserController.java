package com.demo.blog;

import java.util.List;

import com.jfinal.core.Controller;
/**
 * RoleUserController 
 * 
 * @author LH
 *
 */
public class RoleUserController extends Controller{

	/**
	 * 查询用户属于 哪个角色
	 */
	public void findRoleByUser(){
		/*try {
			RoleUser roleuser = new RoleUser();
			RoleUser rus = getModel(RoleUser.class);
			Integer id = rus.getInt("userid");
			System.out.println(id);
			if(id != null){
			List<RoleUser> list = roleuser.findRoleByUser(id);
				if(list.size() > 0 && !list.isEmpty()){
					renderJson("array",list);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
