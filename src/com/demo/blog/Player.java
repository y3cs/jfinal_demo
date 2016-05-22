package com.demo.blog;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

/**
 * 微助力
 * 
 * @author LH
 * 
 */
@SuppressWarnings("serial")
public class Player extends Model<Player> {
	public static final Player p = new Player();

	/**
	 * 创建选手列表
	 */
	public boolean addPlayer(Player play) {
		try {
			if (play != null) {
				Record plays = new Record();
				plays.set("playname", play.getStr("playname"))
						.set("playtel", play.getStr("playtel"))
						.set("weddingtime", play.getStr("weddingtime"))
						.set("helpnum", play.getInt("helpnum"))
						.set("gift", play.getStr("gift"))
						.set("createtime", play.getTimestamp("createtime"))
						.set("openid", play.getStr("openid"))
						.set("forwardnum", play.getInt("forwardnum"))
						.set("topnum", play.getInt("topnum"))
						.set("source", play.getStr("source"));
				Db.save("player", plays);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据id查询单个选手信息
	 */
	public List<Player> findPlayerById(Integer id) {
		try {
			if (id != null) {
				return super
						.find("select * from player p where p.playid=?", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑选手信息
	 */
	public Integer editPlayerList(Integer id, String playname, String playtel,
			String weddingtime) {
		try {
			if (id != null) {
				return Db.update(
						"update player p set p.playname=?,p.playtel=?,p.weddingtime=? where p.playid=?",
						playname, playtel, weddingtime, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除选手列表
	 */
	public Integer deletePlayerById(Integer id) {
		try {
			if (id != null) {
				return Db.update("delete from player p where p.playid=?", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据活动id查询该活动的所有选手信息
	 */
	public List<Player> findPlayerByActivId(Integer activId){
		try {
			if(activId != null){
				return super.find("select * from player p where p.activid=?", activId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询所有选手列表信息(暂时不用)
	 */
	public List<Player> findPlayer(){
		try {
			return super.find("select * from player p where 1=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
