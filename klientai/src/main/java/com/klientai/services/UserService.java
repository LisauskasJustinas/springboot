package com.klientai.services;

import com.klientai.model.User;



public interface UserService {
    void save (User user);
    User findByUsername(String username);
}
