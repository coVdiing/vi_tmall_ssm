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
import com.vi.tmall.pojo.Product;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductService;

@Controller
public class ProductController {
	@Autowired 
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	/**
	 * 产品数据展示
	 * @param page 当前页码，默认值第一页
	 * @param cid ��ǰ���ʵ�Category id
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_product_list")
	public String list(@RequestParam(value="page",defaultValue="1") int page,int cid,Model model){
		//对查询进行分页
		PageHelper.startPage(page,5);
		List<Product> productList = productService.list(cid);
		//导航页码一共5页
		PageInfo<Product> pageInfo = new PageInfo<>(productList,5);
		Category category = categoryService.get(cid);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("productList", productList);
		model.addAttribute("category",category);
		return "admin/listProduct";
	}
	
	@RequestMapping("admin_product_delete")
	public String delete(int id,int cid) {
		productService.delete(id);
		return "redirect:admin_product_list?cid="+cid;
	}
	
	@RequestMapping("admin_product_add") 
	public String add(Product product) {
		productService.add(product);
		return "redirect:admin_product_list?cid="+product.getCid();
	}
	
	@RequestMapping("admin_product_edit")
	public String edit(int id,Model model) {
		Product product = productService.get(id);
		Category category = categoryService.get(product.getCid());
		model.addAttribute("product", product);
		model.addAttribute("category", category);
		return "admin/editProduct";
	}
	
	@RequestMapping("admin_product_update")
	public String update(Product product) {
		productService.update(product);
		return "redirect:admin_product_list?cid="+product.getCid();
	}
}
