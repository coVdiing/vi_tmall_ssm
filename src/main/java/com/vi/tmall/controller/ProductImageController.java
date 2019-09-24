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
	 * 展示产品图片
	 * @param pid 对应的 product ID
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_productImage_list")
	public String list(int pid,Model model) {
		//获取对应ID的product对象
		Product product = productService.get(pid);
		Category category = categoryService.get(product.getCid());
		product.setCategory(category);
		//根据type类型创建对应的ProductImage集合
		List<ProductImage> type_single = productImageService.list(pid, ProductImageService.type_single);
		List<ProductImage> type_detail = productImageService.list(pid, ProductImageService.type_detail);
		//System.out.println("调试:"+type_single);
		model.addAttribute("product", product);
		model.addAttribute("type_single",type_single);
		model.addAttribute("type_detail",type_detail);
		return "admin/listProductImage";
	}
	
	/**
	 * 添加图片
	 * @param productImage 接收pid的注入
	 * @param uploadedImageFile  接收image文件的注入
	 * @return
	 */
	@RequestMapping("admin_productImage_add")
	public String add(ProductImage productImage,UploadedImageFile uploadedImageFile,HttpSession session){
		productImageService.add(productImage);
		//准备文件路径
		File imageFolder;
		File imageFolder_small = null;
		File imageFolder_middle = null;
		String fileName = productImage.getId()+".jpg";
		//判断图片类型
		if(productImage.getType().equals(ProductImageService.type_single)){
			//如果是单个图片类型
			imageFolder = new File(session.getServletContext().getRealPath("/img/productSingle"));
			imageFolder_small = new File(session.getServletContext().getRealPath("/img/productSingle_small"));
			imageFolder_middle = new File(session.getServletContext().getRealPath("/img/productMiddle"));
		} else {
			imageFolder = new File(session.getServletContext().getRealPath("/img/productDetail"));
		}
			File file = new File(imageFolder,fileName);
			//如果文件夹不存在，创建其
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			//将上传的图片写入到项目路径下的文件中
			try {
				uploadedImageFile.getImage().transferTo(file);
				//调用工具类，将图片转换成真正的jpg图片，而非仅仅是.jpg后缀的文件
				BufferedImage img = ImageUtil.change2jpg(file);
				ImageIO.write(img, "jpg", file);
				
				//如果图片类型是单个类型的，将图片尺寸进行调整，生成中小型的对应图片
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
	 * 删除图片
	 * @param id productImage对应id
	 * @return
	 */
	@RequestMapping("admin_productImage_delete")
	public String delete(int id,HttpSession session) {
		//根据id获取对象
		ProductImage productImage = productImageService.get(id);
		String type = productImage.getType();
		int pid = productImage.getPid();
		//根据id删除数据库中的记录
		productImageService.delete(id);
		String fileName = id+".jpg";
		//删除数据库中的记录以后需要根据图片类型删除其对应的文件
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
