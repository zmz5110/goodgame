package org.lastsurprise.goodgame.service.Impl;

import org.lastsurprise.goodgame.mapper.UserMapper;
import org.lastsurprise.goodgame.model.User;
import org.lastsurprise.goodgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(long id) {
        return null;
    }
}
