package com.nebula.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.api.dao.UserMapper;
import com.nebula.api.entity.Users;

/**
 * 
 * @author zheyan.yan
 *
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public Users findByUsername(Users user){
        return userMapper.findByUsername(user.getUsername());
    }
    public Users findUserById(String userId) {
        return userMapper.findUserById(userId);
    }
    
    public List<Users> findUsersInfos(){
    	return userMapper.findAllUsersInfo();
    }

}
