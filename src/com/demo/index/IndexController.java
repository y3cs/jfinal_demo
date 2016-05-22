package com.demo.index;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
	
	/*public void index() {
		setAttr("base", getHttpPath(getRequest()));
		// 要返回的页面(百度编辑器所在页面)
		render("/WEB-INF/view/index.html");
	}
	
	private String getHttpPath(HttpServletRequest request){
		StringBuilder path = new StringBuilder();
		
		path.append(request.getScheme() + "://");
		path.append(request.getServerName() + ":");
		path.append(request.getServerPort());
		path.append(request.getContextPath());
		
		return path.toString();
	}*/
}





