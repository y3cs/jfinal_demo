package com.demo.blog;

import java.util.List;

import com.jfinal.core.Controller;

/**
 * 组件Controller
 * 
 * @author LH
 * 
 */
public class ModuleController extends Controller {

	/**
	 * 添加组件
	 */
	public void saveModule() {
		try {
			Module module = new Module();
			Module modules = getModel(Module.class);
			if (modules != null) {
				// 调用addModule
				if (module.addModule(modules)) {
					renderJson("msg", "添加成功！");
				} else {
					renderJson("msg", "添加失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询组件
	 */
	public void findModule() {
		try {
			Module module = new Module();
			Module modules = getModel(Module.class);
			List<Module> list = module.findModule();
			if (list.size() > 0) {
				renderJson("array", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据id修改组件
	 */
	public void editModule() {
		try {
			Module module = new Module();
			Module modules = getModel(Module.class);
			// 获取参数
			Integer moduleId = modules.getInt("moduleid");
			System.out.println("ID:" + moduleId);
			String content = modules.getStr("modulecontent");
			System.out.println("content:" + content);
			Integer acitvid = modules.getInt("acitvid");
			System.out.println("acitvid:" + acitvid);
			boolean status = modules.getBoolean("status");
			System.out.println("status:" + status);
			if (moduleId != null) {
				Integer size = 0;
				size = module.editModule(moduleId, content, acitvid, status);
				System.out.println("size:" + size);
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
	 * 根据id删除组件
	 */
	public void deleteModule() {
		try {
			Module module = new Module();
			Module modules = getModel(Module.class);
			// 获取id
			Integer moduleId = modules.getInt("moduleid");
			if (moduleId != null) {
				Integer size = 0;
				size = module.deleteModule(moduleId);
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
}
