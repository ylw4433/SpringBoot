package com.example.demo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
public class ThymeleafController {
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/queryUserInfo")
	public String queryUserInfo(/*@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn,*/ModelMap model) {
//		PageHelper.startPage(pn,5);
//		List<User> users = userService.findUserInfo();
//		PageInfo<User> pageInfo = new PageInfo<User>(users,5);
		model.addAttribute("users", userService.findUserInfo());
		return "user";
	}
	
	@RequestMapping(value="/del")
	public String del(HttpServletRequest request,ModelMap model) {
    	String id = request.getParameter("id");
//    	int result = userService.del(id);
    	int result = userService.del();
    	model.addAttribute("result", result);
    	return "redirect:/queryUserInfo";
    }
	
	@RequestMapping(value="/findUserInfoById")
	public String findUserInfoById(HttpServletRequest request,ModelMap model) {
		String id = request.getParameter("id");
		model.addAttribute("userInfo", userService.findUserInfoById(id));
		return "update";
	}
	
	@RequestMapping(value="/updateUserInfoById")
	public String  updateUserInfoById(HttpServletRequest request,ModelMap model) throws ParseException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String userdesc = request.getParameter("userdesc");
		String telephone = request.getParameter("telephone");
		String date = request.getParameter("createtime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createtime = sdf.parse(date);
		User user = new User();
		user.setId(id);
		if(!"".equals(username)&&username!=null) {
			user.setUsername(username);
		}
		if(!"".equals(userdesc)&&userdesc!=null) {
			user.setUserdesc(userdesc);
		}
		if(!"".equals(telephone)&&telephone!=null) {
			user.setTelephone(telephone);
		}
		if(createtime!=null) {
			user.setCreatetime(createtime);
		}
		int result = userService.updateUserInfo(user);
		model.addAttribute("result", result);
		return "redirect:/queryUserInfo";
	}
	
	@RequestMapping(value="/add")
	public String add(ModelMap model) {
		return "add";
	}
	
	@RequestMapping(value="/addUser")
	public String addUser(HttpServletRequest request,ModelMap model) throws ParseException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userdesc = request.getParameter("userdesc");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
//		String date = request.getParameter("createtime");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date createtime = sdf.parse(date);
		User user = new User();
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setStatus("1");
		user.setCreatetime(new Date());
		if(!"".equals(username)&&username!=null) {
			user.setUsername(username);
		}
		if(!"".equals(password)&&password!=null) {
			user.setPassword(password);
		}
		if(!"".equals(userdesc)&&userdesc!=null) {
			user.setUserdesc(userdesc);
		}
		if(!"".equals(telephone)&&telephone!=null) {
			user.setTelephone(telephone);
		}
		if(!"".equals(email)&&email!=null) {
			user.setEmail(email);
		}
		int result = userService.addUser(user);
		model.addAttribute("result", result);
		return "redirect:/queryUserInfo";
	}

}
