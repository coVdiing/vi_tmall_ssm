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
	 * ��ʼ������product��Ӧ��propertyValue�����ݿ��в�������
	 */
	@Override
	public void init(Product product) {
		//ͨ��product��ȡcategory id �ٻ�ȡ��Ӧ���Լ���
		List<Property> list = propertyService.list(product.getCid());
		for(Property property : list) {
			PropertyValue propertyValue = get(product.getId(),property.getId());
			if(propertyValue == null) {
				propertyValue = new PropertyValue();
				propertyValue.setPid(product.getId());
				propertyValue.setPtid(property.getId());
				//�������ݿ�
				propertyValueMapper.insert(propertyValue);
			}
		}
	}
	
	/**
	 * ����pid,ptid��ȡ����propertyValue����
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
	 * ���ؽ�product��Ӧ��propertyValue
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
	 * ����
	 */
	@Override
	public void update(PropertyValue propertyValue) {
		propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
	}
	
	
}
