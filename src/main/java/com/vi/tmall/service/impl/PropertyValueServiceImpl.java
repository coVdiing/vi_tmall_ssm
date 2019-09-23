package com.vi.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.PropertyValueMapper;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.Property;
import com.vi.tmall.pojo.PropertyExample;
import com.vi.tmall.pojo.PropertyValue;
import com.vi.tmall.pojo.PropertyValueExample;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.service.PropertyService;
import com.vi.tmall.service.PropertyValueService;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
	@Autowired
	PropertyValueMapper propertyValueMapper;
	@Autowired
	PropertyService propertyService;
	@Autowired
	ProductService productService;
	
	/**
	 * 初始化，将product对应的propertyValue在数据库中插入数据
	 */
	@Override
	public void init(Product product) {
		//通过product获取category id 再获取对应属性集合
		List<Property> list = propertyService.list(product.getCid());
		for(Property property : list) {
			PropertyValue propertyValue = get(product.getId(),property.getId());
			if(propertyValue == null) {
				propertyValue = new PropertyValue();
				propertyValue.setPid(product.getId());
				propertyValue.setPtid(property.getId());
				//插入数据库
				propertyValueMapper.insert(propertyValue);
			}
		}
	}
	
	/**
	 * 根据pid,ptid获取单个propertyValue对象
	 */
	@Override
	public PropertyValue get(int pid, int ptid) {
		PropertyValueExample example = new PropertyValueExample();
		example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
		List<PropertyValue> list = propertyValueMapper.selectByExample(example);
		if(list.isEmpty())
			return null;
		return list.get(0);
	}
	
	/**
	 * 返回将product对应的propertyValue
	 */
	@Override
	public List<PropertyValue> list(int pid) {
		PropertyValueExample example = new PropertyValueExample();
		example.createCriteria().andPidEqualTo(pid);
		List<PropertyValue> list = propertyValueMapper.selectByExample(example);
		for(PropertyValue propertyValue : list) {
			Property property = propertyService.get(propertyValue.getPtid());
			propertyValue.setProperty(property);
		}
		return list;
	}
	
	/**
	 * 更新
	 */
	@Override
	public void update(PropertyValue propertyValue) {
		propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
	}
	
	
}
