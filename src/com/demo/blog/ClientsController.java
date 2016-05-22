package com.demo.blog;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * ClientsController 控制器 客户资料
 * 
 * @author yl
 * 
 */
public class ClientsController extends Controller {

	/**
	 * 保存客户资料
	 */
	public void addClients() {
		Clients clients = new Clients();
		Clients client = getModel(Clients.class);
		ActivitiesController ac = new ActivitiesController();
		String openid = ac.map.get("openid");
		System.out.println("play_openid:" + openid);
		try {
			if (clients != null) {
				// addClients
				clients.set("openid", openid);
				if (clients.addClient(client)) {
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
	 * 查询全部客户资料
	 */
	public void findByClients() {
		Clients clients = new Clients();
		Clients client = getModel(Clients.class);
		Integer index = getParaToInt(0);
		Integer pageSize = getParaToInt(1);
		if (index == null || pageSize == null) {
			index = 1;
			pageSize = 10;
		}
		try {
			Page<Record> size = clients.findByClients(index, pageSize);
			if (size != null) {
				renderJson("array", size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据客户id查询该客户的资料
	 */
	public void findByClientsId() {
		Clients clients = new Clients();
		Clients client = getModel(Clients.class);
		// 获取参数
		Integer clientId = client.getInt("clients_id");
		System.out.println("clientId:" + clientId);
		try {
			if (clientId != null) {
				// 调用方法
				List<Clients> list = clients.findClientInfoById(clientId);
				if (list.size() > 0) {
					renderJson("array", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据id删除客户资料
	 */
	public void deleteByClientsId() {
		Clients clients = new Clients();
		Clients client = getModel(Clients.class);
		// 获取参数
		Integer clientId = client.getInt("clients_id");
		System.out.println("clientId:" + clientId);
		try {
			if(clientId != null){
				Integer size = 0;
				size = clients.deleteClientById(clientId);
				if(size > 0){
					renderJson("msg", "删除成功！");
				}else{
					renderJson("msg", "删除失败！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
