package com.vi.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.pojo.User;
import com.vi.tmall.service.UserService;

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * �û������Ǻ������ݣ�ֻ�ṩ��ѯ������ע����޸��û�������ǰ̨
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_user_list")
	public String list(Model model,@RequestParam(value="page",defaultValue="1") int page) {
		//��ҳ
		PageHelper.startPage(page,5);
		List<User> list = userService.list();
		PageInfo<User> pageInfo = new PageInfo<>(list,5);
		model.addAttribute("list",list);
		model.addAttribute("pageInfo",pageInfo);
		return "admin/listUser";
	}
}
