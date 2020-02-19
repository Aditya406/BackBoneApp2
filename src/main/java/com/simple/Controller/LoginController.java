package com.simple.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.Account;
import com.simple.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService service;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String details(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		return service.getUser(username, password);
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody String saveUser(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		return service.saveuser(username, password, email);
	}

	@RequestMapping(value = "/getalldata")
	public @ResponseBody JSONArray userdata(HttpServletRequest request, HttpServletResponse response) {
		return service.getall();
	}

	@RequestMapping(value = "/getuserdata",method = RequestMethod.POST)
	public @ResponseBody String getdata(@RequestParam("a_id") String a_id, HttpServletRequest request,
			HttpServletResponse response) {
		/* System.out.println("Aditya 146"); */
		return service.getuser(a_id);
	}
	
	@RequestMapping(value = "/saveupdate",method = RequestMethod.POST)
	public @ResponseBody String updatedata(@RequestParam("a_id")Integer a_id,@RequestParam("username") String username,@RequestParam("email") String email, HttpServletRequest request,HttpServletResponse response) {
		System.out.println("Aditya 146789");
		return service.updateuser(a_id,username,email);
	}

}