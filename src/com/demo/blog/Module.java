package com.demo.blog;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * 组件表
 * 
 * @author LH
 * 
 */
@SuppressWarnings("serial")
public class Module extends Model<Module> {
	public static final Module m = new Module();

	/**
	 * 添加组件
	 */
	public boolean addModule(Module mo) {
		try {
			if (mo != null) {
				Record moduls = new Record();
				moduls.set("modulecontent", mo.getStr("modulecontent"))
						.set("acitvid", mo.getInt("acitvid"))
						.set("type", mo.getBoolean("type"))
						.set("status", mo.getBoolean("status"));
				Db.save("module", moduls);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询组件
	 */
	public List<Module> findModule() {
		try {
			return super.find("select * from module where 1=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改组件(根据id修改)
	 */

	public Integer editModule(Integer moduleId, String moduleContent,
			Integer activId, boolean status) {
		try {
			return Db
					.update("update module m set m.modulecontent=?,m.acitvid=?,m.status=? where m.moduleid=?",
							moduleContent, activId, status, moduleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除组件(根据id删除)
	 */
	public Integer deleteModule(Integer moduleId) {
		try {
			return Db.update("delete from module where moduleid=?", moduleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
