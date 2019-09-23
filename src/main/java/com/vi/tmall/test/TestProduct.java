package com.vi.tmall.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vi.tmall.pojo.Product;
import com.vi.tmall.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestProduct {
	@Autowired
	ProductService productService;
	
	@Test
	public void test1() {
//		Product product = productService.get(8);
//		System.out.println("category=>"+product.getCategory().getName());
	}
}
