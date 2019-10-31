package com.wlc.shiroSSM.controller;

import com.wlc.shiroSSM.pojo.Permission;
import com.wlc.shiroSSM.pojo.Role;
import com.wlc.shiroSSM.service.PermissionService;
import com.wlc.shiroSSM.service.RolePermissionService;
import com.wlc.shiroSSM.service.RoleService;
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
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    PermissionService permissionService;

    /**查询角色**/
    @RequestMapping("listRole")
    public String list(Model model) {
        List<Role> rs = roleService.list();
        model.addAttribute("rs", rs);

        Map<Role, List<Permission>> role_permissions = new HashMap<>();

        for (Role role : rs) {
            //查询厨具角色所对应的权限
            List<Permission> ps = permissionService.list(role);
            role_permissions.put(role, ps);
        }
        model.addAttribute("role_permissions", role_permissions);

        return "listRole";
    }

    /**修改角色**/
    @RequestMapping("editRole")
    public String list(Model model, long id) {
        Role role = roleService.get(id);
        model.addAttribute("role", role);

        List<Permission> ps = permissionService.list();
        model.addAttribute("ps", ps);

        List<Permission> currentPermissions = permissionService.list(role);
        model.addAttribute("currentPermissions", currentPermissions);

        return "editRole";
    }

    @RequestMapping("updateRole")
    public String update(Role role, long[] permissionIds) {
        rolePermissionService.setPermissions(role, permissionIds);
        roleService.update(role);
        return "redirect:listRole";
    }

    @RequestMapping("addRole")
    public String list(Model model, Role role) {
        System.out.println(role.getName());
        System.out.println(role.getDesc_());
        roleService.add(role);
        return "redirect:listRole";
    }

    @RequestMapping("deleteRole")
    public String delete(Model model, long id) {
        int result = roleService.delete(id);
        //根据角色id，删除用户角色表中的角色，
        int resultUserRole = roleService.deleteUserRoleByid(id);
        int resultRolePermit = roleService.deleteRolePermitById(id);

        return "redirect:listRole";
    }

}
