package com.demo.common;

import com.demo.blog.Activities;
import com.demo.blog.ActivitiesController;
import com.demo.blog.Clients;
import com.demo.blog.ClientsController;
import com.demo.blog.Menu;
import com.demo.blog.MenuController;
import com.demo.blog.Module;
import com.demo.blog.ModuleController;
import com.demo.blog.RoleMenu;
import com.demo.blog.RoleMenuController;
import com.demo.blog.Player;
import com.demo.blog.PlayerController;
import com.demo.blog.RoleUser;
import com.demo.blog.RoleUserController;
import com.demo.blog.TwoDimensionCodeController;
import com.demo.blog.User;
import com.demo.blog.UserController;
import com.demo.index.IndexController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("a_little_config.txt");
		// me.setDevMode(PropKit.getBoolean("devMode", false));
		
		
		// ApiConfigkit设为开发模式可以在开发阶段输出请求交互的xml 与 json 数据
		//ApiConfigKit.setDevMode(me.getDevMode());
		me.setDevMode(true);
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/index"); // 第三个参数为该Controller的视图存放路径
		me.add("/user", UserController.class);
		me.add("/activities", ActivitiesController.class);
		me.add("/module", ModuleController.class);
		me.add("/player", PlayerController.class);
		me.add("/menu", MenuController.class);
		me.add("/rolemenu", RoleMenuController.class);
		me.add("/roleuser", RoleUserController.class);
		me.add("/qr", TwoDimensionCodeController.class);
		me.add("/cl", ClientsController.class);
		
		// 微信路由配置
		
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		// C3p0Plugin c3p0Plugin1=new
		// C3p0Plugin(getProperty("jdbcUrl"),getProperty("user"),getProperty("password"));
		C3p0Plugin dsMysql = new C3p0Plugin(PropKit.get("jdbcUrl"),
				PropKit.get("user"), PropKit.get("password").trim());
		me.add(dsMysql);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arpMysql = new ActiveRecordPlugin("mysql", dsMysql);
		me.add(arpMysql);
		arpMysql.setCache(new EhCache());
		arpMysql.addMapping("user", "userid", User.class);
		arpMysql.addMapping("activities", "activid", Activities.class);
		arpMysql.addMapping("module","moduleid", Module.class);
		arpMysql.addMapping("player","playid", Player.class);
		arpMysql.addMapping("menu", "menu_id", Menu.class);
		arpMysql.addMapping("rolemenu", "role_menu_id", RoleMenu.class);
		arpMysql.addMapping("roleuser", "role_user_id",RoleUser.class);
		arpMysql.addMapping("clients", "clients_id", Clients.class);
		arpMysql.setShowSql(true);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

	}

	/**
	 * 启动完毕所做的处理
	 */

	// /**
	// * 建议使用 JFinal 手册推荐的方式启动项目
	// * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	// */
	// public static void main(String[] args) {
	// JFinal.start("WebRoot", 80, "/", 5);
	// }
}
