package com.demo.blog;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class Activities extends Model<Activities> {
	public static final Activities a = new Activities();

	/**
	 * 创建集赞活动
	 */
	public boolean addActiv(Activities ac) {
		try {
			Record re = new Record();
			re.set("activtitle", ac.getStr("activtitle"))
					.set("smallpic", ac.getStr("smallpic"))
					.set("activlogo", ac.getStr("activlogo"))
					.set("praisenum", ac.getInt("praisenum"))
					.set("activbody", ac.get("activbody"))
					.set("name", ac.getStr("name"))
					.set("address", ac.getStr("address"))
					.set("begintime", ac.getTimestamp("begintime"))
					.set("endtime", ac.getStr("endtime"))
					.set("status", ac.getInt("status"))
					.set("desc", ac.getStr("desc"))
					.set("activtype", ac.getInt("activtype"))
					.set("createtime", ac.getTimestamp("createtime"));
			Db.save("activities", re);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据id查询单个活动的详细信息
	 */

	public List<Activities> findByActivId(Integer activId) {
		try {
			if (activId != null) {
				return super
						.find("select * from activities a where a.activid=?",
								activId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑活动信息
	 */
	public Integer editActivInfo(Integer id, String title, String smallPic,
			String name, String address, Timestamp endTime, String body) {
		try {
			if (id != null) {
				return Db
						.update("update activities a set a.activtitle=?,a.smallpic=?,a.name=?,a.address=?,a.endtime=?,a.activbody=? where a.activid=?",
								title, smallPic, name, address, endTime, body,
								id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除活动
	 */
	public Integer deleteActiv(Integer id) {
		try {
			if (id != null) {
				return Db
						.update("delete from activities where activities.activid=?",
								id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据活动类型查询该活动
	 */
	public Page<Record> findActivByType(Integer index, Integer size,
			Integer type) {
		try {
			Page<Record> page = Db.paginate(index, size, "select *",
					" from activities a where a.activtype=?", type);
			System.out.println("page:" + page);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** -------------------------获取微信的openid test------------------------------ */

}
