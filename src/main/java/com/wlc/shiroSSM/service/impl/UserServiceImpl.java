package com.wlc.shiroSSM.service.impl;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.wlc.shiroSSM.mapper.UserMapper;
import com.wlc.shiroSSM.pojo.User;
import com.wlc.shiroSSM.pojo.UserExample;
import com.wlc.shiroSSM.service.UserRoleService;
import com.wlc.shiroSSM.service.UserService;
 
@Service
public class UserServiceImpl  implements UserService{
 
    @Autowired UserMapper userMapper;
    @Autowired UserRoleService userRoleService;
     
    @Override
    public String getPassword(String name) {
        User user = getByName(name);
        if(null==user)
            return null;
        return user.getPassword();
    }
 
    @Override
    public User getByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty())
            return null;
        return users.get(0);
    }
     
    @Override
    public void add(User u) {
        userMapper.insert(u);
    }
  
    @Override
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
        userRoleService.deleteByUser(id);
    }
  
    @Override
    public void update(User u) {
        userMapper.updateByPrimaryKeySelective(u);
    }
  
    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
  
    @Override
    public List<User> list(){
        UserExample example =new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
  
    }
 
}