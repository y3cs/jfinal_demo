package com.demo.blog;


import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Clients  客户资料<br/>
 * @author yl
 *
 * clients_id  		客户id<br/>
 * clients_source   来源(来自哪个活动)<br/>
 * openid  			openid<br/>
 * clients_name  	真实姓名<br/>
 * tel    			联系电话 <br/>
 * wedding_day		婚期<br/>
 * get_gift  		抽中礼品<br/>
 * get_gift_time    获奖时间<br/>
 * type  			类型：0微助力客户资料  1微投票客户资料<br/>
 * activid 			 活动id<br/>
 */
@SuppressWarnings("serial")
public class Clients extends Model<Clients>{
	public static final Clients cl = new Clients();
	/**
	 * 添加客户资料
	 */
	public boolean addClient(Clients clients){
		try {
			Record re = new Record();
			re.set("clients_id", clients.getInt("clients_id"))
			  .set("clients_source", clients.getStr("clients_source"))
			  .set("openid", clients.getStr("openid"))
			  .set("clients_name", clients.getStr("clients_name"))
			  .set("tel", clients.getStr("tel"))
			  .set("wedding_day", clients.getStr("wedding_day"))
			  .set("get_gift", clients.getStr("get_gift"))
			  .set("get_gift_time", clients.getStr("get_gift_time"))
			  .set("type", clients.getInt("type"))
			  .set("activid", clients.getInt("activid"));
			Db.save("clients", re);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 查询全部客户资料(分页)
	 */
	public Page<Record> findByClients(Integer index , Integer size ){
		try {
			Page<Record> page = Db.paginate(index, size, "select *", " from clients where 1=?", 1);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据客户id查询该客户的信息
	 */
	public List<Clients> findClientInfoById(Integer clientId){
		try {
			return this.cl.find("select * from clients c where 1=1 and c.clients_id=?", clientId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 编辑客户资料信息
	 */
	/*public Integer editClientsById(Integer clientId){
		try {
			return Db.update("update", paras)
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}*/
	public Integer deleteClientById(Integer clientId){
		try {
			return Db.update("delete from clients  where clients.clients_id=?", clientId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
