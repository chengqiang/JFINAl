package com.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.bean.Blog;
import com.jfinal.core.Controller;

/**
 * BlogController
 * ���� sql д�� Model �� Service �У���Ҫд�� Controller �У����ɺ�ϰ�ߣ������ڴ�����Ŀ�Ŀ�����ά��
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	public void index() {
		// ��ҳ
		setAttr("blogPage", Blog.dao.paginate(getParaToInt(0, 1), 10));
		render("blog.html");
	}
	
	public void add() {
	}
	
	@Before(BlogValidator.class)
	public void save() {
		getModel(Blog.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", Blog.dao.findById(getParaToInt()));
	}
	
	@Before(BlogValidator.class)
	public void update() {
		getModel(Blog.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		Blog.dao.deleteById(getParaToInt());
		redirect("/blog");
	}
}


