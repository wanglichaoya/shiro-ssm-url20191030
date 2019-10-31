package com.wlc.shiroSSM.controller;

import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.pojo.User;
import com.wlc.shiroSSM.service.RoleService;
import com.wlc.shiroSSM.service.UserRoleService;
import com.wlc.shiroSSM.service.UserService;
import com.wlc.shiroSSM.util.EncodeSaltPasswordUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/10/31
 */
@Controller
@RequestMapping("config")
public class UserController {

    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @RequestMapping("listUser")
    public String list(Model model){
        List<User> us= userService.list();
        model.addAttribute("us", us);
        Map<User,List<Role>> user_roles = new HashMap<>();
        for (User user : us) {
            List<Role> roles=roleService.listRoles(user);
            user_roles.put(user, roles);
        }
        model.addAttribute("user_roles", user_roles);

        return "listUser";
    }
    @RequestMapping("editUser")
    public String edit(Model model,long id){
        List<Role> rs = roleService.list();
        model.addAttribute("rs", rs);
        User user =userService.get(id);
        model.addAttribute("user", user);

        List<Role> roles =roleService.listRoles(user);
        model.addAttribute("currentRoles", roles);

        return "editUser";
    }
    @RequestMapping("deleteUser")
    public String delete(Model model,long id){
        userService.delete(id);
        return "redirect:listUser";
    }
    @RequestMapping("updateUser")
    public String update(User user,long[] roleIds){
        userRoleService.setRoles(user,roleIds);

        String password=user.getPassword();
        //如果在修改的时候没有设置密码，就表示不改动密码
        if(user.getPassword().length()!=0) {
            String encodedPassword = EncodeSaltPasswordUtil.encodedPassword(2,"md5",password);
            String salt = EncodeSaltPasswordUtil.getSalt();
            user.setSalt(salt);
            user.setPassword(encodedPassword);
        }
        else
            user.setPassword(null);

        userService.update(user);

        return "redirect:listUser";

    }

    @RequestMapping("addUser")
    public String add(Model model,String name, String password){
        String encodedPassword = EncodeSaltPasswordUtil.encodedPassword(2,"md5",password);
        String salt = EncodeSaltPasswordUtil.getSalt();
        User u = new User();
        u.setName(name);
        u.setPassword(encodedPassword);
        u.setSalt(salt);
        userService.add(u);

        return "redirect:listUser";
    }

}

