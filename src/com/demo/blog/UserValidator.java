package com.demo.blog;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		/*validateRequiredString("blog.title", "titleMsg", "请输入Blog标题!");
		validateRequiredString("blog.content", "contentMsg", "请输入Blog内容!");*/
		
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(User.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/user/saveUser"))
			c.render("login.html");
		else if (actionKey.equals("/user/login"))
			c.render("login.html");
	}
	
}
