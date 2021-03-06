package com.itheima.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户的模块的控制器
 * @author Administrator
 */
public class UserAction extends ActionSupport{

	private static final long serialVersionUID = 1305643617977647333L;
	
	/**
	 * 处理登录功能
	 * @return
	 */
	public String login(){
		// 这边没有学习功能，封装数据，现在还需要使用request对象
		// 怎么获取request方式
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取请求参数
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			// 调用业务层
			User existUser = new UserService().login(user);
			// 判断
			if(existUser == null){
				// 说明，用户名或者密码错误了
				System.out.println("用户名或者密码错误了...");
				return LOGIN;
			}else{
				// 存入到session中
				request.getSession().setAttribute("existUser", existUser);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

}








