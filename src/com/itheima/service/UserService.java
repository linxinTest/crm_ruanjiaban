package com.itheima.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;

/**
 * 用户的业务层
 * @author Administrator
 */
public class UserService {
	
	/**
	 * 处理登录的功能
	 * @param user
	 * @return
	 */
	public User login(User user){
		// 使用事务
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		User existUser = null;
		try {
			// 调用持久层，查询数据
			existUser = new UserDao().findByNameAndPwd(user);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}
		return existUser;
	}
	
	@Test
	public void run(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		User existUser = this.login(user);
		if(existUser != null){
			System.out.println("登录成功了...");
		}else {
			System.out.println("登录失败了...");
		}
	}

}












