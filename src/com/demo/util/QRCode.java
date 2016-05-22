package com.demo.util;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.demo.util.MatrixToImageWriter;
import com.demo.util.Tools;



/**
 * 
 * @author yl
 *	
 */
public class QRCode {
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		String text = "http://www.oschina.net/"; 
        int width = 1200; 
        int height = 1200; 
        //二维码的图片格式 
        String format = "jpg"; 
        Hashtable hints = new Hashtable(); 
        //内容所使用编码 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        //L水平   7%的字码可被修正  
        //M水平   15%的字码可被修正  
        //Q水平   25%的字码可被修正  
        //H水平   30%的字码可被修正
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//可以再这儿自己调节容错率
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, 
                BarcodeFormat.QR_CODE, width, height, hints); 
        //生成二维码 
        File outputFile = new File("d:"+File.separator+"test.jpg"); 
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
	}
	/**
	 * 
	 * @param path保存二维码图片的路径
	 * @param url 要生成二维码的链接
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void createqrcode(String path,String url) throws Exception{
		Tools tools = new Tools();
		//path = "C:/apache-tomcat-8.0.26/webapps/qrcode/";
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();//如果路径不存在则创建
		}
		path = path + tools.getdatenum()+".jpg";
		int width = 300; 
        int height = 300; 
        //二维码的图片格式 
        String format = "jpg"; 
        Hashtable hints = new Hashtable(); 
        //内容所使用编码 
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints); 
        //生成二维码 
        
        File outputFile = new File(path); 
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
	}
	
}
