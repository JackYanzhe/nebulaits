package com.nebula.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nebula.api.common.annotation.MysqlRepository;
import com.nebula.api.entity.Users;
/**
 * 
 * @author zheyan.yan
 *
 */
@MysqlRepository
public interface UserMapper {
    Users findByUsername(@Param("username") String username);
    Users findUserById(@Param("id") String Id);
    List<Users> findAllUsersInfo();
}
