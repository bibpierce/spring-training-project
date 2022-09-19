package com.bibvip.springtest.repositories.impl;

import com.bibvip.springtest.model.User;
import com.bibvip.springtest.repositories.mybatis.UserMapper;
import com.bibvip.springtest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMyBatisImpl implements UserRepository {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User export(Long id){
        return userMapper.singleExport(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }


    @Override
    public void insert(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User delById(Long id) {
        User user = findById(id);
        userMapper.delById(id);
        return user;
    }
}
