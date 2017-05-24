package com.my.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.my.dao.UsersDao;
import com.my.dao.UsersDaoImpl;
import com.my.entity.Users;
import com.opensymphony.xwork2.ModelDriven;


//ģ�������ķ�ʽ���ձ�����
public class UsersAction extends SuperAction implements ModelDriven<Users>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -376438698988144690L;
	private Users user = new Users();
	//ִ���û���½�ķ���
	public String login() {
		UsersDao usersDao = new UsersDaoImpl();
		
		if(usersDao.usersLogin(user)){
			this.httpSession.setAttribute("loginUserName", user.getUsername());
			
			return "login_success";
		}else {
			this.addFieldError("loginError", "�û������������");
			return "login_failure";
		}
	}
	
	
	//�÷���Ϊ��������������
	//ע��ʹ���˴˷�������Ҫ��struts.xml�ļ�����Ӧaction������<result name="input">xxxx</result>
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		//trim����Ϊȥ���ַ���ǰ��Ŀո�
		if("".equals(user.getUsername().trim())) {
			this.addFieldError("userNameError", "�û�������Ϊ��");
		}
		if(user.getPassword().trim().length() < 4) {
			this.addFieldError("passwordError", "���볤�Ȳ���С��4λ");
		}		
	}


	//��ע���ʾִ�и÷���������validate�������в������
	@SkipValidation
	//ִ���û��˳��ķ���
	public String logout() {
		this.httpSession.removeAttribute("loginUserName");
		return "logout_success";
	}
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
