package com.simple.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.Account;
import com.simple.dao.LoginDao;


@Service
public class LoginService {
	
	@Autowired
	private LoginDao dao;

public String getUser(String username, String password) {
	return dao.getUser(username, password);
}

public String saveuser(String username, String email,String password) {
	return dao.saveUser(username,email,password);
}

public JSONArray getall() {
	return dao.getall();
	
}

public String getuser(String a_id) {

	return dao.getuser(a_id);
}

public String updateuser(Integer a_id,String username,String email) {
	return dao.updateuser(a_id,username,email);
}

}