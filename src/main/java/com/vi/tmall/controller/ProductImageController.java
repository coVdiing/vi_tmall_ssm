package com.vi.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.ProductImage;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductImageService;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.util.ImageUtil;
import com.vi.tmall.util.UploadedImageFile;

@Controller
@RequestMapping("")
public class ProductImageController {
	@Autowired
	ProductImageService productImageService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService; 
	
	/**
	 * 展示图片数据
	 * @param pid 图片所属的产品分类
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_productImage_list")
	public String list(int pid,Model model) {
		//查询Product对象
		Product product = productService.get(pid);
		Category category = categoryService.get(product.getCid());
		product.setCategory(category);
		//查出pid的产品的两种不同类型的图片，分别保存
		List<ProductImage> type_single = productImageService.list(pid, ProductImageService.type_single);
		List<ProductImage> type_detail = productImageService.list(pid, ProductImageService.type_detail);
		System.out.println("查到的图片:"+type_single);
		model.addAttribute("product", product);
		model.addAttribute("type_single",type_single);
		model.addAttribute("type_detail",type_detail);
		return "admin/listProductImage";
	}
	
	/**
	 * 添加图片
	 * @param productImage 用于接收注入的pid,type
	 * @param image 用于接收上传的图片
	 * @return
	 */
	@RequestMapping("admin_productImage_add")
	public String add(ProductImage productImage,UploadedImageFile uploadedImageFile,HttpSession session){
		productImageService.add(productImage);
		//为上传文件准备路径
		File imageFolder;
		File imageFolder_small = null;
		File imageFolder_middle = null;
		String fileName = productImage.getId()+".jpg";
		//根据类型生成路径
		if(productImage.getType().equals(ProductImageService.type_single)){
			//如果是单个图片的类型
			imageFolder = new File(session.getServletContext().getRealPath("/img/productSingle"));
			imageFolder_small = new File(session.getServletContext().getRealPath("/img/productSingle_small"));
			imageFolder_middle = new File(session.getServletContext().getRealPath("/img/productMiddle"));
		} else {
			imageFolder = new File(session.getServletContext().getRealPath("/img/productDetail"));
		}
			File file = new File(imageFolder,fileName);
			//如果文件夹不存在，创建
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			//将图片内容从image对象转入到file中
			try {
				uploadedImageFile.getImage().transferTo(file);
				//把file转成真正的jpg文件，而非只是jpg后缀的文件
				BufferedImage img = ImageUtil.change2jpg(file);
				ImageIO.write(img, "jpg", file);
				
				//如果上传的是单个图片的类型，把正常大小的图片，
	            // 改变大小之后，分别复制到productSingle_middle和productSingle_small目录下
				if(productImage.getType().equals(ProductImageService.type_single)){
					File file_small = new File(imageFolder_small,fileName);
	                File file_middle = new File(imageFolder_middle,fileName);
	                ImageUtil.resizeImage(file,56,56,file_small);
	                ImageUtil.resizeImage(file,217,190,file_middle);
				}
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "redirect:admin_productImage_list?pid="+productImage.getPid();
	}
	
	/**
	 * 删除功能
	 * @param id 通过productImage的id进行删除
	 * @return
	 */
	@RequestMapping("admin_productImage_delete")
	public String delete(int id,HttpSession session) {
		//通过id确定图片类型
		ProductImage productImage = productImageService.get(id);
		String type = productImage.getType();
		int pid = productImage.getPid();
		//在数据库中对记录进行删除
		productImageService.delete(id);
		String fileName = id+".jpg";
		//在文件系统中将对应的图片删除
		if(type.equals("type_detail")){
			File file = new File(session.getServletContext().getRealPath("img/productDetail"),fileName);
			file.delete();
		} else {
			String imageFolder = session.getServletContext().getRealPath("img/productSingle");
			String imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
			String imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
			File imageFile = new File(imageFolder, fileName);
			File imageFile_small = new File(imageFolder_small, fileName);
			File imageFile_middle = new File(imageFolder_middle, fileName);
			imageFile.delete();
			imageFile_small.delete();
			imageFile_middle.delete();
		}
		return "redirect:admin_productImage_list?pid="+pid;
	}
}
