package com.bibvip.springtest.repositories;

import com.bibvip.springtest.model.User;

import java.util.List;

public interface UserRepository {

    User export(Long id);

    List<User> findAll();

    User findById(Long id);

    void insert(User user);

    void update(User user);

    User delById(Long id);

}
