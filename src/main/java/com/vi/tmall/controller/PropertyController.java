package com.vi.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Property;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.PropertyService;
/**
 * 属性相关的控制处理
 * @author vi
 *
 */
@Controller
@RequestMapping("")
public class PropertyController {
	@Autowired
	PropertyService propertyService;
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("admin_property_list")
	public String list(int id,Model model,@RequestParam(value="page",defaultValue="1") int page) {
		PageHelper.startPage(page, 5);
		List<Property> props = propertyService.list(id);
		PageInfo<Property> pageInfo = new PageInfo<>(props,5);
		Category category = categoryService.get(id);
//		System.out.println(props);
		model.addAttribute("props", props);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("category",category);
		return "admin/listProperty";
	}
	
	@RequestMapping("admin_property_add")
	public String add(Property property,int cid) {
		propertyService.add(property);
		return "redirect:admin_property_list?id="+cid;
	}
	
	@RequestMapping("admin_property_delete")
	public String delete(int id) {
		Property property = propertyService.get(id);
		propertyService.delete(id);
		return "redirect:admin_property_list?id="+property.getCid();
	}
	
	@RequestMapping("admin_property_edit")
	public String edit(int id,Model model) {
		//根据页面传过来的id得到property对象
		Property property = propertyService.get(id);
		Category category = categoryService.get(property.getCid());
		//把property通过request传到下个页面
		model.addAttribute("property",property);
		model.addAttribute("category",category);
		return "admin/editProperty";
	}
	
	@RequestMapping("admin_property_update")
	public String update(Property property) {
		propertyService.update(property);
		return "redirect:admin_property_list?id="+property.getCid();
	}
}
