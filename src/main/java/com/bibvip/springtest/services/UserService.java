package com.bibvip.springtest.services;

import com.bibvip.springtest.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User singleExport(Long id);

    User findById(Long id) throws Exception;

    void update(User user);

    User delById(Long id) throws Exception;

    void insert(User user);
}
