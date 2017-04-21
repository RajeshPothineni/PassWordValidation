package io.techUSA.Springboot.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.techUSA.Springboot.service.PasswordValidatorService;
import io.techUSA.Springboot.vo.UserInfo;

@RestControllerAdvice
@RequestMapping(value="/passwordvalidator")
public class PasswordValidationController {
	@Autowired
	PasswordValidatorService pwdValidatorService;
	
	public static int passwordcount ;
    @RequestMapping(value="/validate/credentials",method=RequestMethod.POST)
	public Set<String> validateUserNameAndPassword(@RequestBody UserInfo userInfo){
    	Set<String> errors;
        	
    	if(null!=userInfo.getPassword() && null != userInfo.getUserName()){
    		 errors=pwdValidatorService.validate(userInfo.getPassword());
    		     		 
    	}else{
    		 errors=new HashSet<String>();
    		 errors.add("Either Username or Password is null");
    	}
    	
		return errors;
		
	}
   
}
