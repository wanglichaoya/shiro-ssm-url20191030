package com.wlc.shiroSSM.controller;

import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/10/31
 */
@Controller
@RequestMapping("config")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    /**
     * 查询权限
     **/
    @RequestMapping("listPermission")
    public String list(Model model) {
        List<Permission> ps = permissionService.list();
        model.addAttribute("ps", ps);
        return "listPermission";
    }

    /***
     * 根据权限id修改权限
     * **/
    @RequestMapping("editPermission")
    public String list(Model model, long id) {
        Permission permission = permissionService.get(id);
        model.addAttribute("permission", permission);
        return "editPermission";
    }

    /**
     * 更新权限
     **/
    @RequestMapping("updatePermission")
    public String update(Permission permission) {

        permissionService.update(permission);
        return "redirect:listPermission";
    }

    /**
     * 添加权限
     **/
    @RequestMapping("addPermission")
    public String list(Model model, Permission permission) {
        System.out.println(permission.getName());
        System.out.println(permission.getDesc_());
        permissionService.add(permission);
        return "redirect:listPermission";
    }

    /**
     * 删除权限
     **/
    @RequestMapping("deletePermission")
    public String delete(Model model, long id) {
        permissionService.delete(id);
        return "redirect:listPermission";
    }

}
