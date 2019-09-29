package com.vi.tmall.controller;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.util.ImageUtil;
import com.vi.tmall.util.UploadedImageFile;

@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	CategoryService cs;

	/**
	 * 分类展示
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("admin_category_list")
	public String list(Model model,@RequestParam(value="page",defaultValue="1")int page) {
		PageHelper.startPage(page, 5);
		List<Category> list = cs.list();
		PageInfo<Category> pageInfo = new PageInfo<>(list,5);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo",pageInfo);
		return "admin/listCategory";
	}

	/**
	 * 添加分类
	 * 
	 * @param category
	 * @param session
	 * @param uploadedImageFile
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("admin_category_add")
	public String add(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
		cs.add(category);
		File imageFolder = new File(session.getServletContext().getRealPath("/img/category"));
		File file = new File(imageFolder, category.getId() + ".jpg");
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		uploadedImageFile.getImage().transferTo(file);
		BufferedImage img = ImageUtil.change2jpg(file);
		ImageIO.write(img, "jpg", file);
		return "redirect:admin_category_list";
	}

	/**
	 * 根据分类id跳转到分类编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("admin_category_edit")
	public String get(int id, Model model) {
		Category category = cs.get(id);
		model.addAttribute("category", category);
		return "admin/editCategory";
	}

	/**
	 * 更新分类信息
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("admin_category_update")
	public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile)
			throws IOException {
		cs.update(category);
		MultipartFile image = uploadedImageFile.getImage();
		if (null != image && !image.isEmpty()) {
			File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
			File file = new File(imageFolder, category.getId() + ".jpg");
			System.out.println(file.getPath());
			image.transferTo(file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jpg", file);
		}
		return "redirect:admin_category_list";
	}

	@RequestMapping("admin_category_delete")
	public String delete(int id, HttpSession session) {
		cs.delete(id);
		File file = new File(new File(session.getServletContext().getRealPath("/img/category")), id + ".jpg");
		file.delete();
		return "redirect:admin_category_list";
	}
}
