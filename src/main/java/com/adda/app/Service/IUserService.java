package com.adda.app.Service;

import java.util.Set;

import com.adda.app.entity.User;
import com.adda.app.entity.UserRole;

public interface IUserService {

	public User CreateUser(User user,Set<UserRole>userRoles) throws Exception;
    public User getUser(String username);
    public void deleteuser(Long uid);
}
