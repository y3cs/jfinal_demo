package com.demo.blog;

import com.demo.util.QRCode;
import com.jfinal.core.Controller;
/**
 * TwoDimensionCodeController
 * 
 * @author yl
 *
 */
public class TwoDimensionCodeController extends Controller{
	
	/**
	 * 测试
	 * @throws Exception 
	 */
	public void QCode() throws Exception{
		String url = getPara("url");
		System.out.println("url:" + url);
		String path = getPara("path");
		System.out.println("path:" + path);
		QRCode qr = new QRCode();
		try {
			if(path != null && url != null){
				qr.createqrcode(path, url);
				renderJson("msg", "哦啦");
			}else{
				renderJson("msg", "哦也，失败了！");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
}
