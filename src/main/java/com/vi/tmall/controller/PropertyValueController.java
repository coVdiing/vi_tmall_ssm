package com.vi.tmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.PropertyValue;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.service.PropertyService;
import com.vi.tmall.service.PropertyValueService;

@Controller
public class PropertyValueController {
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	PropertyService propertySerivce;
	@Autowired
	ProductService productService;

	
	/**
	 * 跳转到属性编辑页
	 * @param pid ��Ʒid
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_propertyValue_edit")
	public String edit(int pid, Model model) {
		Product product = productService.get(pid);
		System.out.println("pro id:"+product.getId()+",cate name:"+product.getCategory().getName());
		//对属性值初始化
		propertyValueService.init(product);
		//根据产品id查询对应的属性值集合
		List<PropertyValue> list = propertyValueService.list(pid);
		model.addAttribute("product",product);
		model.addAttribute("list",list);
		return "admin/editPropertyValue";
	}
	
	@RequestMapping("admin_propertyValue_update")
	@ResponseBody
	public String update(PropertyValue propertyValue) {
		propertyValueService.update(propertyValue);
		return "success";
	}
}
