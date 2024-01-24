package com.adda.app.ServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adda.app.Reposatotries.IRoleReposatory;
import com.adda.app.Reposatotries.IUserReposatory;
import com.adda.app.Service.IUserService;
import com.adda.app.entity.User;
import com.adda.app.entity.UserRole;
import com.adda.app.helper.UserFoundException;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
	private IUserReposatory urepo;
    @Autowired
	private IRoleReposatory rolerepo;
    @Autowired
    private BCryptPasswordEncoder pencode;
	@Override
	public User CreateUser(User user, Set<UserRole> userRoles) throws Exception {
    
		User local=this.urepo.findByusername(user.getUsername());
		if(local!=null) 
		{
			System.out.println("User is Alredy exist");
			throw new UserFoundException("User with this name allready Exists");
			
		}
		else 
		{
			for(UserRole ur:userRoles) 
			{
				rolerepo.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
		String encpwd = pencode.encode(user.getPassword());
		user.setPassword(encpwd);
			local=this.urepo.save(user);
		}
		
		return local;
	}

	@Override
	public User getUser(String username) {
		return this.urepo.findByusername(username);
	}

	@Override
	public void deleteuser(Long uid) {
		// TODO Auto-generated method stub
				this.urepo.deleteById(uid);
	}

}
