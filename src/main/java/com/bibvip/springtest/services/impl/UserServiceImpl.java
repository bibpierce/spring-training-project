package com.bibvip.springtest.services.impl;


import com.bibvip.springtest.model.User;
import com.bibvip.springtest.repositories.UserRepository;
import com.bibvip.springtest.services.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User singleExport(Long id) {
        return userRepository.export(id);
    }

    @Override
    public User findById(Long id) throws Exception {
        return Optional.ofNullable(userRepository.findById(id)).orElseThrow(() ->
                new NotFoundException("Id not found: " + id));
    }


    @Override
    public void insert(User user){
            userRepository.insert(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public User delById(Long id) throws Exception {
            return Optional.ofNullable(userRepository.delById(id)).orElseThrow(() ->
                    new NotFoundException("Id not found: " + id));
    }



}
