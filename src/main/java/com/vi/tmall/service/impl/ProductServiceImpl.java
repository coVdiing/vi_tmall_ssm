package com.vi.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.ProductMapper;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.ProductExample;
import com.vi.tmall.pojo.ProductImage;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductImageService;
import com.vi.tmall.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageService ProductImageService;
    @Autowired
    CategoryService categoryService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);

    }

    @Override
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        int cid = product.getCid();
        Category category = categoryService.get(cid);
        System.out.println("cid===>" + cid);//���Դ���
        System.out.println("category====>" + category.getName());//���Դ���
        product.setCategory(category);
        setFirstProductImage(product);
        return product;
    }

    @Override
    public List<Product> list(int cid) {
        //����Category id���в�ѯ
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        //����id��������
        example.setOrderByClause("id desc");
        Category category = categoryService.get(cid);

        List<Product> list = productMapper.selectByExample(example);
        //���ҵ��˶�Ӧ������֮�󣬸����Ǽ��϶�Ӧ��ͼƬ
        setFirstProductImage(list);
        for (Product product : list)
            product.setCategory(category);
        return list;
    }

    /**
     * ��product�����϶�Ӧ��ProductImage
     */
    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> plist =
                ProductImageService.list(product.getId(), ProductImageService.type_single);
        if (!plist.isEmpty()) {
            //���������ǿգ�ȡ�����е�һ��ͼƬ������Ϊproduct��productImage����
            product.setFirstProductImage(plist.get(0));
        }
    }

    public void setFirstProductImage(List<Product> list) {
        for (Product product : list)
            setFirstProductImage(product);
    }

    /**
     * Ϊ��������Ʒ����
     *
     * @param category
     */
    @Override
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }

    /**
     * Ϊ�����������Ʒ����
     *
     * @param categories
     */
    @Override
    public void fill(List<Category> categories) {
        for (Category category : categories)
            fill(category);
    }

    /**
     * Ϊ�����������Ƽ���Ʒ���ϣ����ѷ����µĲ�Ʒ������8��Ϊһ�У���ɶ��У������ں���ҳ���Ͻ�����ʾ
     *
     * @param categories
     */
    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category category : categories) {
            List<Product> products = category.getProducts();
            List<List<Product>> productByRow = new ArrayList<>();
            for (int i = 0; i< products.size() ; i+=productNumberEachRow) {
            	int size = i + productNumberEachRow;
            	size = size > products.size() ? products.size() : size;
            	List list = products.subList(i,size);
            	productByRow.add(list);
			}
            category.setProductsByRow(productByRow);
        }

    }
}
