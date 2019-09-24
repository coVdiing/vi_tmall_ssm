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
	 * 对product对应的属性初始化
	 */
	@Override
	public void init(Product product) {
		//ͨ根据product对应的category查询对应属性
		List<Property> list = propertyService.list(product.getCid());
		for(Property property : list) {
			PropertyValue propertyValue = get(product.getId(),property.getId());
			if(propertyValue == null) {
				propertyValue = new PropertyValue();
				propertyValue.setPid(product.getId());
				propertyValue.setPtid(property.getId());
				//在数据库中插入该数据
				propertyValueMapper.insert(propertyValue);
			}
		}
	}
	
	/**
	 * 根据pid,ptid查询propertyValue对象
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
	 * 遍历product对应的propertyValue对象
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
	 * 更新propertyValue
	 */
	@Override
	public void update(PropertyValue propertyValue) {
		propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
	}
	
	
}
