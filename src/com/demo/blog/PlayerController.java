package com.demo.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;

/**
 * 微助力Controller
 * 
 * @author LH
 * 
 */
public class PlayerController extends Controller {

	/**
	 * 创建选手列表
	 */
	public void savePlayer() {
		ActivitiesController ac = new ActivitiesController();
		String openid = ac.map.get("openid");
		System.out.println("play_openid:" + openid);
		try {
			Player player = new Player();
			Player players = getModel(Player.class);
			if (players != null) {
				players.set("openid", openid);
				if (player.addPlayer(players)) {
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
	 * 根据id查询单个选手信息
	 */
	public void findPlayerById() {
		try {
			Player player = new Player();
			Player players = getModel(Player.class);
			Integer id = players.getInt("playid");
			if (id != null) {
				// 查询
				List<Player> list = player.findPlayerById(id);
				if (list.size() > 0) {
					renderJson("array", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑选手信息
	 */
	public void editPlayerInfo() {
		try {
			Player player = new Player();
			Player players = getModel(Player.class);
			Integer id = players.getInt("playid");
			String playname = players.getStr("playname");
			String playtel = players.getStr("playtel");
			String weddingtime = players.getStr("weddingtime");
			if (id != null) {
				// 修改操作
				Integer size = 0;
				size = player
						.editPlayerList(id, playname, playtel, weddingtime);
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
	 * 删除选手
	 */
	public void deletePlayer() {
		try {
			Player player = new Player();
			Player players = getModel(Player.class);
			Integer id = players.getInt("palyid");
			if (id != null) {
				// 删除操作
				Integer size = 0;
				size = player.deletePlayerById(id);
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

	/**
	 * 根据活动id查询该活动的所有选手信息
	 */
	public void findPlayerByActivId() {
		try {
			Player player = new Player();
			Player players = getModel(Player.class);
			Integer activid = players.getInt("activid");
			if (activid != null) {
				List<Player> list = player.findPlayerByActivId(activid);
				if (list.size() > 0) {
					renderJson("array", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有助力活动信息(暂时不用)
	 */
	public void findPlaryer() {
		try {
			Player player = new Player();
			Player players = getModel(Player.class);
			List<Player> list = player.findPlayer();
			if (list.size() > 0) {
				renderJson("array", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
