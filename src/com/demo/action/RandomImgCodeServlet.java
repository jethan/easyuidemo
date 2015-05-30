package com.demo.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.util.ImageCode;

/**
 * 验证码生成.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-20 上午10:21:07
 */
public class RandomImgCodeServlet extends HttpServlet {

	public RandomImgCodeServlet() {
		super();
	}
	
	@Override
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)    
    	throws ServletException, IOException {  
		//处理get请求，生成随机数并以JPEG图像输出
		response.setContentType("image/jpeg");    
		ImageCode image = new ImageCode();    
		response.setHeader("Pragma", "No-cache");    
		response.setHeader("Cache-Control", "no-cache");    
		response.setDateHeader("Expires", 0);    
		try {    
			// 输出图象到页面    
		    BufferedImage img = image.creatImage();    
		    //将随机数保存到当前请求用户的Session中，刷新一次随机数都会更新
		    request.getSession().setAttribute("randCode", image.getSRand()); 
		    ImageIO.write(img, "JPEG", response.getOutputStream());    
		    response.getOutputStream().flush();    
		    response.getOutputStream().close();    
		} catch (Exception e) {    
		    System.out.println("错误:" + e);    
		}    
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	@Override
	public void init() throws ServletException {
		
	}
}