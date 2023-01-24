package com.bibvip.springtest.repositories.impl;

import com.bibvip.springtest.model.User;
import com.bibvip.springtest.repositories.mybatis.UserMapper;
import com.bibvip.springtest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = in.readLine();
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
