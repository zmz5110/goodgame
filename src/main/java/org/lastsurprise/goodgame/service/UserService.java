package org.lastsurprise.goodgame.service;

import org.lastsurprise.goodgame.model.User;

public interface UserService {

    User findUserById(long id);

    User findUserByUserName(String userName);

    void InsertUser(User user);
}
