package com.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.ActionInvocation;

/**
 * 拦截器 （做权限）
 * @author LH
 *
 */
public class UserInterceptor implements Interceptor{
	public void intercept(Invocation inv) {
		
		// 切面代码
		/*if(!exc){
			
		}*/
		System.out.println("Befor method invoking");
		// 传递本次调用,调用剩下的拦截器与目标方法(对目标方法的调用)
		inv.invoke(); 
		System.out.println("After method invoking");
		// 切面代码
	}

	@Override
	public void intercept(ActionInvocation arg0) {
		// TODO Auto-generated method stub
		
	}
}
