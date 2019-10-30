package com.wlc.shiroSSM.controller;


import com.wlc.shiroSSM.mapper.PermissionMapper;
import com.wlc.shiroSSM.mapper.RoleMapper;
import com.wlc.shiroSSM.mapper.UserMapper;
import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("")
public class LoginController {

    private User user;
	@RequestMapping(value="/login",method=RequestMethod.POST) 
	public String login(Model model,String name, String password,String rememberme) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
       /* boolean flag ;
        flag = rememberme == null ? false : true;   //null=>false
        token.setRememberMe(flag);*/
        try {  
            subject.login(token);
            Session session=subject.getSession();
            session.setAttribute("subject", subject);
            return "redirect:index";
            
        } catch (AuthenticationException e) {  
            model.addAttribute("error", "验证失败");  
            return "login"; 
        }  
	}

	@RequestMapping("/register")
	public String register(Model model,String name,String password){
	    //加密次数
	    int time = 2;
	    //盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodePassword = new SimpleHash("md5",password,salt,time).toString();
        user = new User();
        user.setName(name);
        user.setPassword(encodePassword);
        user.setSalt(salt);
        int result = userMapper.createUser(user);

        if(result>0){
            return "redirect:login";
        }else{
            return "redirect:registerPage";
        }

    }

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private PermissionMapper permissionMapper;
	@RequestMapping("/test")
	public void test(){
	    User user = userMapper.getByName("zhang3");
        System.out.println("user.name->" + user.getPassword());

        List<Role> roleList = roleMapper.listRolesByUserName("zhang3");
        System.out.println("roleList->" + roleList.get(0).getName());

        List<Permission> permissionList = permissionMapper.listPermissionsByUserName("zhang3");
        System.out.println("permissionList->" + permissionList.get(0).getName());
    }
	


}
