package com.adda.app.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adda.app.Service.IUserService;
import com.adda.app.ServiceImpl.UserDetailsServiceImpl;
import com.adda.app.config.jwtUtil;
import com.adda.app.entity.User;
import com.adda.app.helper.UserFoundException;
import com.adda.app.helper.UserNotFoundException;
import com.adda.app.payload.userRequest;
import com.adda.app.payload.userResponse;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
	private UserDetailsServiceImpl udsi;
    @Autowired
    private jwtUtil jwtutils;
    @Autowired
    private IUserService uservice;
    
    
    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody userRequest userrequest) throws Exception
    {
      try 
      {
    	 
    	  this.authenticate(userrequest.getUsername(), userrequest.getPassword());
      }	
      catch(UsernameNotFoundException e) 
      {
    	  e.printStackTrace();
    	  throw new UserNotFoundException("user not found");
      }
	 
      
      //authenticate
      UserDetails userdetails=  this.udsi.loadUserByUsername(userrequest.getUsername());
      String token = this.jwtutils.generateToken(userdetails);
      return ResponseEntity.ok(new userResponse(token));
     
    }
    
    
    
    
    
    private void authenticate(String username,String password) throws Exception 
    {
    	try 
    	{
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));	
    	}
    	catch(DisabledException e) 
    	{
    		throw new Exception("USER DISABLE "+e.getMessage());
    	}
    	catch(BadCredentialsException e) 
    	{
    		throw new Exception("Invalid Credentials "+e.getMessage() );
    	}
    }
   @GetMapping("/current-user")
    public User getCurrentUser(Principal p) 
    {
    	return this.uservice.getUser(p.getName());
      
		
    	
    }
}
