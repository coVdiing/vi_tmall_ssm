package com.vi.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.vi.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vi.tmall.mapper.ProductMapper;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.ProductExample;
import com.vi.tmall.pojo.ProductImage;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageService ProductImageService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

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
        //System.out.println("调试cid===>" + cid);
       // System.out.println("调试category====>" + category.getName());
        product.setCategory(category);
        setFirstProductImage(product);
        return product;
    }

    @Override
    public List<Product> list(int cid) {
        //根据Category id获取Product对象集合
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        //查询结果按照product id倒序
        example.setOrderByClause("id desc");
        Category category = categoryService.get(cid);

        List<Product> list = productMapper.selectByExample(example);
        //给对应的Product对象设置firstProductImage属性和category属性
        setFirstProductImage(list);
        for (Product product : list)
            product.setCategory(category);
        return list;
    }

    /**
     * 给product对象设置对应的productImage对象
     */
    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> plist =
                ProductImageService.list(product.getId(), ProductImageService.type_single);
        if (!plist.isEmpty()) {
            //将查询到的图片集合中的第一张图片设置为firstProductImage
            product.setFirstProductImage(plist.get(0));
        }
    }

    public void setFirstProductImage(List<Product> list) {
        for (Product product : list)
            setFirstProductImage(product);
    }

    /**
     *  给product对象填充category对象
     *
     * @param category
     */
    @Override
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }

    /**
     * 批量填充category对象
     *
     * @param categories
     */
    @Override
    public void fill(List<Category> categories) {
        for (Category category : categories)
            fill(category);
    }

    /**
     * 填充推荐产品列表
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

    /**
     * 设置产品销量和评论数量
     * @param product
     */
    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product.getId());
        int reviewCount = reviewService.getCount(product.getId());
        product.setSaleCount(saleCount);
        product.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for(Product product : products)
            setSaleAndReviewNumber(product);
    }
}
