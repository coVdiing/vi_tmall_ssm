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
	 * չʾͼƬ����
	 * @param pid ͼƬ�����Ĳ�Ʒ����
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_productImage_list")
	public String list(int pid,Model model) {
		//��ѯProduct����
		Product product = productService.get(pid);
		Category category = categoryService.get(product.getCid());
		product.setCategory(category);
		//���pid�Ĳ�Ʒ�����ֲ�ͬ���͵�ͼƬ���ֱ𱣴�
		List<ProductImage> type_single = productImageService.list(pid, ProductImageService.type_single);
		List<ProductImage> type_detail = productImageService.list(pid, ProductImageService.type_detail);
		System.out.println("�鵽��ͼƬ:"+type_single);
		model.addAttribute("product", product);
		model.addAttribute("type_single",type_single);
		model.addAttribute("type_detail",type_detail);
		return "admin/listProductImage";
	}
	
	/**
	 * ���ͼƬ
	 * @param productImage ���ڽ���ע���pid,type
	 * @param image ���ڽ����ϴ���ͼƬ
	 * @return
	 */
	@RequestMapping("admin_productImage_add")
	public String add(ProductImage productImage,UploadedImageFile uploadedImageFile,HttpSession session){
		productImageService.add(productImage);
		//Ϊ�ϴ��ļ�׼��·��
		File imageFolder;
		File imageFolder_small = null;
		File imageFolder_middle = null;
		String fileName = productImage.getId()+".jpg";
		//������������·��
		if(productImage.getType().equals(ProductImageService.type_single)){
			//����ǵ���ͼƬ������
			imageFolder = new File(session.getServletContext().getRealPath("/img/productSingle"));
			imageFolder_small = new File(session.getServletContext().getRealPath("/img/productSingle_small"));
			imageFolder_middle = new File(session.getServletContext().getRealPath("/img/productMiddle"));
		} else {
			imageFolder = new File(session.getServletContext().getRealPath("/img/productDetail"));
		}
			File file = new File(imageFolder,fileName);
			//����ļ��в����ڣ�����
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			//��ͼƬ���ݴ�image����ת�뵽file��
			try {
				uploadedImageFile.getImage().transferTo(file);
				//��fileת��������jpg�ļ�������ֻ��jpg��׺���ļ�
				BufferedImage img = ImageUtil.change2jpg(file);
				ImageIO.write(img, "jpg", file);
				
				//����ϴ����ǵ���ͼƬ�����ͣ���������С��ͼƬ��
	            // �ı��С֮�󣬷ֱ��Ƶ�productSingle_middle��productSingle_smallĿ¼��
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
	 * ɾ������
	 * @param id ͨ��productImage��id����ɾ��
	 * @return
	 */
	@RequestMapping("admin_productImage_delete")
	public String delete(int id,HttpSession session) {
		//ͨ��idȷ��ͼƬ����
		ProductImage productImage = productImageService.get(id);
		String type = productImage.getType();
		int pid = productImage.getPid();
		//�����ݿ��жԼ�¼����ɾ��
		productImageService.delete(id);
		String fileName = id+".jpg";
		//���ļ�ϵͳ�н���Ӧ��ͼƬɾ��
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
